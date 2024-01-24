package Practice.ReHELPER.Repository;

import Practice.ReHELPER.Entity.MemberSpec;

import java.util.List;

public interface MemberSpecRepository {
    void save(MemberSpec memberSpec);
    MemberSpec findById(Long id);
    List findAll();
    MemberSpec findByMemberId(Long id);
}
