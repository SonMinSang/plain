package plain.spring.thumbnailimage.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QThumbnailImage is a Querydsl query type for ThumbnailImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QThumbnailImage extends EntityPathBase<ThumbnailImage> {

    private static final long serialVersionUID = 2009458359L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QThumbnailImage thumbnailImage = new QThumbnailImage("thumbnailImage");

    public final plain.spring.art.domain.QArt art;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath url = createString("url");

    public QThumbnailImage(String variable) {
        this(ThumbnailImage.class, forVariable(variable), INITS);
    }

    public QThumbnailImage(Path<? extends ThumbnailImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QThumbnailImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QThumbnailImage(PathMetadata metadata, PathInits inits) {
        this(ThumbnailImage.class, metadata, inits);
    }

    public QThumbnailImage(Class<? extends ThumbnailImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.art = inits.isInitialized("art") ? new plain.spring.art.domain.QArt(forProperty("art"), inits.get("art")) : null;
    }

}

