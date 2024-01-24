package Practice.ReHELPER.Entity;

import Practice.ReHELPER.Entity.E_type.Gender;
import Practice.ReHELPER.Entity.E_type.Goals;
import Practice.ReHELPER.Entity.E_type.Level;
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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "routine_id")
    private Routine routine;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "memberSpec")
    private List<Calendar> calendar = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "memberSpec")
    private List<MemberSpecHistory> history = new ArrayList<>();

    private int height;
    private int weight;
    private int waist;
    private int hip;
    private int career;
    private int age;
    private int times;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private Goals goals;
    @Enumerated(EnumType.STRING)
    private Level level;
    public void setBasic(int height, int weight, int waist, int hip, int career, int age){
        this.height = height;
        this.weight = weight;
        this.waist = waist;
        this.hip = hip;
        this.career = career;
        this.age = age;
    }
    // 연관관계 메서드
    public void setMember(Member member){
        this.member = member;
        member.setMemberSpec(this);
    }

    public void setRoutine(Routine routine){
        this.routine = routine;
        routine.setMemberSpec(this);
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setGoals(Goals goals) {
        this.goals = goals;
    }

    public void setTimes(int times) {
        this.times = times;
    }
    public void addCareer(){
        double perWeek = 7.0 / this.getTimes();
        perWeek = Math.round(perWeek * 100.0) / 100.0;
        perWeek = perWeek * 100;
        perWeek = (int) perWeek;

        this.career += perWeek;
        int objCareer = this.getCareer();
        int subtract = objCareer - ((objCareer / 100) * 100);
        if (subtract >= 90){
            this.career += 1;
        }

    }
    public void decreaseCareer(){
        double perWeek = 7.0 / this.getTimes();
        perWeek = Math.round(perWeek * 100.0) / 100.0;
        perWeek = perWeek * 100;
        perWeek = (int) perWeek;

        this.career -= perWeek;
        int objCareer = this.getCareer();
        int subtract = objCareer - ((objCareer / 100) * 100);
        if (subtract == 1){
            this.career -= 1;
        }
    }

    public void setLevel(Level level){
        this.level = level;
    }

    public Level makeLevel(){
        int career = getCareer() / 100;
        Level level = null;
        if (career < 30){
            level = Level.UNTRAINED;
        } else if (career >= 30 && career < 90) {
            level = Level.BEGINNER;
        } else if (career >= 90 && career < 180) {
            level = Level.NOVICE;
        } else if (career > 180) {
            level = Level.INTERMEDIATE;
        }
        return level;
    }

    public MemberSpec(Member member,
                      int height, int weight, int waist, int hip, int career, int age, int times,
                      Gender gender, Goals goals) {
        this.member = member;
        this.height = height;
        this.weight = weight;
        this.waist = waist;
        this.hip = hip;
        this.career = career;
        this.age = age;
        this.times = times;
        this.gender = gender;
        this.goals = goals;
    }
}
