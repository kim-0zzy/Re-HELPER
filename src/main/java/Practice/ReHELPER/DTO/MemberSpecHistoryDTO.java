package Practice.ReHELPER.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "memberSpecHistoryDTO", timeToLive = 60)
public class MemberSpecHistoryDTO {
    @Id
    private Long id;
    private LocalDateTime makeDateWithTime;
    private LocalDate makeDate;
    private int historyWeight;
    private int historyCareer;
}
