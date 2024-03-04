package Practice.ReHELPER.Repository.QueryDSL;

import Practice.ReHELPER.Entity.Calendar;
import Practice.ReHELPER.Repository.CalendarRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static Practice.ReHELPER.Entity.QCalendar.calendar;
import static Practice.ReHELPER.Entity.QMemberSpec.memberSpec;

@Repository
@RequiredArgsConstructor
@Primary

public class QueryDSLCalendarRepository implements CalendarRepository {

    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public void save(Calendar calendar) {
        em.persist(calendar);
    }

    @Override
    public List<Calendar> findAll() {
        return jpaQueryFactory
                .selectFrom(calendar)
                .fetch();
    }

    @Override
    public List<Calendar> findByOwnerId(Long id) {
        return jpaQueryFactory
                .selectFrom(calendar)
                .leftJoin(calendar.memberSpec)
                .fetchJoin()
                .where(calendar.memberSpec.id.eq(id))
                .fetch();
    }

    @Override
    public List<Calendar> findByOwnerIdWithYear(Long id, int year) {
        return jpaQueryFactory
                .selectFrom(calendar)
                .leftJoin(calendar.memberSpec)
                .fetchJoin()
                .where(calendar.memberSpec.id.eq(id).and(calendar.date.goe(LocalDate.of(year, 1, 1))))
                .fetch();
    }

    @Override
    public List<Calendar> findByOwnerIdWithYM(Long id, int year, int month) {
        return jpaQueryFactory
                .selectFrom(calendar)
                .leftJoin(calendar.memberSpec)
                .fetchJoin()
                .where(calendar.memberSpec.id.eq(id).and(calendar.date.goe(LocalDate.of(year, month, 1))))
                .fetch();
    }

    @Override
    public Calendar findByOwnerIdWithYMD(Long id, int year, int month, int day) {
        return jpaQueryFactory
                .selectFrom(calendar)
                .leftJoin(calendar.memberSpec)
                .fetchJoin()
                .where(calendar.memberSpec.id.eq(id).and(calendar.date.eq(LocalDate.of(year, month, day))))
                .fetchOne();
    }

    @Override
    public Long delete(Long id, int year, int month, int day) {
        return jpaQueryFactory
                .delete(calendar)
                .where(calendar.memberSpec.id.eq(id)
                        .and(calendar.date.eq(LocalDate.of(year, month, day))))
                .execute();
    }
}
