package Practice.ReHELPER.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    public Member(String realName, String username, String password) {
        this.realName = realName;
        this.username = username;
        this.password = password;
        this.role = "USER";
    }

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String realName;
    private String username;
    private String password;
    private String role;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "member")
    private MemberSpec memberSpec;

    public void setMemberSpec(MemberSpec memberSpec) {
        this.memberSpec = memberSpec;
    }
}
