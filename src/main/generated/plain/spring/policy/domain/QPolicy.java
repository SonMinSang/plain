package plain.spring.policy.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPolicy is a Querydsl query type for Policy
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPolicy extends EntityPathBase<Policy> {

    private static final long serialVersionUID = -1825510115L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPolicy policy = new QPolicy("policy");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath receiveMarketingInfo = createBoolean("receiveMarketingInfo");

    public final BooleanPath termsOfService = createBoolean("termsOfService");

    public final BooleanPath useOfPersonalInfo = createBoolean("useOfPersonalInfo");

    public final plain.spring.user.domain.QUser user;

    public QPolicy(String variable) {
        this(Policy.class, forVariable(variable), INITS);
    }

    public QPolicy(Path<? extends Policy> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPolicy(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPolicy(PathMetadata metadata, PathInits inits) {
        this(Policy.class, metadata, inits);
    }

    public QPolicy(Class<? extends Policy> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new plain.spring.user.domain.QUser(forProperty("user")) : null;
    }

}

