package Practice.ReHELPER.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberSpecHistoryDTO {
    private LocalDateTime makeDateWithTime;
    private LocalDate makeDate;
    private int historyWeight;
    private int historyCareer;
}
