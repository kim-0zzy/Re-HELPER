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
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
            memberSpecService.decreaseCareer(memberSpec);
            // 경고 메시지 넣을까?
        }

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

    @PostMapping("/saveSelect")
    public ResponseEntity<MessageResponseDTO> saveSelectProgress(@Valid @RequestBody CreateCalendarForm createCalendarForm) throws NotLoggedInException {
        MemberSpec memberSpec = memberSpecService.findMemberSpecByMemberId(loadLoginMember());

        if (calendarService.findDateRecord(memberSpec.getId(), LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()) == null) {
            calendarService.deleteCalendarData(memberSpec.getId(), LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
            memberSpecService.decreaseCareer(memberSpec);
            // 경고 메시지 넣을까?
        }

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

    @GetMapping("/recentlyProgress")
    public ResponseEntity<MessageResponseDTO> recent2MonthRecord() throws NotLoggedInException {
        MemberSpec memberSpec = memberSpecService.findMemberSpecByMemberId(loadLoginMember());
        String nickName = memberSpec.getMember().getNickName();

        List<Calendar> thisMonth = calendarService.findMonthlyRecord(memberSpec.getId(), LocalDate.now().getYear(), LocalDate.now().getMonthValue());
        List<Calendar> lastMonth = new ArrayList<>();
        if (LocalDate.now().getMonthValue() == 1) {
            lastMonth = calendarService.findMonthlyRecord(memberSpec.getId(), LocalDate.now().getYear() - 1, 12);
        } else {
            lastMonth = calendarService.findMonthlyRecord(memberSpec.getId(), LocalDate.now().getYear(), LocalDate.now().getMonthValue() - 1);
        }

        List<CalendarDTO> result = new ArrayList<>();

        for (Calendar calendar : lastMonth) {
            result.add(calendarService.buildCalendar(calendar, nickName));
        }
        for (Calendar calendar : thisMonth) {
            result.add(calendarService.buildCalendar(calendar, nickName));
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                new MessageResponseDTO("Find Success", HttpStatus.OK.value(), result)
                , httpHeaders, HttpStatus.OK);
    }





}
