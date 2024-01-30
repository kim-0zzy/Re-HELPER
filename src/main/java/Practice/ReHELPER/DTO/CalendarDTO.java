package Practice.ReHELPER.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class CalendarDTO {
    private String nickName;
    private LocalDate date;
    private Boolean progress;
}
