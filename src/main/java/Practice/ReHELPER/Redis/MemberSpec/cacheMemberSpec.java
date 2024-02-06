package Practice.ReHELPER.Redis.MemberSpec;


import Practice.ReHELPER.Entity.E_type.Gender;
import Practice.ReHELPER.Entity.E_type.Goals;
import Practice.ReHELPER.Entity.E_type.Level;
import Practice.ReHELPER.Entity.Embedded.Routine;
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
@RedisHash(value = "cachedMemberSpec", timeToLive = 60)
public class cacheMemberSpec {

    @Id
    private String nickName;

    private Long originId;
    private Integer height;
    private Integer weight;
    private Integer waist;
    private Integer hip;
    private Double career;
    private Integer age;
    private Integer times;

    private Gender gender;
    private Goals goals;
    private Level level;
    private Routine routine;


}
