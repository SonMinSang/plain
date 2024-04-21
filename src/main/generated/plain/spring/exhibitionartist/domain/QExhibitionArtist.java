package plain.spring.exhibitionartist.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExhibitionArtist is a Querydsl query type for ExhibitionArtist
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExhibitionArtist extends EntityPathBase<ExhibitionArtist> {

    private static final long serialVersionUID = 94127237L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExhibitionArtist exhibitionArtist = new QExhibitionArtist("exhibitionArtist");

    public final plain.spring.exhibition.domain.QExhibition exhibition;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final plain.spring.user.domain.QUser user;

    public QExhibitionArtist(String variable) {
        this(ExhibitionArtist.class, forVariable(variable), INITS);
    }

    public QExhibitionArtist(Path<? extends ExhibitionArtist> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExhibitionArtist(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExhibitionArtist(PathMetadata metadata, PathInits inits) {
        this(ExhibitionArtist.class, metadata, inits);
    }

    public QExhibitionArtist(Class<? extends ExhibitionArtist> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.exhibition = inits.isInitialized("exhibition") ? new plain.spring.exhibition.domain.QExhibition(forProperty("exhibition")) : null;
        this.user = inits.isInitialized("user") ? new plain.spring.user.domain.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

