package Practice.ReHELPER.Service;


import Practice.ReHELPER.DTO.MemberSpecHistoryDTO;
import Practice.ReHELPER.Entity.MemberSpecHistory;
import Practice.ReHELPER.Exception.NotFoundResultException;

import java.util.List;

public interface MemberSpecHistoryService {
    void saveHistory(MemberSpecHistory memberSpecHistory);
    List<MemberSpecHistoryDTO> findAllHistory(Long id);
    List<MemberSpecHistoryDTO> findFirstRecord(Long id) throws NotFoundResultException;
    MemberSpecHistoryDTO buildMemberSpecHistory(MemberSpecHistory memberSpecHistory);
    Long resetHistory(Long id);
}
