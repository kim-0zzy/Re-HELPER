package Practice.ReHELPER.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSpecHistory {

    @Id
    @GeneratedValue
    @Column(name = "histories_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "memberSpec_id")
    private MemberSpec memberSpec;


    private LocalDateTime makeDateWithTime;
    private LocalDate makeDate;
    private int historyWeight;
    private int historyCareer;

    public MemberSpecHistory(int his_weight, int his_career) {
        this.makeDate = LocalDate.now();
        this.makeDateWithTime = LocalDateTime.now();
        this.historyWeight = his_weight;
        this.historyCareer = his_career;
    }

    public void setHistory(int his_career, int his_weight){
        this.makeDate = LocalDate.now();
        this.makeDateWithTime = LocalDateTime.now();
        this.historyWeight = his_weight;
        this.historyCareer = his_career;
    }

    public void setMemberSpec(MemberSpec memberSpec) {
        this.memberSpec = memberSpec;
        memberSpec.getHistory().add(this);
    }

}
