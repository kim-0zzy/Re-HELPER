package Practice.ReHELPER.Controller.MemberSpec.Form;

import Practice.ReHELPER.Entity.E_type.Gender;
import Practice.ReHELPER.Entity.E_type.Goals;
import lombok.Data;

@Data
public class UpdateMemberSpecForm {

    private int height;
    private int weight;
    private int waist;
    private int hip;
    private int career;
    private int age;
    private int times;
    private Gender gender;
    private Goals goals;
}
