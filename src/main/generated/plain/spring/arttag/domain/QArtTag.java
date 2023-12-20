package plain.spring.arttag.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QArtTag is a Querydsl query type for ArtTag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QArtTag extends EntityPathBase<ArtTag> {

    private static final long serialVersionUID = -678178169L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QArtTag artTag = new QArtTag("artTag");

    public final plain.spring.art.domain.QArt art;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final plain.spring.tag.domain.QTag tag;

    public QArtTag(String variable) {
        this(ArtTag.class, forVariable(variable), INITS);
    }

    public QArtTag(Path<? extends ArtTag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QArtTag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QArtTag(PathMetadata metadata, PathInits inits) {
        this(ArtTag.class, metadata, inits);
    }

    public QArtTag(Class<? extends ArtTag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.art = inits.isInitialized("art") ? new plain.spring.art.domain.QArt(forProperty("art"), inits.get("art")) : null;
        this.tag = inits.isInitialized("tag") ? new plain.spring.tag.domain.QTag(forProperty("tag")) : null;
    }

}

