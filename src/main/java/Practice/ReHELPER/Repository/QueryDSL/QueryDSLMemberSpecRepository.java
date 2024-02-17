package Practice.ReHELPER.Repository.QueryDSL;

import Practice.ReHELPER.Entity.MemberSpec;
import Practice.ReHELPER.Repository.MemberSpecRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

import static Practice.ReHELPER.Entity.QMemberSpec.memberSpec;

@Repository
@RequiredArgsConstructor
@Primary

public class QueryDSLMemberSpecRepository implements MemberSpecRepository {

    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public void save(MemberSpec memberSpec) {
        em.persist(memberSpec);
    }

    @Override
    public MemberSpec findById(Long id) {
        return em.find(MemberSpec.class, id);
    }

    @Override
    public List<MemberSpec> findAll() {
        return jpaQueryFactory
                .selectFrom(memberSpec)
                .fetch();
    }

    @Override
    public MemberSpec findByMemberId(Long id) {
        return jpaQueryFactory
                .selectFrom(memberSpec)
                .where(memberSpec.member.id.eq(id))
                .fetchOne();
    }

    @Override
    public Long deleteByOwnerID(Long id) {
        return jpaQueryFactory
                .delete(memberSpec)
                .where(memberSpec.member.id.eq(id))
                .execute();
    }
}
