package Practice.ReHELPER.Entity;


import Practice.ReHELPER.Entity.Embedded.MainPartition;
import Practice.ReHELPER.Entity.Embedded.Nutrition;
import Practice.ReHELPER.Entity.Embedded.SubPartition;
import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Getter
public class Routine {

    @Id @GeneratedValue
    @Column(name = "routine_id")
    private Long id;

    @OneToOne(mappedBy = "routine")
    private MemberSpec memberSpec;
    @Embedded
    private MainPartition mainPartition;
    @Embedded
    private SubPartition subPartition;
    @Embedded
    private Nutrition nutrition;

    public void setMemberSpec(MemberSpec memberSpec) {
        this.memberSpec = memberSpec;
    }
    public void setSubPartition(SubPartition subpartition){
        this.subPartition = subpartition;
    }
    public void setMainPartition(MainPartition mainPartition) {
        this.mainPartition = mainPartition;
    }
    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
    }
    public double getBMR(MemberSpec memberSpec){
        int height = memberSpec.getHeight();
        int weight = memberSpec.getWeight();
        int age = memberSpec.getAge();

        return switch (memberSpec.getGender()) {
            case MALE -> ((88.4 + (13.4 * weight)) + (4.8 * height) - (5.68 * age));
            case FEMALE -> ((447.6 + (9.25 * weight)) + (3.1 * height) - (4.33 * age));
        };
    }
//    public void makePartition(MemberSpec memberSpec){
//        Goals goals = memberSpec.getGoals();
//        Level level = memberSpec.getLevel();
//        String aerobic = switch (goals) {
//            case DIET -> "20min";
//            case ENDURE, BULKUP, STRENGTH -> "None";
//        };
//
//        SetMap setMap = new SetMap();
//        RoutineMap routineMap = new RoutineMap();
//
//        String sets = setMap.makeSets(memberSpec);
//        String reps = setMap.makeReps(memberSpec);
//
//        String main = "Main";
//        String basic = "Basic";
//        String sub = "Sub";
//        String none = "None";
//        String back = null, chest = null, shoulder = null, leg = null;
//        String subBack = null, subChest = null, subShoulder = null, subLeg = null;
//
//        switch (level) {
//            case UNTRAINED -> {
//                back = routineMap.getBack(basic);
//                chest = routineMap.getChest(basic);
//                shoulder = routineMap.getShoulder(basic);
//                leg = routineMap.getLeg(basic);
//                subBack = routineMap.getBack(none);
//                subChest = routineMap.getChest(none);
//                subShoulder = routineMap.getShoulder(none);
//                subLeg = routineMap.getLeg(none);
//            }
//            case BEGINNER -> {
//                back = routineMap.getBack(main);
//                chest = routineMap.getChest(main);
//                shoulder = routineMap.getShoulder(main);
//                leg = routineMap.getLeg(main);
//                subBack = routineMap.getBack(none);
//                subChest = routineMap.getChest(none);
//                subShoulder = routineMap.getShoulder(none);
//                subLeg = routineMap.getLeg(none);
//            }
//            case NOVICE -> {
//                back = routineMap.getBack(main);
//                chest = routineMap.getChest(main);
//                shoulder = routineMap.getShoulder(main);
//                leg = routineMap.getLeg(main);
//                subBack = routineMap.getBack(sub);
//                subChest = routineMap.getChest(sub);
//                subShoulder = routineMap.getShoulder(sub);
//                subLeg = routineMap.getLeg(sub);
//            }
//        }
//        MainPartition mainPartition = new MainPartition(back, chest, shoulder, leg , reps, sets, aerobic);
//        SubPartition subPartition = new SubPartition(subBack, subChest, subShoulder, subLeg, reps, sets);
//        this.setMainPartition(mainPartition);
//        this.setSubPartition(subPartition);
}
//    public void makeNutrition(MemberSpec memberSpec){
//        // 공식 수정 예정 and Service 계층으로 양도 예정
//        double protein = Math.round(memberSpec.getWeight() * 1.6);
//        double carbohydrate = Math.round((this.getBMR(memberSpec) * 0.4) / 4);
//        double fat = Math.round(memberSpec.getWeight() * 0.8);
//        Nutrition nutrition = new Nutrition(protein, carbohydrate, fat);
//        this.setNutrition(nutrition);
//    }
//}
