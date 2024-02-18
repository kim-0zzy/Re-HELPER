package Practice.ReHELPER.Redis.Repository;

import Practice.ReHELPER.DTO.CalendarDTO;
import org.springframework.data.repository.CrudRepository;

public interface CalendarDTORedisRepository extends CrudRepository<CalendarDTO, Long> {
}
