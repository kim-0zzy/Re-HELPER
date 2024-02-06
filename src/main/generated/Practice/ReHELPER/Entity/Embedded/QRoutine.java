package Practice.ReHELPER.Entity.Embedded;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRoutine is a Querydsl query type for Routine
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QRoutine extends BeanPath<Routine> {

    private static final long serialVersionUID = -349917455L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRoutine routine = new QRoutine("routine");

    public final NumberPath<Double> BMR = createNumber("BMR", Double.class);

    public final QMainPartition mainPartition;

    public final QNutrition nutrition;

    public final QSubPartition subPartition;

    public QRoutine(String variable) {
        this(Routine.class, forVariable(variable), INITS);
    }

    public QRoutine(Path<? extends Routine> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRoutine(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRoutine(PathMetadata metadata, PathInits inits) {
        this(Routine.class, metadata, inits);
    }

    public QRoutine(Class<? extends Routine> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.mainPartition = inits.isInitialized("mainPartition") ? new QMainPartition(forProperty("mainPartition")) : null;
        this.nutrition = inits.isInitialized("nutrition") ? new QNutrition(forProperty("nutrition")) : null;
        this.subPartition = inits.isInitialized("subPartition") ? new QSubPartition(forProperty("subPartition")) : null;
    }

}

