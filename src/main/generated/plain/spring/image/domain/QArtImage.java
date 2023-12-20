package plain.spring.image.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QArtImage is a Querydsl query type for ArtImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QArtImage extends EntityPathBase<ArtImage> {

    private static final long serialVersionUID = 1701996722L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QArtImage artImage = new QArtImage("artImage");

    public final plain.spring.art.domain.QArt art;

    public final NumberPath<Integer> height = createNumber("height", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath url = createString("url");

    public final NumberPath<Integer> width = createNumber("width", Integer.class);

    public QArtImage(String variable) {
        this(ArtImage.class, forVariable(variable), INITS);
    }

    public QArtImage(Path<? extends ArtImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QArtImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QArtImage(PathMetadata metadata, PathInits inits) {
        this(ArtImage.class, metadata, inits);
    }

    public QArtImage(Class<? extends ArtImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.art = inits.isInitialized("art") ? new plain.spring.art.domain.QArt(forProperty("art"), inits.get("art")) : null;
    }

}

