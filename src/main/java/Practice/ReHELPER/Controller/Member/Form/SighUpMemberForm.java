package Practice.ReHELPER.Controller.Member.Form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SighUpMemberForm {
    private String nickName;
    private String username;
    private String password;
    private String address;

    public SighUpMemberForm(String nickName, String username, String password, String address) {
        this.nickName = nickName;
        this.username = username;
        this.password = password;
        this.address = address;
    }
}
