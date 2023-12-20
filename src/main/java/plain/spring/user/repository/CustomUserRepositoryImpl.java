package plain.spring.user.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;
import plain.spring.user.domain.User;

import java.util.List;

import static plain.spring.job.domain.QJob.job;
import static plain.spring.tag.domain.QTag.tag;
import static plain.spring.user.domain.QUser.user;
import static plain.spring.userjob.domain.QUserJob.userJob;
import static plain.spring.usertag.domain.QUserTag.userTag;


@Repository
@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository{
    private final JPAQueryFactory queryFactory;

    public Slice<User> findAllArtistsByQueryAndCategory(String query, String category, Pageable pageable){
        List<User> results = queryFactory
                .selectFrom(user)
                .leftJoin(user.userJobs, userJob)
                .leftJoin(userJob.job, job)
                .leftJoin(user.userTags, userTag)
                .leftJoin(userTag.tag, tag)
                .where(containsQuery(query).or(eqTag(query)).and(eqCategory(category)))
                .offset(pageable.getOffset())
                .orderBy(user.followedCount.desc())
                .limit(pageable.getPageSize() + 1)
                .fetch();
        boolean hasNext = false;
        if (results.size() > pageable.getPageSize()) {
            results.remove(pageable.getPageSize());
            hasNext = true;
        }
        return new SliceImpl<>(results, pageable, hasNext);
    }

    private BooleanBuilder containsQuery(String query) {
        if (query == null || query.isBlank()) {
            return new BooleanBuilder();
        }
        return new BooleanBuilder(user.nickname.contains(query));
    }
    private BooleanBuilder eqCategory(String category) {
        if (category == null || category.isBlank()) {
            return new BooleanBuilder();
        }
        switch (category){
            case "공예" :
                return new BooleanBuilder(job.name.eq("공예가"));
            case "제품":
                return new BooleanBuilder(job.name.eq("제품 디자이너").or(job.name.eq("UX 디자이너")));
            case "패션":
                return new BooleanBuilder(job.name.eq("패션 디자이너"));
            case "그래픽":
                return new BooleanBuilder(job.name.eq("시각 디자이너").or(job.name.eq("3D 아티스트")).or(job.name.eq("크리에이터")).or(job.name.eq("일러스트레이터")));
            case "그림":
                return new BooleanBuilder(job.name.eq("화가"));
            default:
                return new BooleanBuilder();
        }
    }
    private BooleanBuilder eqTag(String query) {
        if (query == null || query.isBlank()) {
            return new BooleanBuilder();
        }
        return new BooleanBuilder(tag.name.eq(query));
    }
}
