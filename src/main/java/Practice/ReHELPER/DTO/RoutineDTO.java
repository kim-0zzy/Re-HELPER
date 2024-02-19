package Practice.ReHELPER.DTO;

import Practice.ReHELPER.Entity.Embedded.MainPartition;
import Practice.ReHELPER.Entity.Embedded.Nutrition;
import Practice.ReHELPER.Entity.Embedded.SubPartition;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoutineDTO {
    private String nickName;
    private MainPartition mainPartition;
    private SubPartition subPartition;
    private Nutrition nutrition;
    private Double BMR;
}
