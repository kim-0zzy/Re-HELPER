package Practice.ReHELPER.Entity.Embedded;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MainPartition {
    private String mainBack;
    private String mainChest;
    private String mainShoulder;
    private String mainLeg;
    private String mainReps;
    private String mainSets;

    public MainPartition(String back, String chest, String shoulder, String leg, String reps, String sets) {
        this.mainBack = back;
        this.mainChest = chest;
        this.mainShoulder = shoulder;
        this.mainLeg = leg;
        this.mainReps = reps;
        this.mainSets = sets;
    }
}
