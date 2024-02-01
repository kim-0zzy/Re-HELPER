package Practice.ReHELPER.Controller.Member.Form;

import lombok.Data;

@Data
public class ChangePasswordForm {

    private String presentPassword;
    private String changePassword;

    public ChangePasswordForm(String presentPassword, String changePassword) {
        this.presentPassword = presentPassword;
        this.changePassword = changePassword;
    }
}
