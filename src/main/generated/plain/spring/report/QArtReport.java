package plain.spring.report;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QArtReport is a Querydsl query type for ArtReport
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QArtReport extends EntityPathBase<ArtReport> {

    private static final long serialVersionUID = 1488118458L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QArtReport artReport = new QArtReport("artReport");

    public final plain.spring.art.domain.QArt art;

    public final EnumPath<ArtReportProblem> artReportProblem = createEnum("artReportProblem", ArtReportProblem.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final plain.spring.user.domain.QUser reporter;

    public QArtReport(String variable) {
        this(ArtReport.class, forVariable(variable), INITS);
    }

    public QArtReport(Path<? extends ArtReport> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QArtReport(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QArtReport(PathMetadata metadata, PathInits inits) {
        this(ArtReport.class, metadata, inits);
    }

    public QArtReport(Class<? extends ArtReport> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.art = inits.isInitialized("art") ? new plain.spring.art.domain.QArt(forProperty("art"), inits.get("art")) : null;
        this.reporter = inits.isInitialized("reporter") ? new plain.spring.user.domain.QUser(forProperty("reporter"), inits.get("reporter")) : null;
    }

}

