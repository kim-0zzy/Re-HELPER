package Practice.ReHELPER.Repository;

import Practice.ReHELPER.Entity.Calendar;

import java.util.List;

public interface CalendarRepository {

    void save(Calendar calendar);
    List findAll();
    List findByOwnerId(Long id);
    List findByOwnerIdWithYear(Long id, int year);
    List findByOwnerIdWithYM(Long id, int year, int month);
    Calendar findByOwnerIdWithYMD(Long id, int year, int month, int day);
    int delete(Long id, int year, int month, int day);
}
