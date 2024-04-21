package plain.spring.usersetting;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserSetting is a Querydsl query type for UserSetting
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserSetting extends EntityPathBase<UserSetting> {

    private static final long serialVersionUID = -517236897L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserSetting userSetting = new QUserSetting("userSetting");

    public final BooleanPath activityNotification = createBoolean("activityNotification");

    public final BooleanPath blockRequest = createBoolean("blockRequest");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath registerNotification = createBoolean("registerNotification");

    public final BooleanPath serviceNotification = createBoolean("serviceNotification");

    public final plain.spring.user.domain.QUser user;

    public QUserSetting(String variable) {
        this(UserSetting.class, forVariable(variable), INITS);
    }

    public QUserSetting(Path<? extends UserSetting> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserSetting(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserSetting(PathMetadata metadata, PathInits inits) {
        this(UserSetting.class, metadata, inits);
    }

    public QUserSetting(Class<? extends UserSetting> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new plain.spring.user.domain.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

