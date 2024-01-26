package Practice.ReHELPER.Service;

import Practice.ReHELPER.DTO.CalendarDTO;
import Practice.ReHELPER.Entity.Calendar;
import Practice.ReHELPER.Entity.MemberSpec;
import jakarta.persistence.NoResultException;

import java.util.List;

public interface CalendarService {
    void saveProgress(Calendar calendar);
    List<Calendar> findAll();
    List<Calendar> findAllRecord(Long id);
    List<Calendar> findAnnualRecord(Long id, int year);
    List<Calendar> findMonthlyRecord(Long id, int year, int month);
    Calendar findDateRecord(Long id, int year, int month, int day);
    Calendar createCalendar_today(MemberSpec memberSpec);
    Calendar createCalendar_select(MemberSpec memberSpec, int day);
    void deleteCalendarData(Long id, int year, int month, int day);
    CalendarDTO buildCalendar(Calendar calendar);
}
