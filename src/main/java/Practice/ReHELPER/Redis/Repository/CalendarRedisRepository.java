package Practice.ReHELPER.Redis.Repository;

import Practice.ReHELPER.DTO.CalendarDTO;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CalendarRedisRepository extends CrudRepository<CalendarDTO, Long> {
}
