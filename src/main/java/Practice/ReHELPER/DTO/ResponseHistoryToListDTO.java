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
@RedisHash(value = "memberSpecHistoryDTO", timeToLive = 60)
public class ResponseHistoryToListDTO {
    @Id
    private Long id;
    private List<MemberSpecHistoryDTO> memberSpecHistoryDTOList;
}
