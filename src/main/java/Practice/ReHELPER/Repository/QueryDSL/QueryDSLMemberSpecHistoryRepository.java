package Practice.ReHELPER.Repository.QueryDSL;

import Practice.ReHELPER.Entity.MemberSpecHistory;
import Practice.ReHELPER.Entity.QMemberSpecHistory;
import Practice.ReHELPER.Repository.MemberSpecHistoryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static Practice.ReHELPER.Entity.QMemberSpecHistory.memberSpecHistory;

@Repository
@RequiredArgsConstructor
@Primary
public class QueryDSLMemberSpecHistoryRepository implements MemberSpecHistoryRepository {

    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public void save(MemberSpecHistory memberSpecHistory) {
        em.persist(memberSpecHistory);
    }

    @Override
    public List<MemberSpecHistory> findByOwnerID(Long id) {
        return jpaQueryFactory
                .selectFrom(memberSpecHistory)
                .fetch();
    }

    @Override
    public MemberSpecHistory findFirst(Long id) {
        Optional<MemberSpecHistory> findHistory = jpaQueryFactory
                .selectFrom(memberSpecHistory)
                .where(memberSpecHistory.memberSpec.id.eq(id))
                .orderBy(memberSpecHistory.make_date_withTime.asc())
                .stream().findFirst();
        return findHistory.orElse(null);
    }
}