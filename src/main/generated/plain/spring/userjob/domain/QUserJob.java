package plain.spring.userjob.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserJob is a Querydsl query type for UserJob
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserJob extends EntityPathBase<UserJob> {

    private static final long serialVersionUID = 1673342433L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserJob userJob = new QUserJob("userJob");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final plain.spring.job.domain.QJob job;

    public final plain.spring.user.domain.QUser user;

    public QUserJob(String variable) {
        this(UserJob.class, forVariable(variable), INITS);
    }

    public QUserJob(Path<? extends UserJob> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserJob(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserJob(PathMetadata metadata, PathInits inits) {
        this(UserJob.class, metadata, inits);
    }

    public QUserJob(Class<? extends UserJob> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.job = inits.isInitialized("job") ? new plain.spring.job.domain.QJob(forProperty("job")) : null;
        this.user = inits.isInitialized("user") ? new plain.spring.user.domain.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

