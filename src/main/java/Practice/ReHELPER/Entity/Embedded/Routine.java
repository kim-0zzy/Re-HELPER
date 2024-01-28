package Practice.ReHELPER.Entity.Embedded;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Routine {

    @Embedded
    private MainPartition mainPartition;
    @Embedded
    private SubPartition subPartition;
    @Embedded
    private Nutrition nutrition;
    private Double BMR;

    public Routine(MainPartition mainPartition, SubPartition subPartition, Nutrition nutrition, Double BMR) {
        this.mainPartition = mainPartition;
        this.subPartition = subPartition;
        this.nutrition = nutrition;
        this.BMR = BMR;
    }

}
