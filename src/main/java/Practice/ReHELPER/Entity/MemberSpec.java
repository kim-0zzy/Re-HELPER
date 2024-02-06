package Practice.ReHELPER.Entity;

import Practice.ReHELPER.Entity.E_type.Gender;
import Practice.ReHELPER.Entity.E_type.Goals;
import Practice.ReHELPER.Entity.E_type.Level;
import Practice.ReHELPER.Entity.Embedded.Routine;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSpec {

    @Id
    @GeneratedValue
    @Column(name = "memberSpec_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "memberSpec")
    private final List<Calendar> calendar = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "memberSpec")
    private final List<MemberSpecHistory> history = new ArrayList<>();

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

    public void setBasicInfo(Integer weight, Integer waist, Integer hip, Integer age){
        this.weight = weight;
        this.waist = waist;
        this.hip = hip;
        this.age = age;
    }
    public void setMember(Member member){
        this.member = member;
        member.setMemberSpec(this);
    }
    public void setRoutine(Routine routine) {
        this.routine = routine;
    }
    public void setGender(String gender) {
        this.gender = switch (gender) {
            case "female" -> Gender.FEMALE;
            default -> Gender.MALE;
        };
    }
    public void setGoals(String goal) {
        this.goals = switch (goal) {
            case "bulk" -> Goals.BULK;
            case "end" -> Goals.ENDURE;
            case "str" -> Goals.STRENGTH;
            default -> Goals.DIET;
        };
    }
    public void setTimes(int times) {
        this.times = times;
    }
    public void setCareer(Double career) {
        this.career = career;
    }
    public void setLevel(Level level){
        this.level = level;
    }
    public void setAttendanceChecker(Integer attendanceChecker) {
        this.attendanceChecker = attendanceChecker;
    }
    public MemberSpec(Integer height, Integer weight, Integer waist,
                      Integer hip, Double career, Integer age, Integer times,
                      Gender gender, Goals goals) {
        this.height = height;
        this.weight = weight;
        this.waist = waist;
        this.hip = hip;
        this.career = career;
        this.age = age;
        this.times = times;
        this.gender = gender;
        this.goals = goals;
        this.attendanceChecker = 0;
    }
}
