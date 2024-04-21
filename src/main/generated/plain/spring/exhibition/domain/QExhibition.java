package plain.spring.exhibition.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExhibition is a Querydsl query type for Exhibition
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExhibition extends EntityPathBase<Exhibition> {

    private static final long serialVersionUID = 722615575L;

    public static final QExhibition exhibition = new QExhibition("exhibition");

    public final StringPath artCount = createString("artCount");

    public final ListPath<plain.spring.exhibitionartist.domain.ExhibitionArtist, plain.spring.exhibitionartist.domain.QExhibitionArtist> artists = this.<plain.spring.exhibitionartist.domain.ExhibitionArtist, plain.spring.exhibitionartist.domain.QExhibitionArtist>createList("artists", plain.spring.exhibitionartist.domain.ExhibitionArtist.class, plain.spring.exhibitionartist.domain.QExhibitionArtist.class, PathInits.DIRECT2);

    public final ListPath<plain.spring.exhibitionart.domain.ExhibitionArt, plain.spring.exhibitionart.domain.QExhibitionArt> arts = this.<plain.spring.exhibitionart.domain.ExhibitionArt, plain.spring.exhibitionart.domain.QExhibitionArt>createList("arts", plain.spring.exhibitionart.domain.ExhibitionArt.class, plain.spring.exhibitionart.domain.QExhibitionArt.class, PathInits.DIRECT2);

    public final StringPath backgroundColor = createString("backgroundColor");

    public final StringPath category = createString("category");

    public final NumberPath<Integer> commentCount = createNumber("commentCount", Integer.class);

    public final StringPath description = createString("description");

    public final StringPath englishName = createString("englishName");

    public final StringPath estimatedDuration = createString("estimatedDuration");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> likesCount = createNumber("likesCount", Integer.class);

    public final StringPath name = createString("name");

    public final StringPath posterImageUrl = createString("posterImageUrl");

    public final StringPath status = createString("status");

    public QExhibition(String variable) {
        super(Exhibition.class, forVariable(variable));
    }

    public QExhibition(Path<? extends Exhibition> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExhibition(PathMetadata metadata) {
        super(Exhibition.class, metadata);
    }

}

