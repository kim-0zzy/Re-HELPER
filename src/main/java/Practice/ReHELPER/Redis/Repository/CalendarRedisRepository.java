package Practice.ReHELPER.Redis.Repository;

import Practice.ReHELPER.DTO.CalendarDTO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CalendarRedisRepository extends CrudRepository<List<CalendarDTO>, Long> {
}
