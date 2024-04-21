package plain.spring.exhibitionlikes.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExhibitionLikes is a Querydsl query type for ExhibitionLikes
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExhibitionLikes extends EntityPathBase<ExhibitionLikes> {

    private static final long serialVersionUID = 1797797761L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExhibitionLikes exhibitionLikes = new QExhibitionLikes("exhibitionLikes");

    public final plain.spring.exhibition.domain.QExhibition exhibition;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final plain.spring.user.domain.QUser user;

    public QExhibitionLikes(String variable) {
        this(ExhibitionLikes.class, forVariable(variable), INITS);
    }

    public QExhibitionLikes(Path<? extends ExhibitionLikes> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExhibitionLikes(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExhibitionLikes(PathMetadata metadata, PathInits inits) {
        this(ExhibitionLikes.class, metadata, inits);
    }

    public QExhibitionLikes(Class<? extends ExhibitionLikes> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.exhibition = inits.isInitialized("exhibition") ? new plain.spring.exhibition.domain.QExhibition(forProperty("exhibition")) : null;
        this.user = inits.isInitialized("user") ? new plain.spring.user.domain.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

