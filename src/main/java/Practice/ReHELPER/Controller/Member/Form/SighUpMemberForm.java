package Practice.ReHELPER.Controller.Member.Form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SighUpMemberForm {
    private String nickName;
    private String username;
    private String password;
    private String address;

}
