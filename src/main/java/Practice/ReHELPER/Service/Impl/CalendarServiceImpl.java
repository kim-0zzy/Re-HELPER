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
import java.util.ArrayList;
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
    public List<CalendarDTO> findRecently2MonthRecord(MemberSpec memberSpec, int year, int month){
        List<CalendarDTO> calendarDTOList = new ArrayList<>();

        List<Calendar> thisMonth = calendarRepository.findByOwnerIdWithYM(memberSpec.getId(), year, month);
        List<Calendar> lastMonth;

        if (LocalDate.now().getMonthValue() == 1) {
            lastMonth = calendarRepository.findByOwnerIdWithYM(memberSpec.getId(), (LocalDate.now().getYear() - 1), 12);
        } else {
            lastMonth = calendarRepository.findByOwnerIdWithYM(memberSpec.getId(), LocalDate.now().getYear(), LocalDate.now().getMonthValue() - 1);
        }

        for (Calendar calendar : lastMonth) {
            calendarDTOList.add(buildCalendar(calendar, memberSpec.getMember().getNickName()));
        }
        for (Calendar calendar : thisMonth) {
            calendarDTOList.add(buildCalendar(calendar, memberSpec.getMember().getNickName()));
        }


        return calendarDTOList;
    }

    @Override
    public CalendarDTO findDateRecord(MemberSpec memberSpec, int year, int month, int day){
        Calendar calendar = calendarRepository.findByOwnerIdWithYMD(memberSpec.getId(), year, month, day);
        return buildCalendar(calendar, memberSpec.getMember().getNickName());
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
    public void deleteCalendarData(Long id, CalendarDTO calendarDTO) throws NoResultException {
        calendarRepository.delete(id, calendarDTO.getDate().getYear(), calendarDTO.getDate().getDayOfMonth(), calendarDTO.getDate().getMonthValue());
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
