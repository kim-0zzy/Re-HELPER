package Practice.ReHELPER.Exception.ExHandler;

import Practice.ReHELPER.Controller.Member.APIMemberController;
import Practice.ReHELPER.Exception.ExHandler.Form.ErrorForm;
import Practice.ReHELPER.Exception.ExistMemberException;
import Practice.ReHELPER.Exception.NotFoundResultException;
import Practice.ReHELPER.Exception.NotLoggedInException;
import Practice.ReHELPER.Exception.PasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = APIMemberController.class)
public class APIMemberAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ExistMemberException.class)
    public ErrorForm existMemberExHandler(ExistMemberException e) {
        return new ErrorForm("BAD_REQUEST", e.getMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotFoundResultException.class)
    public ErrorForm notFoundResultExHandler(NotFoundResultException e) {
        return new ErrorForm("BAD_REQUEST", e.getMessage());
    }
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(NotLoggedInException.class)
    public ErrorForm notLoggedInExHandler(NotLoggedInException e) {
        return new ErrorForm("UNAUTHORIZED", e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PasswordException.class)
    public ErrorForm passwordExHandler(PasswordException e) {
        return new ErrorForm("BAD_REQUEST", e.getMessage());
    }

}
