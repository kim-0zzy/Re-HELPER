package Practice.ReHELPER.Config;

import Practice.ReHELPER.Entity.Map.RoutineMap;
import Practice.ReHELPER.Entity.Map.SetMap;
import Practice.ReHELPER.Entity.Member;
import Practice.ReHELPER.Exception.NotLoggedInException;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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
