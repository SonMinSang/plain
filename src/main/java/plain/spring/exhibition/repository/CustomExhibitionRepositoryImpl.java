package plain.spring.exhibition.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import plain.spring.exhibition.dto.ExhibitionWithLikes;
import plain.spring.exhibition.dto.ExhibitionSummary;

import java.util.List;
import java.util.Optional;

import static plain.spring.exhibition.domain.QExhibition.exhibition;
import static plain.spring.exhibitionlikes.domain.QExhibitionLikes.exhibitionLikes;
import static plain.spring.follow.domain.QFollow.follow;
import static plain.spring.user.domain.QUser.user;


@Repository
@RequiredArgsConstructor
public class CustomExhibitionRepositoryImpl implements CustomExhibitionRepository{
    private final JPAQueryFactory queryFactory;
    public List<ExhibitionSummary> findAllAvailableExhibition(Long userId){
        List<ExhibitionSummary> results;
        if (userId != null) {
            results = queryFactory
                    .select(
                            Projections.constructor(ExhibitionSummary.class,
                                    exhibition.id,
                                    exhibition.posterImageUrl,
                                    exhibition.description,
                                    exhibition.backgroundColor,
                                    exhibition.likesCount,
                                    JPAExpressions.selectOne()
                                            .from(exhibitionLikes)
                                            .where(eqLikesUserId(userId).and(exhibitionLikes.exhibition.eq(exhibition)))
                                            .exists().as("isLikes"),
                                    exhibition.commentCount
                            )
                    )
                    .from(exhibition)
                    .where(exhibition.status.eq("available"))
                    .fetch();
        }
        else {
            results = queryFactory
                    .select(
                            Projections.constructor(ExhibitionSummary.class,
                                    exhibition.id,
                                    exhibition.posterImageUrl,
                                    exhibition.description,
                                    exhibition.backgroundColor,
                                    exhibition.likesCount,
                                    Expressions.asBoolean(false).as("isLikes"),
                                    exhibition.commentCount
                            )
                    )
                    .from(exhibition)
                    .where(exhibition.status.eq("available"))
                    .fetch();
        }
        return results;
    }

    public Optional<ExhibitionWithLikes> findExhibitionWithLikes(Long exhibitionId, Long userId){
        Optional<ExhibitionWithLikes> result;
        if (userId != null){
            result = Optional.ofNullable(queryFactory
                    .select(
                            Projections.constructor(ExhibitionWithLikes.class,
                                    exhibition,
                                    JPAExpressions.selectOne()
                                            .from(exhibitionLikes)
                                            .where(eqLikesUserId(userId).and(exhibitionLikes.exhibition.eq(exhibition)))
                                            .exists().as("isLikes")
                            )
                    )
                    .from(exhibition)
                    .where(eqExhibitionId(exhibitionId))
                    .fetchOne());
        } else {
            result = Optional.ofNullable(queryFactory
                    .select(
                            Projections.constructor(ExhibitionWithLikes.class,
                                    exhibition,
                                    Expressions.asBoolean(false).as("isLikes")
                            )
                    )
                    .from(exhibition)
                    .where(eqExhibitionId(exhibitionId))
                    .fetchOne());
        }

        return result;
    }

    public List<Long> findUserFollows(Long userId, List<Long> userIds){
        List<Long> result = queryFactory
                .select(
                       follow.following.id
                ).from(follow)
                .leftJoin(follow.follower, user).fetchJoin()
                .leftJoin(follow.following, user).fetchJoin()
                .where(follow.follower.id.eq(userId).and(follow.follower.id.in(userIds)))
                .fetch();
        return result;
    }

    private BooleanBuilder eqExhibitionId(Long exhibitionId){
        if (exhibitionId == null)
            return new BooleanBuilder();
        return new BooleanBuilder(exhibition.id.eq(exhibitionId));
    }

    private BooleanBuilder eqLikesUserId(Long userId){
        if (userId == null)
            return new BooleanBuilder();
        return new BooleanBuilder(exhibitionLikes.user.id.eq(userId));
    }

}
