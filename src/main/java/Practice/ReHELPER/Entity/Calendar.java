package Practice.ReHELPER.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Calendar {

    @Id @GeneratedValue
    @Column(name = "calendar_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "memberSpec_id")
    private MemberSpec memberSpec;

    private LocalDate date;
    private Boolean progress;

    public void setMemberSpec(MemberSpec memberSpec) {
        this.memberSpec = memberSpec;
        memberSpec.getCalendar().add(this);
    }
    public Calendar(int year, int month, int day) {
        this.date = LocalDate.of(year, month, day);
        this.progress = Boolean.TRUE;
    }

}