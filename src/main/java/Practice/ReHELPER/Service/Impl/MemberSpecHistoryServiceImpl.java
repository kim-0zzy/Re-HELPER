package Practice.ReHELPER.Service.Impl;

import Practice.ReHELPER.DTO.MemberSpecHistoryDTO;
import Practice.ReHELPER.Entity.MemberSpecHistory;
import Practice.ReHELPER.Exception.NotFoundResultException;
import Practice.ReHELPER.Repository.MemberSpecHistoryRepository;
import Practice.ReHELPER.Service.MemberSpecHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<MemberSpecHistoryDTO> findAllHistory(Long id) {
        return memberSpecHistoryRepository.findByOwnerID(id)
                .stream()
                .map(this::buildMemberSpecHistory)
                .collect(Collectors.toList());
    }
    @Override
    public MemberSpecHistoryDTO findFirstRecord(Long id) throws NotFoundResultException {
        MemberSpecHistory first = memberSpecHistoryRepository.findFirst(id);
        if (first == null) {
            throw new NotFoundResultException("Record is not Founded");
        }
        return buildMemberSpecHistory(first);
    }
    @Override
    public Long resetHistory(Long id) {
        return memberSpecHistoryRepository.deleteAllByOwnerID(id);
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
}
