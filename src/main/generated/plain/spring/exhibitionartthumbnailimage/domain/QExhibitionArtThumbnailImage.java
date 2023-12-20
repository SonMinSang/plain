package plain.spring.exhibitionartthumbnailimage.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExhibitionArtThumbnailImage is a Querydsl query type for ExhibitionArtThumbnailImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExhibitionArtThumbnailImage extends EntityPathBase<ExhibitionArtThumbnailImage> {

    private static final long serialVersionUID = -732331455L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExhibitionArtThumbnailImage exhibitionArtThumbnailImage = new QExhibitionArtThumbnailImage("exhibitionArtThumbnailImage");

    public final plain.spring.exhibitionart.domain.QExhibitionArt exhibitionArt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath url = createString("url");

    public QExhibitionArtThumbnailImage(String variable) {
        this(ExhibitionArtThumbnailImage.class, forVariable(variable), INITS);
    }

    public QExhibitionArtThumbnailImage(Path<? extends ExhibitionArtThumbnailImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExhibitionArtThumbnailImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExhibitionArtThumbnailImage(PathMetadata metadata, PathInits inits) {
        this(ExhibitionArtThumbnailImage.class, metadata, inits);
    }

    public QExhibitionArtThumbnailImage(Class<? extends ExhibitionArtThumbnailImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.exhibitionArt = inits.isInitialized("exhibitionArt") ? new plain.spring.exhibitionart.domain.QExhibitionArt(forProperty("exhibitionArt"), inits.get("exhibitionArt")) : null;
    }

}

