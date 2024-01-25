package Practice.ReHELPER.Config;

import Practice.ReHELPER.Entity.Map.RoutineMap;
import Practice.ReHELPER.Entity.Map.SetMap;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    JPAQueryFactory jpaQueryFactory(EntityManager em) {
        return new JPAQueryFactory(em);
    }
    @Bean
    RoutineMap routineMap() {
        return new RoutineMap();
    }
    @Bean
    SetMap setMap() {
        return new SetMap();
    }

}
