package Practice.ReHELPER.Redis.Calendar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "cacheCalender", timeToLive = 60)
public class cacheCalendar {
    @Id
    private String nickName;
    private LocalDate date;
    private Boolean progress;
}
