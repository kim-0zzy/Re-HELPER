package Practice.ReHELPER.Controller.Calendar.Form;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateCalendarForm {

//    @NotBlank(message = "반드시 입력해주십시오.")
//    @Min(value = )
//    @Max(value = 12)
//    private int year;

    @NotBlank(message = "반드시 입력해주십시오.")
    @Min(value = 1)
    @Max(value = 12)
    private int month;

    @NotBlank(message = "반드시 입력해주십시오.")
    @Min(value = 1)
    @Max(value = 31)
    private int day;
}
