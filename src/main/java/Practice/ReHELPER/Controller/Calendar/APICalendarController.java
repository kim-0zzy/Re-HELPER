package Practice.ReHELPER.Controller.Calendar;

import Practice.ReHELPER.Service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apiCalendar")
public class APICalendarController {

    private final CalendarService calendarService;


//    MessageResponseDTO messageResponseDTO = new MessageResponseDTO("Rental Success", HttpStatus.CREATED.value(),
//            responseBorrowDTOList);
//
//    HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(new MediaType("application", "json",StandardCharsets.UTF_8));
//
//        return new ResponseEntity<>(messageResponseDTO, httpHeaders, HttpStatus.OK);

}
