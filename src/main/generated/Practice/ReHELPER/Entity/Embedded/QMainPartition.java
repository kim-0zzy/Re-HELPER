package Practice.ReHELPER.Entity.Embedded;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMainPartition is a Querydsl query type for MainPartition
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QMainPartition extends BeanPath<MainPartition> {

    private static final long serialVersionUID = 989054366L;

    public static final QMainPartition mainPartition = new QMainPartition("mainPartition");

    public final StringPath mainBack = createString("mainBack");

    public final StringPath mainChest = createString("mainChest");

    public final StringPath mainLeg = createString("mainLeg");

    public final StringPath mainReps = createString("mainReps");

    public final StringPath mainSets = createString("mainSets");

    public final StringPath mainShoulder = createString("mainShoulder");

    public QMainPartition(String variable) {
        super(MainPartition.class, forVariable(variable));
    }

    public QMainPartition(Path<? extends MainPartition> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMainPartition(PathMetadata metadata) {
        super(MainPartition.class, metadata);
    }

}

