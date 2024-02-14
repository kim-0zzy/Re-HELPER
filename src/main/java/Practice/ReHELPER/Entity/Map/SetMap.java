package Practice.ReHELPER.Entity.Map;

import Practice.ReHELPER.Entity.E_type.Goals;
import Practice.ReHELPER.Entity.E_type.Level;
import Practice.ReHELPER.Entity.MemberSpec;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SetMap {

    Map<Integer, String> sets = new ConcurrentHashMap<>();
    Map<Integer, String> subSets = new ConcurrentHashMap<>();
    Map<String, String> reps = new ConcurrentHashMap<>();
    public SetMap() {
        // 주간 n회에 따른 세트수
        sets.put(2, "5");
        sets.put(3, "4");
        sets.put(4, "3");
        // 서브 운동 세트 수
        subSets.put(2, "3");
        subSets.put(3, "3");
        subSets.put(4, "2");

        // 목적에 따른 반복수
        reps.put("DIET", "12~15");
        reps.put("BEAUTY", "5~8");
        reps.put("STRENGTH", "3~5");
        reps.put("SUB", "8~12");
        reps.put("UNTRAINED", "12~15");
    }

    public String makeSets(Integer times) {
        return this.sets.get(times);
    }
    public String makeSubSets(Integer times) {
        return this.subSets.get(times);
    }
    public String makeReps(Goals goals, Level level, String identifier) {
        if (identifier.equals("sub")) {
            return this.reps.get("SUB");
        }
        if (level.equals(Level.UNTRAINED) || level.equals(Level.BEGINNER)) {
            return this.reps.get("UNTRAINED");
        }
        return this.reps.get(goals.toString());
    }
}
