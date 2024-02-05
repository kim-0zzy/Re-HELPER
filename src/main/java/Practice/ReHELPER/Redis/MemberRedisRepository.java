package Practice.ReHELPER.Redis;

import org.springframework.data.repository.CrudRepository;


public interface MemberRedisRepository extends CrudRepository<cacheMember, String> {
}
