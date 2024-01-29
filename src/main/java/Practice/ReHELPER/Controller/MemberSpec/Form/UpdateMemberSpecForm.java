package Practice.ReHELPER.Controller.MemberSpec.Form;

import Practice.ReHELPER.Entity.E_type.Gender;
import Practice.ReHELPER.Entity.E_type.Goals;
import lombok.Data;

@Data
public class UpdateMemberSpecForm {

    private Integer height;
    private Integer weight;
    private Integer waist;
    private Integer hip;
    private Integer age;
    private Integer times;
    private Double career;
    private Gender gender;
    private Goals goals;
}
