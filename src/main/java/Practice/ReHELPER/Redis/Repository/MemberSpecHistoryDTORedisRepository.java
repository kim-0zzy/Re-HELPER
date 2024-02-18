package Practice.ReHELPER.Redis.Repository;

import Practice.ReHELPER.DTO.MemberSpecHistoryDTO;
import org.springframework.data.repository.CrudRepository;

public interface MemberSpecHistoryDTORedisRepository extends CrudRepository<MemberSpecHistoryDTO, Long> {
}
