package Practice.ReHELPER.Repository.QueryDSL;

import Practice.ReHELPER.Entity.MemberSpecHistory;
import Practice.ReHELPER.Repository.MemberSpecHistoryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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
                .where(memberSpecHistory.memberSpec.id.eq(id))
                .orderBy(memberSpecHistory.makeDateWithTime.asc())
                .fetch();
    }

    @Override
    public MemberSpecHistory findFirst(Long id) {
        return jpaQueryFactory
                .selectFrom(memberSpecHistory)
                .where(memberSpecHistory.memberSpec.member.id.eq(id))
                .orderBy(memberSpecHistory.makeDateWithTime.asc())
                .stream().findFirst().orElse(null);
    }

    @Override
    public Long deleteAllByOwnerID(Long id) {
        return jpaQueryFactory.delete(memberSpecHistory)
                .where(memberSpecHistory.memberSpec.member.id.eq(id))
                .execute();
    }
}
