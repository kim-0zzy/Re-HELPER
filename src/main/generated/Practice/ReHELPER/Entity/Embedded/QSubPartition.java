package Practice.ReHELPER.Entity.Embedded;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSubPartition is a Querydsl query type for SubPartition
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QSubPartition extends BeanPath<SubPartition> {

    private static final long serialVersionUID = 804848253L;

    public static final QSubPartition subPartition = new QSubPartition("subPartition");

    public final StringPath subBack = createString("subBack");

    public final StringPath subChest = createString("subChest");

    public final StringPath subLeg = createString("subLeg");

    public final StringPath subReps = createString("subReps");

    public final StringPath subSets = createString("subSets");

    public final StringPath subShoulder = createString("subShoulder");

    public QSubPartition(String variable) {
        super(SubPartition.class, forVariable(variable));
    }

    public QSubPartition(Path<? extends SubPartition> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSubPartition(PathMetadata metadata) {
        super(SubPartition.class, metadata);
    }

}

