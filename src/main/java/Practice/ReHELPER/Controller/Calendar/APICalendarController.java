package Practice.ReHELPER.Controller.Calendar;

import Practice.ReHELPER.Controller.Calendar.Form.CreateCalendarForm;
import Practice.ReHELPER.DTO.CalendarDTO;
import Practice.ReHELPER.DTO.MessageResponseDTO;
import Practice.ReHELPER.Entity.Calendar;
import Practice.ReHELPER.Entity.Member;
import Practice.ReHELPER.Entity.MemberSpec;
import Practice.ReHELPER.Exception.NotLoggedInException;
import Practice.ReHELPER.Service.CalendarService;
import Practice.ReHELPER.Service.MemberSpecService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apiCalendar")
public class APICalendarController {

    private final CalendarService calendarService;
    private final MemberSpecService memberSpecService;

    public Long loadLoginMember() throws NotLoggedInException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            throw new NotLoggedInException("Not Yet Logged in");
        }
        Member member = (Member) authentication.getPrincipal();
        return member.getId();
    }
    @PostMapping("/saveToday")
    public ResponseEntity<MessageResponseDTO> saveTodayProgress() throws NotLoggedInException {
        MemberSpec memberSpec = memberSpecService.findMemberSpecByMemberId(loadLoginMember());

        if (calendarService.findDateRecord(memberSpec.getId(), LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()) == null) {
            calendarService.deleteCalendarData(memberSpec.getId(), LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
            // 경고 메시지 넣을까?
        }

        Calendar calendar = calendarService.createCalendarToday(memberSpec);
        calendarService.saveProgress(calendar);

        CalendarDTO calendarDTO = calendarService.buildCalendar(calendar, memberSpec.getMember().getNickName());

        MessageResponseDTO messageResponseDTO = new MessageResponseDTO("Rental Success", HttpStatus.CREATED.value(),
                calendarDTO);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(messageResponseDTO, httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/saveSelect")
    public ResponseEntity<MessageResponseDTO> saveSelectProgress(@Valid @RequestBody CreateCalendarForm createCalendarForm) throws NotLoggedInException {
        MemberSpec memberSpec = memberSpecService.findMemberSpecByMemberId(loadLoginMember());

        if (calendarService.findDateRecord(memberSpec.getId(), LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()) == null) {
            calendarService.deleteCalendarData(memberSpec.getId(), LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
            // 경고 메시지 넣을까?
        }
    }





}
