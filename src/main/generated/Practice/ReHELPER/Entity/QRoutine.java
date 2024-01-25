package Practice.ReHELPER.Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRoutine is a Querydsl query type for Routine
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRoutine extends EntityPathBase<Routine> {

    private static final long serialVersionUID = 1662144499L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRoutine routine = new QRoutine("routine");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final Practice.ReHELPER.Entity.Embedded.QMainPartition mainPartition;

    public final QMemberSpec memberSpec;

    public final Practice.ReHELPER.Entity.Embedded.QNutrition nutrition;

    public final Practice.ReHELPER.Entity.Embedded.QSubPartition subPartition;

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
        this.mainPartition = inits.isInitialized("mainPartition") ? new Practice.ReHELPER.Entity.Embedded.QMainPartition(forProperty("mainPartition")) : null;
        this.memberSpec = inits.isInitialized("memberSpec") ? new QMemberSpec(forProperty("memberSpec"), inits.get("memberSpec")) : null;
        this.nutrition = inits.isInitialized("nutrition") ? new Practice.ReHELPER.Entity.Embedded.QNutrition(forProperty("nutrition")) : null;
        this.subPartition = inits.isInitialized("subPartition") ? new Practice.ReHELPER.Entity.Embedded.QSubPartition(forProperty("subPartition")) : null;
    }

}

