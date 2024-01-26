package Practice.ReHELPER.Repository;

import Practice.ReHELPER.Entity.Calendar;

import java.util.List;

public interface CalendarRepository {

    void save(Calendar calendar);
    List<Calendar> findAll();
    List<Calendar> findByOwnerId(Long id);
    List<Calendar> findByOwnerIdWithYear(Long id, int year);
    List<Calendar> findByOwnerIdWithYM(Long id, int year, int month);
    Calendar findByOwnerIdWithYMD(Long id, int year, int month, int day);
    Long delete(Long id, int year, int month, int day);
}
