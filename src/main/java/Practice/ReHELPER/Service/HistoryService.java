package Practice.ReHELPER.Service;


import Practice.ReHELPER.Entity.MemberSpecHistory;

import java.util.List;

public interface HistoryService {
    public Long saveHistory(MemberSpecHistory memberSpecHistory);
    public List<MemberSpecHistory> findAllHistory(Long id);
    public MemberSpecHistory findFirstRecord(Long id);
    public MemberSpecHistory findFirstRecord_V2(Long id);
}
