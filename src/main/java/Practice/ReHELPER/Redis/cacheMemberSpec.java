package Practice.ReHELPER.Redis;


import Practice.ReHELPER.Entity.E_type.Gender;
import Practice.ReHELPER.Entity.E_type.Goals;
import Practice.ReHELPER.Entity.E_type.Level;
import Practice.ReHELPER.Entity.Embedded.Routine;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@RedisHash(value = "cacheMemberSpec", timeToLive = 60)
public class cacheMemberSpec {

    @Id
    private String id;
    private Integer height;
    private Integer weight;
    private Integer waist;
    private Integer hip;
    private Double career;
    private Integer age;
    private Integer times;
    private Integer attendanceChecker;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private Goals goals;
    @Enumerated(EnumType.STRING)
    private Level level;
    @Embedded
    private Routine routine;
    private String memberID;

}
