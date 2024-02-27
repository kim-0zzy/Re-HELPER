package Practice.ReHELPER.Controller.Member.Form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordForm {
    private String presentPassword;
    private String changePassword;
}
