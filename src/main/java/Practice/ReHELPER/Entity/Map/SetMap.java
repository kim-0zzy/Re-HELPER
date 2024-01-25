package Practice.ReHELPER.Entity.Map;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SetMap {

    Map<Integer, String> sets = new ConcurrentHashMap<>();
    Map<String, String> reps = new ConcurrentHashMap<>();
    public SetMap() {
        // 주간 n회에 따른 세트수
        sets.put(2, "5");
        sets.put(3, "4");
        sets.put(4, "3");

        // 목적에 따른 반복수
        reps.put("DIET", "8~12");
        reps.put("BULK", "8~12");
        reps.put("STRENGTH", "3~5");
        reps.put("ENDURE", "15~20");
    }

}
