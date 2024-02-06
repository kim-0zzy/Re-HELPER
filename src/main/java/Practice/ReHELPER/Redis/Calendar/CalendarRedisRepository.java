package Practice.ReHELPER.Redis.Calendar;

import Practice.ReHELPER.DTO.CalendarDTO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CalendarRedisRepository extends CrudRepository<List<CalendarDTO>, String> {
}
