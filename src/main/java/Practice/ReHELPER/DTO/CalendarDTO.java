package Practice.ReHELPER.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "calendarDTO", timeToLive = 60)
public class CalendarDTO {
    @Id
    private Long id;
    private String nickName;
    private LocalDate date;
    private Boolean progress;
}
