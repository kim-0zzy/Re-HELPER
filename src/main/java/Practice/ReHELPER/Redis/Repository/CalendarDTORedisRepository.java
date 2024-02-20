package Practice.ReHELPER.Redis.Repository;

import Practice.ReHELPER.DTO.CalendarDTO;
import Practice.ReHELPER.DTO.ResponseCalendarDTO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CalendarDTORedisRepository extends CrudRepository<ResponseCalendarDTO, Long> {
}
