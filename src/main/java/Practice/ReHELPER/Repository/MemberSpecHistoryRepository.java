package Practice.ReHELPER.Repository;

import Practice.ReHELPER.Entity.MemberSpecHistory;

import java.util.List;

public interface MemberSpecHistoryRepository {
    void save(MemberSpecHistory memberSpecHistory);
    List<MemberSpecHistory> findByOwnerID(Long id);
    MemberSpecHistory findFirst(Long id);
}
