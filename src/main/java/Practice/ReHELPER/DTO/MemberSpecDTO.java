package Practice.ReHELPER.DTO;

import Practice.ReHELPER.Entity.Embedded.Routine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "memberSpecDTO", timeToLive = 60)
public class MemberSpecDTO {
    @Id
    private Long id;
    private String nickName;
    private Integer height;
    private Integer weight;
    private Integer waist;
    private Integer hip;
    private Double career;
    private Integer age;
    private Integer times;

    private String gender;
    private String goals;
    private String level;
    private Routine routine;
}
