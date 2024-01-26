package Practice.ReHELPER.Service;


import Practice.ReHELPER.DTO.MemberSpecHistoryDTO;
import Practice.ReHELPER.Entity.MemberSpecHistory;

import java.util.List;

public interface MemberSpecHistoryService {
    void saveHistory(MemberSpecHistory memberSpecHistory);
    List<MemberSpecHistory> findAllHistory(Long id);
    MemberSpecHistory findFirstRecord(Long id);
    MemberSpecHistoryDTO buildMemberSpecHistory(MemberSpecHistory memberSpecHistory);
}
