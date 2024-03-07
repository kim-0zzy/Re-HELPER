package Practice.ReHELPER.Service.Impl;


import Practice.ReHELPER.DTO.CalendarDTO;
import Practice.ReHELPER.DTO.ResponseCalendarDTO;
import Practice.ReHELPER.Entity.Calendar;
import Practice.ReHELPER.Entity.MemberSpec;
import Practice.ReHELPER.Redis.Repository.CalendarDTORedisRepository;
import Practice.ReHELPER.Repository.CalendarRepository;
import Practice.ReHELPER.Service.CalendarService;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CalendarServiceImpl implements CalendarService {

    private final CalendarRepository calendarRepository;
    private final CalendarDTORedisRepository calendarDTORedisRepository;

    @Override
    public void saveProgress(Calendar calendar) {
        calendarRepository.save(calendar);
    }

    @Override
    public List<Calendar> findAll() {
        return calendarRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<CalendarDTO> findRecently2MonthRecord(Long id, String nickName, int year, int month){
        Optional<ResponseCalendarDTO> redisCalendarDTO = calendarDTORedisRepository.findById(id);
        if (redisCalendarDTO.isPresent()) {
            return redisCalendarDTO.get().getCalendarDTOList();
        }

        List<CalendarDTO> calendarDTOList = new ArrayList<>();

//        List<Calendar> thisMonth = calendarRepository.findByOwnerIdWithYM(id, year, month);
        List<Calendar> lastMonth;

        if (month == 1) {
            lastMonth = calendarRepository.findByOwnerIdWithYM(id, (LocalDate.now().getYear() - 1), 12);
        } else {
            lastMonth = calendarRepository.findByOwnerIdWithYM(id, LocalDate.now().getYear(), LocalDate.now().getMonthValue() - 1);
        }

        for (Calendar calendar : lastMonth) {
            calendarDTOList.add(buildCalendar(calendar, nickName));
        }
//        for (Calendar calendar : thisMonth) {
//            calendarDTOList.add(buildCalendar(calendar, nickName));
//        }

        calendarDTORedisRepository.save(ResponseCalendarDTO.builder()
                .id(id)
                .calendarDTOList(calendarDTOList)
                .build());

        return calendarDTOList;
    }

    @Override
    public CalendarDTO findDateRecord(MemberSpec memberSpec, int year, int month, int day){
        Calendar calendar = calendarRepository.findByOwnerIdWithYMD(memberSpec.getId(), year, month, day);
        return buildCalendar(calendar, memberSpec.getMember().getNickName());
    }

    @Override
    public Calendar createCalendarToday(MemberSpec memberSpec) {
        calendarDTORedisRepository.deleteById(memberSpec.getId());
        Calendar calendar = new Calendar(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        calendar.setMemberSpec(memberSpec);
        return calendar;
    }

    @Override
    public Calendar createCalendarSelect(MemberSpec memberSpec,int year, int month ,int day) {
        calendarDTORedisRepository.deleteById(memberSpec.getId());
        Calendar calendar = new Calendar(year, month, day);
        calendar.setMemberSpec(memberSpec);
        return calendar;
    }

    @Override
    public void deleteCalendarData(Long id, CalendarDTO calendarDTO) throws NoResultException {
        calendarDTORedisRepository.deleteById(id);
        calendarRepository.delete(id, calendarDTO.getDate().getYear(), calendarDTO.getDate().getDayOfMonth(), calendarDTO.getDate().getMonthValue());
    }

    @Override
    public CalendarDTO buildCalendar(Calendar calendar, String nickName) {
        return CalendarDTO.builder()
                .id(calendar.getId())
                .nickName(nickName)
                .date(calendar.getDate())
                .progress(calendar.getProgress())
                .build();
    }

}
