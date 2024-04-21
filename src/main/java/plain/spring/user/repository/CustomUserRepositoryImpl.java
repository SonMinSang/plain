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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static plain.spring.art.domain.QArt.art;
import static plain.spring.block.QBlock.block;
import static plain.spring.job.domain.QJob.job;
import static plain.spring.tag.domain.QTag.tag;
import static plain.spring.user.domain.QUser.user;
import static plain.spring.userjob.domain.QUserJob.userJob;
import static plain.spring.usertag.domain.QUserTag.userTag;


@Repository
@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository{
    private final JPAQueryFactory queryFactory;

    public Slice<User> findAllArtistsByQueryAndCategory(String userId, String query, String category, Pageable pageable){
        if (userId == null){
            List<User> results = queryFactory
                    .selectFrom(user)
                    .leftJoin(user.userTags, userTag).fetchJoin()
                    .leftJoin(user.userJobs, userJob)
                    .where(containsQuery(query)
                                    .or(eqTag(query))
                            .and(eqCategory(category).and(hasArt()))
                    )
                    .offset(pageable.getPageNumber() * pageable.getPageSize())
                    .orderBy(user.followedCount.desc(), user.id.asc())
                    .limit(pageable.getPageSize() + 1)
                    .fetch();
            boolean hasNext = false;
            if (results.size() > pageable.getPageSize()) {
                results.remove(pageable.getPageSize());
                hasNext = true;
            }
            return new SliceImpl<>(results, pageable, hasNext);
        } else {
            List<User> blocked =  queryFactory.selectFrom(block)
                    .where(block.blocker.id.eq(Long.parseLong(userId)))
                    .fetch()
                    .stream()
                    .flatMap(block -> Stream.of(block.getBlocked()))
                    .collect(Collectors.toList());
            List<User> blocker =  queryFactory.selectFrom(block)
                    .where(block.blocked.id.eq(Long.parseLong(userId)))
                    .fetch()
                    .stream()
                    .flatMap(block -> Stream.of(block.getBlocker()))
                    .collect(Collectors.toList());
            List<User> results = queryFactory
                    .selectFrom(user)
                    .leftJoin(user.userTags, userTag)
                    .leftJoin(user.userJobs, userJob)
                    .leftJoin(userJob.job)
                    .leftJoin(userTag.tag)
                    .where((containsQuery(query).or(eqTag(query))).and(eqCategory(category)).and(hasArt()), notBlocked(blocker), notBlocked(blocked))
                    .offset(pageable.getPageNumber() * pageable.getPageSize())
                    .orderBy(user.followedCount.desc(), user.id.asc())
                    .limit(pageable.getPageSize() + 1)
                    .fetch();
            boolean hasNext = false;
            if (results.size() > pageable.getPageSize()) {
                results.remove(pageable.getPageSize());
                hasNext = true;
            }
            return new SliceImpl<>(results, pageable, hasNext);
        }
    }

    private BooleanBuilder containsQuery(String query) {
        if (query == null || query.isBlank()) {
            return new BooleanBuilder();
        }
        return new BooleanBuilder(user.nickname.toUpperCase().contains(query.toUpperCase()));
    }
    private BooleanBuilder hasArt() {

        return new BooleanBuilder(user.arts.size().gt(0));
    }
    private BooleanBuilder eqCategory(String category) {
        if (category == null || category.isBlank()) {
            return new BooleanBuilder();
        }
        switch (category){
            case "공예" :
                return new BooleanBuilder(userJob.job.name.eq("공예가"));
            case "리빙":
                return new BooleanBuilder(userJob.job.name.eq("제품 디자이너").or(userJob.job.name.eq("공예가")));
            case "패션":
                return new BooleanBuilder(userJob.job.name.eq("패션 디자이너"));
            case "그래픽":
                return new BooleanBuilder(userJob.job.name.eq("시각 디자이너").or(userJob.job.name.eq("3D 아티스트")).or(userJob.job.name.eq("크리에이터")).or(userJob.job.name.eq("일러스트레이터")).or(userJob.job.name.eq("UX디자이너")));
            case "그림":
                return new BooleanBuilder(userJob.job.name.eq("화가"));
            default:
                return new BooleanBuilder();
        }
    }

    private BooleanBuilder notBlocked(List<User> users){
        return new BooleanBuilder(user.notIn(users));
    }

    private BooleanBuilder eqTag(String query) {
        if (query == null || query.isBlank()) {
            return new BooleanBuilder();
        }
        return new BooleanBuilder(userTag.tag.name.eq(query));
    }
}
