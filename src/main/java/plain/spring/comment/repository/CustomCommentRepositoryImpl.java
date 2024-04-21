package plain.spring.comment.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import plain.spring.art.domain.Art;
import plain.spring.comment.domain.Comment;
import plain.spring.user.domain.User;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static plain.spring.art.domain.QArt.art;
import static plain.spring.block.QBlock.block;
import static plain.spring.comment.domain.QComment.comment;

@Repository
@RequiredArgsConstructor
public class CustomCommentRepositoryImpl implements CustomCommentRepository{
    private final JPAQueryFactory queryFactory;


    @Override
    public List<Comment> findAllWithUserByArt(Art art, Long userId) {
        List<Comment> results;
        List<User> blocked =  queryFactory.selectFrom(block)
                .where(block.blocker.id.eq(userId))
                .fetch()
                .stream()
                .flatMap(block -> Stream.of(block.getBlocked()))
                .collect(Collectors.toList());
        List<User> blocker =  queryFactory.selectFrom(block)
                .where(block.blocked.id.eq(userId))
                .fetch()
                .stream()
                .flatMap(block -> Stream.of(block.getBlocker()))
                .collect(Collectors.toList());


        if (userId == null){
            results = queryFactory
                    .selectFrom(comment)
                    .where(comment.art.eq(art))
                    .leftJoin(comment.user).fetchJoin()
                    .fetch();
        } else {
            results = queryFactory
                    .selectFrom(comment)
                    .where(
                            comment.art.eq(art), notBlocked(blocked), notBlocked(blocker)
                    )
                    .leftJoin(comment.user).fetchJoin()
                    .fetch();
        }
        return results;
    }
    private BooleanBuilder notBlocked(List<User> users){
        return new BooleanBuilder(comment.user.notIn(users));
    }


}
