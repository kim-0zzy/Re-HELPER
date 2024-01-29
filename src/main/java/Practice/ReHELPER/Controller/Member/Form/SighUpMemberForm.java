package Practice.ReHELPER.Controller.Member.Form;

import lombok.Data;

@Data
public class SighUpMemberForm {
    private String nickName;
    private String username;
    private String password;

    public SighUpMemberForm(String nickName, String username, String password) {
        this.nickName = nickName;
        this.username = username;
        this.password = password;
    }
}
