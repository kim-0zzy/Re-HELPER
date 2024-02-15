package Practice.ReHELPER.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash(value = "memberDTO", timeToLive = 60)
public class MemberDTO {
    @Id
    private Long id;
    private String nickName;
    private String username;
    private String role;
    private String address;
}
