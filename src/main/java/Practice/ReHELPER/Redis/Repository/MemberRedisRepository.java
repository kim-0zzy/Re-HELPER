package Practice.ReHELPER.Redis.Repository;

import Practice.ReHELPER.DTO.MemberDTO;
import org.springframework.data.repository.CrudRepository;


public interface MemberRedisRepository extends CrudRepository<MemberDTO, Long> {

}
