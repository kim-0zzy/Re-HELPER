package Practice.ReHELPER.Redis;

import Practice.ReHELPER.Entity.MemberSpec;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "cacheMember", timeToLive = 60)
public class cacheMember {

    @Id
    private Long id;
    private String nickName;
    private String username;
    private String password;
    private String role;
    private Long memberSpecID;

}
