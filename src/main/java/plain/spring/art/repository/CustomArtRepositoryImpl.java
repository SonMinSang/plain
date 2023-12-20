package plain.spring.art.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;
import plain.spring.art.domain.Art;
import plain.spring.art.dto.ArtWithFollowAndLikes;
import plain.spring.art.dto.ArtWithLikes;

import java.util.List;
import java.util.Optional;

import static plain.spring.art.domain.QArt.art;
import static plain.spring.arttag.domain.QArtTag.artTag;
import static plain.spring.follow.domain.QFollow.follow;
import static plain.spring.likes.domain.QLikes.likes;
import static plain.spring.tag.domain.QTag.tag;
import static plain.spring.user.domain.QUser.user;

@Repository
@RequiredArgsConstructor
public class CustomArtRepositoryImpl implements CustomArtRepository {
    private final JPAQueryFactory queryFactory;

    public List<ArtWithLikes> findAllArtsByCategoryWithLikes(String category, Long id, Long userId){
        List<ArtWithLikes> results;
        if (userId == null){
            results = queryFactory
                    .select(Projections.constructor(ArtWithLikes.class,
                            art,
                            Expressions.asBoolean(false).as("isLikes")))
                    .from(art)
                    .leftJoin(art.artist, user).fetchJoin()
                    .where(eqCategory(category), ltLastId(id))
                    .orderBy(art.id.desc())
                    .limit(20)
                    .fetch();
        } else {
            results = queryFactory
                    .select(Projections.constructor(ArtWithLikes.class,
                            art,
                            likes.user.id.isNotNull()))
                    .from(art)
                    .leftJoin(art.artist, user).fetchJoin()
                    .leftJoin(likes).on(likes.art.eq(art).and(likes.user.id.eq(userId)))
                    .where(eqCategory(category), ltLastId(id))
                    .orderBy(art.id.desc())
                    .limit(20)
                    .fetch();
        }
        return results;
    }
    public Optional<ArtWithFollowAndLikes> findArtByIdWithFollowAndLikes(Long artId, Long userId){
        Optional<ArtWithFollowAndLikes> results;
        if (userId != null){
            results = Optional.ofNullable(queryFactory
                    .select(Projections.fields(ArtWithFollowAndLikes.class,
                            art,
                            JPAExpressions.selectOne()
                                    .from(likes)
                                    .where(eqLikesArtId(artId)
                                            .and(eqLikesUserId(userId)))
                                    .exists().as("isLiked"),
                            JPAExpressions.selectOne()
                                    .from(follow)
                                    .where(eqFollowerUserId(userId)
                                            .and(eqFollowingUserId()))
                                    .exists().as("isFollowing")))
                    .from(art)
                    .leftJoin(art.artist, user).fetchJoin()
                    .where(eqId(artId))
                    .fetchOne());
        } else {
            results = Optional.ofNullable(queryFactory
                    .select(Projections.fields(ArtWithFollowAndLikes.class,
                            art,
                            Expressions.asBoolean(false).as("isLiked"), // isLiked 필드를 false로 설정
                            Expressions.asBoolean(false).as("isFollowing")))
                    .from(art)
                    .leftJoin(art.artist, user).fetchJoin()
                    .where(eqId(artId))
                    .fetchOne());
        }
        return results;
    }


    public Slice<Art> findArtsByQueryAndCategory(String query, String category, Pageable pageable){
        List<Art> results = queryFactory
                .selectFrom(art)
                .leftJoin(art.artTagList, artTag)
                .leftJoin(artTag.tag, tag)
                .where(eqCategory(category).and(containsQuery(query).or(eqTag(query))))
                .orderBy(art.likesCount.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();
        boolean hasNext = false;
        if (results.size() > pageable.getPageSize()) {
            results.remove(pageable.getPageSize());
            hasNext = true;
        }
        return new SliceImpl<>(results, pageable, hasNext);
    }



    public List<Art> findSimilarArts(List<String> tags, Long artId){
        List<Art> results = queryFactory
                .selectFrom(art)
                .leftJoin(art.artTagList, artTag)
                .leftJoin(artTag.tag, tag)
                .where(inTags(tags).and(notEqId(artId)))
                .limit(5)
                .fetch();

        return results;
    }
    private BooleanBuilder eqId(Long artId){
        return new BooleanBuilder(art.id.eq(artId));
    }

    private BooleanBuilder inTags(List<String> tags){

        return new BooleanBuilder(tag.name.in(tags));
    }

    private BooleanBuilder eqFollowerUserId(Long userId){
        if (userId == null)
            return new BooleanBuilder();
        return new BooleanBuilder(follow.follower.id.eq(userId));
    }

    private BooleanBuilder eqFollowingUserId(){
        return new BooleanBuilder(follow.following.id.eq(art.artist.id));
    }
    private BooleanBuilder eqLikesArtId(Long artId){
        if (artId == null)
            return new BooleanBuilder();
        return new BooleanBuilder(likes.art.id.eq(artId));
    }


    private BooleanBuilder eqLikesUserId(Long userId){
        if (userId == null)
            return new BooleanBuilder();
        return new BooleanBuilder(likes.user.id.eq(userId));
    }

    private BooleanBuilder notEqId(Long id) {
        if (id == null)
            return new BooleanBuilder();
        return new BooleanBuilder(art.id.ne(id));
    }

    private BooleanBuilder ltLastId(Long id) {
        if (id == null)
            return new BooleanBuilder();
        return new BooleanBuilder(art.id.lt(id));
    }

    private BooleanBuilder containsQuery(String query) {
        if (query == null || query.isBlank()) {
            return new BooleanBuilder();
        }
        return new BooleanBuilder(art.name.contains(query));
    }
    private BooleanBuilder eqTag(String query) {
        if (query == null || query.isBlank()) {
            return new BooleanBuilder();
        }
        return new BooleanBuilder(tag.name.eq(query));
    }
    private BooleanBuilder eqCategory(String category) {
        if (category == null || category.isBlank()) {
            return new BooleanBuilder();
        }
        return new BooleanBuilder(art.category.eq(category));
    }

}
