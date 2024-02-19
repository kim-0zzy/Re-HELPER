package Practice.ReHELPER.Redis.Repository;

import Practice.ReHELPER.DTO.MemberSpecHistoryDTO;
import Practice.ReHELPER.DTO.ResponseHistoryToListDTO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MemberSpecHistoryDTORedisRepository extends CrudRepository<ResponseHistoryToListDTO, Long> {
}
