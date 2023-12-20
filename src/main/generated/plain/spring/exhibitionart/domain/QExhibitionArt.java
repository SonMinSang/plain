package plain.spring.exhibitionart.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExhibitionArt is a Querydsl query type for ExhibitionArt
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExhibitionArt extends EntityPathBase<ExhibitionArt> {

    private static final long serialVersionUID = -524386783L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExhibitionArt exhibitionArt = new QExhibitionArt("exhibitionArt");

    public final plain.spring.user.domain.QUser artist;

    public final StringPath description = createString("description");

    public final ListPath<plain.spring.ExhibitionArtImage.domain.ExhibitionArtImage, plain.spring.ExhibitionArtImage.domain.QExhibitionArtImage> detailImageUrls = this.<plain.spring.ExhibitionArtImage.domain.ExhibitionArtImage, plain.spring.ExhibitionArtImage.domain.QExhibitionArtImage>createList("detailImageUrls", plain.spring.ExhibitionArtImage.domain.ExhibitionArtImage.class, plain.spring.ExhibitionArtImage.domain.QExhibitionArtImage.class, PathInits.DIRECT2);

    public final plain.spring.exhibition.domain.QExhibition exhibition;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final ListPath<plain.spring.exhibitionartthumbnailimage.domain.ExhibitionArtThumbnailImage, plain.spring.exhibitionartthumbnailimage.domain.QExhibitionArtThumbnailImage> thumbnailImageUrls = this.<plain.spring.exhibitionartthumbnailimage.domain.ExhibitionArtThumbnailImage, plain.spring.exhibitionartthumbnailimage.domain.QExhibitionArtThumbnailImage>createList("thumbnailImageUrls", plain.spring.exhibitionartthumbnailimage.domain.ExhibitionArtThumbnailImage.class, plain.spring.exhibitionartthumbnailimage.domain.QExhibitionArtThumbnailImage.class, PathInits.DIRECT2);

    public QExhibitionArt(String variable) {
        this(ExhibitionArt.class, forVariable(variable), INITS);
    }

    public QExhibitionArt(Path<? extends ExhibitionArt> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExhibitionArt(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExhibitionArt(PathMetadata metadata, PathInits inits) {
        this(ExhibitionArt.class, metadata, inits);
    }

    public QExhibitionArt(Class<? extends ExhibitionArt> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.artist = inits.isInitialized("artist") ? new plain.spring.user.domain.QUser(forProperty("artist")) : null;
        this.exhibition = inits.isInitialized("exhibition") ? new plain.spring.exhibition.domain.QExhibition(forProperty("exhibition")) : null;
    }

}

