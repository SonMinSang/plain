package plain.spring.art.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QArt is a Querydsl query type for Art
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QArt extends EntityPathBase<Art> {

    private static final long serialVersionUID = -544882847L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QArt art = new QArt("art");

    public final plain.spring.commons.util.QBaseTimeEntity _super = new plain.spring.commons.util.QBaseTimeEntity(this);

    public final ListPath<plain.spring.image.domain.ArtImage, plain.spring.image.domain.QArtImage> artImageUrls = this.<plain.spring.image.domain.ArtImage, plain.spring.image.domain.QArtImage>createList("artImageUrls", plain.spring.image.domain.ArtImage.class, plain.spring.image.domain.QArtImage.class, PathInits.DIRECT2);

    public final plain.spring.user.domain.QUser artist;

    public final ListPath<plain.spring.arttag.domain.ArtTag, plain.spring.arttag.domain.QArtTag> artTagList = this.<plain.spring.arttag.domain.ArtTag, plain.spring.arttag.domain.QArtTag>createList("artTagList", plain.spring.arttag.domain.ArtTag.class, plain.spring.arttag.domain.QArtTag.class, PathInits.DIRECT2);

    public final StringPath category = createString("category");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<plain.spring.likes.domain.Likes, plain.spring.likes.domain.QLikes> likes = this.<plain.spring.likes.domain.Likes, plain.spring.likes.domain.QLikes>createList("likes", plain.spring.likes.domain.Likes.class, plain.spring.likes.domain.QLikes.class, PathInits.DIRECT2);

    public final NumberPath<Integer> likesCount = createNumber("likesCount", Integer.class);

    public final StringPath name = createString("name");

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final ListPath<plain.spring.thumbnailimage.domain.ThumbnailImage, plain.spring.thumbnailimage.domain.QThumbnailImage> thumbNailImageUrls = this.<plain.spring.thumbnailimage.domain.ThumbnailImage, plain.spring.thumbnailimage.domain.QThumbnailImage>createList("thumbNailImageUrls", plain.spring.thumbnailimage.domain.ThumbnailImage.class, plain.spring.thumbnailimage.domain.QThumbnailImage.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final NumberPath<Long> version = createNumber("version", Long.class);

    public QArt(String variable) {
        this(Art.class, forVariable(variable), INITS);
    }

    public QArt(Path<? extends Art> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QArt(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QArt(PathMetadata metadata, PathInits inits) {
        this(Art.class, metadata, inits);
    }

    public QArt(Class<? extends Art> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.artist = inits.isInitialized("artist") ? new plain.spring.user.domain.QUser(forProperty("artist")) : null;
    }

}

