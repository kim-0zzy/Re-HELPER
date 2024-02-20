package Practice.ReHELPER.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "calendarDTO", timeToLive = 60)
public class ResponseCalendarDTO {
    @Id
    private Long id;
    private List<CalendarDTO> calendarDTOList;
}
