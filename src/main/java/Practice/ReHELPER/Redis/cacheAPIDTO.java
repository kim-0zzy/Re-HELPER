package Practice.ReHELPER.Redis;

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
@RedisHash(value = "cacheAPI", timeToLive = 60)
public class cacheAPIDTO {
    @Id
    private String id;
    private Object dto;
}
