package Practice.ReHELPER.Redis.Repository;

import Practice.ReHELPER.DTO.MemberSpecHistoryDTO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MemberSpecHistoryRedisRepository extends CrudRepository<List<MemberSpecHistoryDTO>, Long> {
}
