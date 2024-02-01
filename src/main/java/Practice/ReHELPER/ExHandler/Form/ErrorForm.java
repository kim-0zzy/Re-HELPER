package Practice.ReHELPER.ExHandler.Form;

import lombok.Data;

@Data
public class ErrorForm {

    public String code;
    public String message;

    public ErrorForm(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
