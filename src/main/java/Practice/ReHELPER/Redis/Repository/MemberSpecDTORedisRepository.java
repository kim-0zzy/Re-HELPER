package Practice.ReHELPER.Redis.Repository;

import Practice.ReHELPER.DTO.MemberSpecDTO;
import org.springframework.data.repository.CrudRepository;

public interface MemberSpecDTORedisRepository extends CrudRepository<MemberSpecDTO, Long> {
}
