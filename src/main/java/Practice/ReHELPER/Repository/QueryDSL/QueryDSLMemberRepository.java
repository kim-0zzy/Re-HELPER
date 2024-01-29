package Practice.ReHELPER.Repository.QueryDSL;

import Practice.ReHELPER.Entity.Member;
import Practice.ReHELPER.Repository.MemberRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static Practice.ReHELPER.Entity.QMember.member;

@Repository
@RequiredArgsConstructor
@Primary
public class QueryDSLMemberRepository implements MemberRepository {

    private final EntityManager em;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public void save(Member member) {
        em.persist(member);
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public List<Member> findAll() {
        return jpaQueryFactory
                .selectFrom(member)
                .fetch();
    }

    @Override
    public List<Member> findByNickName(String nickName) {
        return jpaQueryFactory
                .selectFrom(member)
                .where(member.nickName.eq(nickName))
                .fetch();
    }

    @Override
    public Optional<Member> findByUsername(String username) {
        Member searchMember =  jpaQueryFactory
                .selectFrom(member)
                .where(member.username.eq(username))
                .fetchOne();
        return Optional.ofNullable(searchMember);
    }
}
