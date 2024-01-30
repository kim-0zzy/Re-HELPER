package Practice.ReHELPER.Controller.MemberSpec.Form;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateMemberSpecForm {

    @NotBlank(message = "반드시 입력해주십시오.")
    @Min(value = 135)
    private Integer height;

    @NotBlank(message = "반드시 입력해주십시오.")
    @Min(value = 35)
    private Integer weight;

    @NotBlank(message = "반드시 입력해주십시오.")
    @Min(value = 35)
    private Integer waist;

    @NotBlank(message = "반드시 입력해주십시오.")
    @Min(value = 45)
    private Integer hip;

    @NotBlank(message = "반드시 입력해주십시오.")
    @Min(value = 0)
    private Double career;

    @NotBlank(message = "반드시 입력해주십시오.")
    @Min(value = 13)
    private Integer age;

    @NotBlank(message = "반드시 입력해주십시오.")
    @Min(value = 0)
    private Integer times;

    @NotBlank(message = "반드시 입력해주십시오.")
    private String  gender;
    @NotBlank(message = "반드시 입력해주십시오.")
    private String goals;
}
