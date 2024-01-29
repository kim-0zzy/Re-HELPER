package Practice.ReHELPER.DTO;

import Practice.ReHELPER.Entity.Embedded.Routine;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberSpecDTO {
    private Integer height;
    private Integer weight;
    private Integer waist;
    private Integer hip;
    private Double career;
    private Integer age;
    private Integer times;

    private String gender;
    private String goals;
    private String level;
    private Routine routine;
}
