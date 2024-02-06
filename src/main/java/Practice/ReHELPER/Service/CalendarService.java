package Practice.ReHELPER.Service;

import Practice.ReHELPER.DTO.CalendarDTO;
import Practice.ReHELPER.Entity.Calendar;
import Practice.ReHELPER.Entity.MemberSpec;
import jakarta.persistence.NoResultException;

import java.util.List;

public interface CalendarService {
    void saveProgress(Calendar calendar);
    List<Calendar> findAll();
    List<CalendarDTO> findMonthlyRecord(MemberSpec memberSpec, int year, int month);
    CalendarDTO findDateRecord(MemberSpec memberSpec, int year, int month, int day);
    Calendar createCalendarToday(MemberSpec memberSpec);
    Calendar createCalendarSelect(MemberSpec memberSpec,int year, int monty, int day);
    void deleteCalendarData(Long id, int year, int month, int day);
    CalendarDTO buildCalendar(Calendar calendar, String nickName);
}
