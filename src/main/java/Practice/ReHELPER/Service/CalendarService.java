package Practice.ReHELPER.Service;

import Practice.ReHELPER.Entity.Calendar;
import Practice.ReHELPER.Entity.MemberSpec;
import jakarta.persistence.NoResultException;

import java.util.List;

public interface CalendarService {
    public Long saveProgress(Calendar calendar);
    public List findAll() throws NoResultException;
    public List<Calendar> findAllRecord(Long id) throws NoResultException;
    public List<Calendar> findAnnualRecord(Long id, int year) throws NoResultException;
    public List<Calendar> findMonthlyRecord(Long id, int year, int month) throws NoResultException;
    public Calendar findDateRecord(Long id, int year, int month, int day) throws NoResultException;
    public Calendar createCalendar_today(MemberSpec memberSpec);
    public Calendar createCalendar(MemberSpec memberSpec, int day);
    public void deleteCalendarData(Long id, int year, int month, int day) throws NoResultException;

}
