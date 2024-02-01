package Practice.ReHELPER.Service.Impl;


import Practice.ReHELPER.DTO.CalendarDTO;
import Practice.ReHELPER.Entity.Calendar;
import Practice.ReHELPER.Entity.MemberSpec;
import Practice.ReHELPER.Repository.CalendarRepository;
import Practice.ReHELPER.Service.CalendarService;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CalendarServiceImpl implements CalendarService {

    private final CalendarRepository calendarRepository;
    @Override
    public void saveProgress(Calendar calendar) {
        calendarRepository.save(calendar);
    }

    @Override
    public List<Calendar> findAll() {
        return calendarRepository.findAll();
    }

    @Override
    public List<Calendar> findAllRecord(Long id){
        return calendarRepository.findByOwnerId(id);
    }

    @Override
    public List<Calendar> findAnnualRecord(Long id, int year){
        return calendarRepository.findByOwnerIdWithYear(id,year);
    }

    @Override
    public List<Calendar> findMonthlyRecord(Long id, int year, int month){
        return calendarRepository.findByOwnerIdWithYM(id,year,month);
    }

    @Override
    public Calendar findDateRecord(Long id, int year, int month, int day){
        return calendarRepository.findByOwnerIdWithYMD(id, year, month, day);
    }

    @Override
    public Calendar createCalendarToday(MemberSpec memberSpec) {
        Calendar calendar = new Calendar(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        calendar.setMemberSpec(memberSpec);
        return calendar;
    }

    @Override
    public Calendar createCalendarSelect(MemberSpec memberSpec,int year, int month ,int day) {
        Calendar calendar = new Calendar(year, month, day);
        calendar.setMemberSpec(memberSpec);
        return calendar;
    }

    @Override
    public void deleteCalendarData(Long id, int year, int month, int day) throws NoResultException {
        calendarRepository.delete(id, year, month, day);
    }

    @Override
    public CalendarDTO buildCalendar(Calendar calendar, String nickName) {
        return CalendarDTO.builder()
                .nickName(nickName)
                .date(calendar.getDate())
                .progress(calendar.getProgress())
                .build();
    }

}
