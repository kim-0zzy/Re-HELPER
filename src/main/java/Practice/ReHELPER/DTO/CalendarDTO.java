package Practice.ReHELPER.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@RedisHash(value = "cacheCalender", timeToLive = 60)
public class CalendarDTO {
    @Id
    private String nickName;
    private LocalDate date;
    private Boolean progress;
}
