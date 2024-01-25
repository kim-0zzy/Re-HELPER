package Practice.ReHELPER.Entity.Embedded;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNutrition is a Querydsl query type for Nutrition
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QNutrition extends BeanPath<Nutrition> {

    private static final long serialVersionUID = -1700990331L;

    public static final QNutrition nutrition = new QNutrition("nutrition");

    public final NumberPath<Double> carbohydrate = createNumber("carbohydrate", Double.class);

    public final NumberPath<Double> fat = createNumber("fat", Double.class);

    public final NumberPath<Double> protein = createNumber("protein", Double.class);

    public QNutrition(String variable) {
        super(Nutrition.class, forVariable(variable));
    }

    public QNutrition(Path<? extends Nutrition> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNutrition(PathMetadata metadata) {
        super(Nutrition.class, metadata);
    }

}

