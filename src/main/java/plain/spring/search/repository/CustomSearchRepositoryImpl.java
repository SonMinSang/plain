package plain.spring.search.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static plain.spring.search.domain.QSearch.search;

@Repository
@RequiredArgsConstructor
public class CustomSearchRepositoryImpl implements CustomSearchRepository{
    private final JPAQueryFactory queryFactory;

    public List<String> findSearchByUserId(Long userId){
        JPAQuery<Long> subQuery = queryFactory
                .select(search.id.max())
                .from(search)
                .where(search.user.id.eq(userId))
                .groupBy(search.query);

        List<String> results = queryFactory
                .select(search.query)
                .from(search)
                .where(search.user.id.eq(userId)
                        .and(search.id.in(subQuery)))
                .orderBy(search.id.desc())
                .limit(10)
                .fetch();

        return results;
    }
    private BooleanBuilder eqUserId(Long userId) {
        return new BooleanBuilder(search.user.id.eq(userId));
    }
}
