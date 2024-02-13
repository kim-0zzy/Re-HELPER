package Practice.ReHELPER.Service.Impl;

import Practice.ReHELPER.DTO.MemberSpecHistoryDTO;
import Practice.ReHELPER.Entity.MemberSpecHistory;
import Practice.ReHELPER.Exception.NotFoundResultException;
import Practice.ReHELPER.Repository.MemberSpecHistoryRepository;
import Practice.ReHELPER.Service.MemberSpecHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Cacheable(value = "historyDTOList", key = "#id")
    @Override
    public List<MemberSpecHistoryDTO> findAllHistory(Long id) {
        return memberSpecHistoryRepository.findByOwnerID(id)
                .stream()
                .map(this::buildMemberSpecHistory)
                .collect(Collectors.toList());
    }
    @Cacheable(value = "historyDTO", key = "#id")
    @Override
    public List<MemberSpecHistoryDTO> findFirstRecord(Long id) throws NotFoundResultException {
        MemberSpecHistory first = memberSpecHistoryRepository.findFirst(id);
        if (first == null) {
            throw new NotFoundResultException("Record is not Founded");
        }
        List<MemberSpecHistoryDTO> result = new ArrayList<>();
        result.add(buildMemberSpecHistory(first));
        return result;
    }
    @Override
    public Long resetHistory(Long id) {
        return memberSpecHistoryRepository.deleteAllByOwnerID(id);
    }
    @Override
    public MemberSpecHistoryDTO buildMemberSpecHistory(MemberSpecHistory memberSpecHistory) {
        return MemberSpecHistoryDTO.builder()
                .id(memberSpecHistory.getId())
                .makeDateWithTime(memberSpecHistory.getMakeDateWithTime())
                .makeDate(memberSpecHistory.getMakeDate())
                .historyWeight(memberSpecHistory.getHistoryWeight())
                .historyWeight(memberSpecHistory.getHistoryWeight())
                .build();
    }
}
