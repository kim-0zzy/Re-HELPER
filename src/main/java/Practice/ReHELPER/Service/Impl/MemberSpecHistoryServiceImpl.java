package Practice.ReHELPER.Service.Impl;

import Practice.ReHELPER.DTO.MemberSpecHistoryDTO;
import Practice.ReHELPER.Entity.MemberSpecHistory;
import Practice.ReHELPER.Repository.MemberSpecHistoryRepository;
import Practice.ReHELPER.Service.MemberSpecHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberSpecHistoryServiceImpl implements MemberSpecHistoryService {

    private final MemberSpecHistoryRepository memberSpecHistoryRepository;

    @Override
    public void saveHistory(MemberSpecHistory memberSpecHistory) {
        memberSpecHistoryRepository.save(memberSpecHistory);
    }

    @Override
    public List<MemberSpecHistory> findAllHistory(Long id) {
        return memberSpecHistoryRepository.findByOwnerID(id);
    }

    @Override
    public MemberSpecHistory findFirstRecord(Long id) {
        return memberSpecHistoryRepository.findFirst(id);
    }

    @Override
    public MemberSpecHistoryDTO buildMemberSpecHistory(MemberSpecHistory memberSpecHistory) {
        return MemberSpecHistoryDTO.builder()
                .makeDateWithTime(memberSpecHistory.getMakeDateWithTime())
                .makeDate(memberSpecHistory.getMakeDate())
                .historyWeight(memberSpecHistory.getHistoryWeight())
                .historyWeight(memberSpecHistory.getHistoryWeight())
                .build();
    }

    @Override
    public Long resetHistory(Long id) {
        return memberSpecHistoryRepository.deleteAllByOwnerID(id);
    }
}
