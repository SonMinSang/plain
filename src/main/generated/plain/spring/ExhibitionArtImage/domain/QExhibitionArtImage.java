package plain.spring.ExhibitionArtImage.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExhibitionArtImage is a Querydsl query type for ExhibitionArtImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExhibitionArtImage extends EntityPathBase<ExhibitionArtImage> {

    private static final long serialVersionUID = -1974955545L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExhibitionArtImage exhibitionArtImage = new QExhibitionArtImage("exhibitionArtImage");

    public final plain.spring.exhibitionart.domain.QExhibitionArt exhibitionArt;

    public final NumberPath<Integer> height = createNumber("height", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath url = createString("url");

    public final NumberPath<Integer> width = createNumber("width", Integer.class);

    public QExhibitionArtImage(String variable) {
        this(ExhibitionArtImage.class, forVariable(variable), INITS);
    }

    public QExhibitionArtImage(Path<? extends ExhibitionArtImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExhibitionArtImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExhibitionArtImage(PathMetadata metadata, PathInits inits) {
        this(ExhibitionArtImage.class, metadata, inits);
    }

    public QExhibitionArtImage(Class<? extends ExhibitionArtImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.exhibitionArt = inits.isInitialized("exhibitionArt") ? new plain.spring.exhibitionart.domain.QExhibitionArt(forProperty("exhibitionArt"), inits.get("exhibitionArt")) : null;
    }

}

