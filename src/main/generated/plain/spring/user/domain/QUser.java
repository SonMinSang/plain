package plain.spring.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 674451151L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final ListPath<plain.spring.art.domain.Art, plain.spring.art.domain.QArt> arts = this.<plain.spring.art.domain.Art, plain.spring.art.domain.QArt>createList("arts", plain.spring.art.domain.Art.class, plain.spring.art.domain.QArt.class, PathInits.DIRECT2);

    public final StringPath backgroundImageUrl = createString("backgroundImageUrl");

    public final StringPath deviceToken = createString("deviceToken");

    public final StringPath email = createString("email");

    public final NumberPath<Integer> followedCount = createNumber("followedCount", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nickname = createString("nickname");

    public final StringPath oauthId = createString("oauthId");

    public final StringPath openChatUrl = createString("openChatUrl");

    public final StringPath profileImageUrl = createString("profileImageUrl");

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public final ListPath<plain.spring.userjob.domain.UserJob, plain.spring.userjob.domain.QUserJob> userJobs = this.<plain.spring.userjob.domain.UserJob, plain.spring.userjob.domain.QUserJob>createList("userJobs", plain.spring.userjob.domain.UserJob.class, plain.spring.userjob.domain.QUserJob.class, PathInits.DIRECT2);

    public final plain.spring.usersetting.QUserSetting userSetting;

    public final ListPath<plain.spring.usertag.domain.UserTag, plain.spring.usertag.domain.QUserTag> userTags = this.<plain.spring.usertag.domain.UserTag, plain.spring.usertag.domain.QUserTag>createList("userTags", plain.spring.usertag.domain.UserTag.class, plain.spring.usertag.domain.QUserTag.class, PathInits.DIRECT2);

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userSetting = inits.isInitialized("userSetting") ? new plain.spring.usersetting.QUserSetting(forProperty("userSetting"), inits.get("userSetting")) : null;
    }

}

