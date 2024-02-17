package Practice.ReHELPER.Controller.MemberSpec.Form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMemberSpecForm {

    @NotNull(message = "반드시 입력해주십시오.")
    @Min(value = 135)
    private Integer height;

    @NotNull(message = "반드시 입력해주십시오.")
    @Min(value = 35)
    private Integer weight;

    @NotNull(message = "반드시 입력해주십시오.")
    @Min(value = 35)
    private Integer waist;

    @NotNull(message = "반드시 입력해주십시오.")
    @Min(value = 45)
    private Integer hip;

    @NotNull(message = "반드시 입력해주십시오.")
    @Min(value = 0)
    private Double career;

    @NotNull(message = "반드시 입력해주십시오.")
    @Min(value = 13)
    private Integer age;

    @NotNull(message = "반드시 입력해주십시오.")
    @Min(value = 0)
    private Integer times;

    @NotBlank(message = "반드시 입력해주십시오.")
    private String  gender;
    @NotBlank(message = "반드시 입력해주십시오.")
    private String goals;
}
