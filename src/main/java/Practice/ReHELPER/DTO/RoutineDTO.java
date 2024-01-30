package Practice.ReHELPER.DTO;

import Practice.ReHELPER.Entity.Embedded.MainPartition;
import Practice.ReHELPER.Entity.Embedded.Nutrition;
import Practice.ReHELPER.Entity.Embedded.SubPartition;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RoutineDTO {
    private MainPartition mainPartition;
    private SubPartition subPartition;
    private Nutrition nutrition;
    private Double BMR;
}
