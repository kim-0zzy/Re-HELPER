package Practice.ReHELPER.Controller.Calendar.Form;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCalendarForm {

    @NotNull(message = "반드시 입력해주십시오.")
    private Integer year;

    @NotNull(message = "반드시 입력해주십시오.")
    @Min(value = 1)
    @Max(value = 12)
    private Integer month;

    @NotNull(message = "반드시 입력해주십시오.")
    @Min(value = 1)
    @Max(value = 31)
    private Integer day;
}
