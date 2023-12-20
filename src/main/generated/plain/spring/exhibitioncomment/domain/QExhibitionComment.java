package plain.spring.exhibitioncomment.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExhibitionComment is a Querydsl query type for ExhibitionComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExhibitionComment extends EntityPathBase<ExhibitionComment> {

    private static final long serialVersionUID = 1198927009L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExhibitionComment exhibitionComment = new QExhibitionComment("exhibitionComment");

    public final plain.spring.commons.util.QBaseTimeEntity _super = new plain.spring.commons.util.QBaseTimeEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final plain.spring.exhibition.domain.QExhibition exhibition;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final plain.spring.user.domain.QUser user;

    public QExhibitionComment(String variable) {
        this(ExhibitionComment.class, forVariable(variable), INITS);
    }

    public QExhibitionComment(Path<? extends ExhibitionComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExhibitionComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExhibitionComment(PathMetadata metadata, PathInits inits) {
        this(ExhibitionComment.class, metadata, inits);
    }

    public QExhibitionComment(Class<? extends ExhibitionComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.exhibition = inits.isInitialized("exhibition") ? new plain.spring.exhibition.domain.QExhibition(forProperty("exhibition")) : null;
        this.user = inits.isInitialized("user") ? new plain.spring.user.domain.QUser(forProperty("user")) : null;
    }

}

