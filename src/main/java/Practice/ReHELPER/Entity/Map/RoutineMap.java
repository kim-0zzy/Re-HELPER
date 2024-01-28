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
        // 임시 //
        Map<String, String> back_PullDown = new ConcurrentHashMap<>();
        back_PullDown.put("Level1", "LatPullDown");
        back_PullDown.put("Level2", "ArmPullDown");
        back_PullDown.put("Level3", "PullUp");

        Map<String, String> back_Row = new ConcurrentHashMap<>();
        back_Row.put("Level1", "SeatedRow");
        back_Row.put("Level2", "DumbbellRow");
        back_Row.put("Level3", "BarbellRow");

        Map<String, String> chest_Press = new ConcurrentHashMap<>();
        chest_Press.put("Level1", "ChestPressMachine");
        chest_Press.put("Level2", "BenchPress");
        chest_Press.put("Level3", "InclineBenchPress");

        Map<String, String> chest_fly = new ConcurrentHashMap<>();
        chest_fly.put("Level1", "ChestFlyMachine");
        chest_fly.put("Level2", "DumbbellFly");
        chest_fly.put("Level3", "CableCrossOver");

        Map<String, String> shoulder_Press = new ConcurrentHashMap<>();
        shoulder_Press.put("Level1", "ShoulderPressMachine");
        shoulder_Press.put("Level2", "StandingOverHeadPress");
        shoulder_Press.put("Level3", "DumbbellShoulderPress");

        Map<String, String> shoulder_Raise = new ConcurrentHashMap<>();
        shoulder_Raise.put("Level1", "SideLateralRaise");
        shoulder_Raise.put("Level2", "FrontRaise");
        shoulder_Raise.put("Level3", "BentOverLateralRaise");

        Map<String, String> leg_freeWeight = new ConcurrentHashMap<>();
        leg_freeWeight.put("Level1", "Squat");
        leg_freeWeight.put("Level2", "DeadLift");
        leg_freeWeight.put("Level3", "Lunge");

        Map<String, String> leg_Machine = new ConcurrentHashMap<>();
        leg_Machine.put("Level1", "LegExtension");
        leg_Machine.put("Level2", "LegCull");
        leg_Machine.put("Level3", "LegPress");


        back.put("Row", back_Row);
        back.put("PullDown", back_PullDown);

        chest.put("Press", chest_Press);
        chest.put("Fly", chest_fly);

        shoulder.put("Press", shoulder_Press);
        shoulder.put("Raise", shoulder_Raise);

        leg.put("FreeWeight", leg_freeWeight);
        leg.put("Machine", leg_Machine);
        }

    public Map<String, Map<String, String>> getBack() {
        return back;
    }

    public Map<String, Map<String, String>> getChest() {
        return chest;
    }

    public Map<String, Map<String, String>> getShoulder() {
        return shoulder;
    }

    public Map<String, Map<String, String>> getLeg() {
        return leg;
    }
}
