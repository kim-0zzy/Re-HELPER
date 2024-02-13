package Practice.ReHELPER.Redis.Repository;

import Practice.ReHELPER.DTO.MemberSpecDTO;
import org.springframework.data.repository.CrudRepository;

public interface MemberSpecRedisRepository extends CrudRepository<MemberSpecDTO, Long> {
}
