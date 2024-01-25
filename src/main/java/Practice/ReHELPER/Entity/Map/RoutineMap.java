package Practice.ReHELPER.Entity.Map;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RoutineMap {

    Map<String, Map<String, String>> back = new ConcurrentHashMap<>();
    Map<String, Map<String, String>> chest = new ConcurrentHashMap<>();
    Map<String, Map<String, String>> shoulder = new ConcurrentHashMap<>();
    Map<String, Map<String, String>> leg = new ConcurrentHashMap<>();

    public RoutineMap(){
        Map<String, String> back_Row = new ConcurrentHashMap<>();
        Map<String, String> back_PullDown = new ConcurrentHashMap<>();

        Map<String, String> chest_Press = new ConcurrentHashMap<>();
        Map<String, String> chest_fly = new ConcurrentHashMap<>();

        Map<String, String> shoulder_Press = new ConcurrentHashMap<>();
        Map<String, String> shoulder_Raise = new ConcurrentHashMap<>();

        Map<String, String> leg_freeWeight = new ConcurrentHashMap<>();
        Map<String, String> leg_Machine = new ConcurrentHashMap<>();


        back.put("Row", back_Row);
        back.put("PullDown", back_PullDown);

        chest.put("Press", chest_Press);
        chest.put("Fly", chest_fly);

        shoulder.put("Press", shoulder_Press);
        shoulder.put("Raise", shoulder_Raise);

        leg.put("FreeWeight", leg_freeWeight);
        leg.put("Machine", leg_Machine);

//            back.put("Main", "PullUp");
//            back.put("Basic", "LatPullDown");
//            back.put("Sub", "SeatedRow");
//            back.put("None", "Unnecessary");
//            back.put(new ConcurrentHashMap<String, String>())
//
//            chest.put("Main", "BenchPress");
//            chest.put("Basic", "ChestPress");
//            chest.put("Sub", "InclineBenchPress");
//            chest.put("None", "Unnecessary");
//
//            shoulder.put("Main", "OverHeadPress");
//            shoulder.put("Basic", "ShoulderPressMachine");
//            shoulder.put("Sub", "SideLateralRaise");
//            shoulder.put("None", "Unnecessary");
//
//            leg.put("Main", "Squat|DeadLift");
//            leg.put("Basic", "LegCull&Extension");
//            leg.put("Sub", "LegPress");
//            leg.put("None", "Unnecessary");
        }

}
