package Practice.ReHELPER.Controller.Calendar;

import Practice.ReHELPER.Config.LoggedMemberHolder;
import Practice.ReHELPER.Controller.Calendar.Form.CreateCalendarForm;
import Practice.ReHELPER.DTO.CalendarDTO;
import Practice.ReHELPER.DTO.MessageResponseDTO;
import Practice.ReHELPER.Entity.Calendar;
import Practice.ReHELPER.Entity.Member;
import Practice.ReHELPER.Entity.MemberSpec;
import Practice.ReHELPER.Exception.NotFoundResultException;
import Practice.ReHELPER.Exception.NotLoggedInException;
import Practice.ReHELPER.Service.CalendarService;
import Practice.ReHELPER.Service.MemberService;
import Practice.ReHELPER.Service.MemberSpecService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apiCalendar")
public class APICalendarController {

    private final CalendarService calendarService;
    private final MemberSpecService memberSpecService;
    private final LoggedMemberHolder loggedMemberHolder;

    public Member loadLoginMember() throws NotLoggedInException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            throw new NotLoggedInException("Not Logged in Yet");
        }
        return loggedMemberHolder.getLoggedMember().get(authentication.getName());
    }
    @PostMapping("/Today")
    public ResponseEntity<MessageResponseDTO> saveTodayProgress() throws NotLoggedInException {
        MemberSpec memberSpec = memberSpecService.findMemberSpecByMemberId(loadLoginMember().getId());

        Calendar calendar = calendarService.createCalendarToday(memberSpec);
        calendarService.saveProgress(calendar);
        memberSpecService.increaseCareer(memberSpec);

        CalendarDTO calendarDTO = calendarService.buildCalendar(calendar, memberSpec.getMember().getNickName());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                new MessageResponseDTO("Save Progress Success", HttpStatus.CREATED.value(), calendarDTO)
                , httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/Select")
    public ResponseEntity<MessageResponseDTO> saveSelectProgress(@Valid @RequestBody CreateCalendarForm createCalendarForm) throws NotLoggedInException {
        MemberSpec memberSpec = memberSpecService.findMemberSpecByMemberId(loadLoginMember().getId());

        Calendar calendar = calendarService.createCalendarSelect(memberSpec,
                createCalendarForm.getYear(), createCalendarForm.getMonth(), createCalendarForm.getDay());
        calendarService.saveProgress(calendar);
        memberSpecService.increaseCareer(memberSpec);


        CalendarDTO calendarDTO = calendarService.buildCalendar(calendar, memberSpec.getMember().getNickName());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                new MessageResponseDTO("Save Progress Success", HttpStatus.CREATED.value(), calendarDTO),
                httpHeaders, HttpStatus.OK);
    }

    @DeleteMapping("/records/{date}")
    public ResponseEntity<MessageResponseDTO> deleteRecord(@PathVariable String date) throws NotLoggedInException, NotFoundResultException {
        MemberSpec memberSpec = memberSpecService.findMemberSpecByMemberId(loadLoginMember().getId());
        LocalDate deleteDate = LocalDate.parse(date);

        CalendarDTO calendarDTO = calendarService.findDateRecord(memberSpec, deleteDate.getYear(), deleteDate.getMonthValue(), deleteDate.getDayOfMonth());
        if (calendarDTO == null) {
            throw new NotFoundResultException("Data is not Exist");
        }

        calendarService.deleteCalendarData(memberSpec.getId(), calendarDTO);
        memberSpecService.decreaseCareer(memberSpec);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                new MessageResponseDTO("Delete Success", HttpStatus.OK.value(), calendarDTO)
                , httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/recentlyProgress")
    public ResponseEntity<MessageResponseDTO> recent2MonthRecord() throws NotLoggedInException {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        MemberSpec memberSpec = memberSpecService.findMemberSpecByMemberId(loadLoginMember().getId());
        List<CalendarDTO> result = calendarService.findRecently2MonthRecord(
                memberSpec.getId(), memberSpec.getMember().getNickName(), LocalDate.now().getYear(), LocalDate.now().getMonthValue());

        return new ResponseEntity<>(
                new MessageResponseDTO("Find Success", HttpStatus.OK.value(), result)
                , httpHeaders, HttpStatus.OK);
    }
}
