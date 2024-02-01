package Practice.ReHELPER.ExHandler;

import Practice.ReHELPER.Controller.Calendar.APICalendarController;
import Practice.ReHELPER.ExHandler.Form.ErrorForm;
import Practice.ReHELPER.Exception.NotLoggedInException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = APICalendarController.class)
public class APICalendarAdvice {
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(NotLoggedInException.class)
    public ErrorForm notLoggedInExHandler(NotLoggedInException e) {
        return new ErrorForm("UNAUTHORIZED", e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorForm exHandler() {
        return new ErrorForm("SERVER_ERROR", "서버 오류");
    }
}
