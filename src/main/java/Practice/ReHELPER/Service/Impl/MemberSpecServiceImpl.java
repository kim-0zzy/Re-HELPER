package Practice.ReHELPER.Service.Impl;

import Practice.ReHELPER.Controller.MemberSpec.Form.UpdateMemberSpecForm;
import Practice.ReHELPER.DTO.MemberSpecDTO;
import Practice.ReHELPER.DTO.RoutineDTO;
import Practice.ReHELPER.Entity.E_type.Goals;
import Practice.ReHELPER.Entity.E_type.Level;
import Practice.ReHELPER.Entity.Embedded.MainPartition;
import Practice.ReHELPER.Entity.Embedded.Nutrition;
import Practice.ReHELPER.Entity.Embedded.SubPartition;
import Practice.ReHELPER.Entity.Map.RoutineMap;
import Practice.ReHELPER.Entity.Map.SetMap;
import Practice.ReHELPER.Entity.Member;
import Practice.ReHELPER.Entity.MemberSpec;
import Practice.ReHELPER.Entity.Embedded.Routine;
import Practice.ReHELPER.Repository.MemberSpecRepository;
import Practice.ReHELPER.Service.MemberSpecService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberSpecServiceImpl implements MemberSpecService {

    private final MemberSpecRepository memberSpecRepository;
    private final SetMap setMap;
    private final RoutineMap routineMap;

    @Override
    public void saveMemberSpec(MemberSpec memberSpec) {
        memberSpecRepository.save(memberSpec);
    }
    @Override
    public MemberSpec createMemberSpec(MemberSpec memberSpec, Member member) {
        memberSpec.setMember(member);
        memberSpec.setLevel(this.makeLevel(memberSpec.getCareer()));
        memberSpec.setRoutine(makeRoutine(memberSpec));
        return memberSpec;
    }
    @CacheEvict(value = "memberSpecDTO", key = "#id")
    @Override
    public void updateBasicMemberSpec(MemberSpec memberSpec, UpdateMemberSpecForm updateMemberSpecForm) {
        memberSpec.setBasicInfo(updateMemberSpecForm.getWeight(), updateMemberSpecForm.getWaist(),
                updateMemberSpecForm.getHip(), updateMemberSpecForm.getAge());
        memberSpec.setTimes(updateMemberSpecForm.getTimes());
        memberSpec.setGoals(updateMemberSpecForm.getGoals());
        memberSpec.setGender(updateMemberSpecForm.getGender());
        memberSpec.setLevel(this.makeLevel(memberSpec.getCareer()));
        memberSpec.setRoutine(makeRoutine(memberSpec));
    }
    @CacheEvict(value = "memberSpecDTO", key = "#id")
    @Override
    public Long resetMemberSpec(Long id) {
        return memberSpecRepository.deleteByOwnerID(id);
    }
    @Override
    public MemberSpec findMemberSpecByMemberId(Long id) {
        return memberSpecRepository.findByMemberId(id);
    }

//    @Cacheable(value = "memberSpecDTO", key = "#id")
    @Override
    public MemberSpecDTO findMemberSpecDTOByMemberId(Long id) {
        return buildMemberSpec(memberSpecRepository.findByMemberId(id));
    }
    @Override
    public RoutineDTO findRoutineDTOByMemberId(Long id) {
        return buildRoutine(memberSpecRepository.findByMemberId(id));
    }
    @Override
    public Routine makeRoutine(MemberSpec memberSpec) {

        Double BMR = makeBMR(memberSpec);
        Nutrition nutrition = makeNutrition(memberSpec, BMR);
        HashMap<String, Object> partList = makePartition(memberSpec.getTimes(), memberSpec.getGoals(), memberSpec.getLevel());
        return new Routine(
                (MainPartition) partList.get("MainPartition"),
                (SubPartition) partList.get("SubPartition"),
                nutrition, BMR);
    }
    @Override
    public Level makeLevel(Double career) {
        if (career < 1){
            return Level.UNTRAINED;
        } else if (1 < career && 3 >= career) {
            return Level.BEGINNER;
        } else if (3 < career && 6 >= career) {
            return Level.NOVICE;
        }
        return Level.INTERMEDIATE;
    }
    @Override
    public Double makeBMR(MemberSpec memberSpec) {
        return switch (memberSpec.getGender()) {
            case MALE -> ((88.4 + (13.4 * memberSpec.getWeight())) + (4.8 * memberSpec.getHeight()) - (5.68 * memberSpec.getAge()));
            case FEMALE -> ((447.6 + (9.25 * memberSpec.getWeight())) + (3.1 * memberSpec.getHeight()) - (4.33 * memberSpec.getAge()));
        };
    }
    @Override
    public Nutrition makeNutrition(MemberSpec memberSpec, Double bmr){
        double protein = Math.round(memberSpec.getWeight() * 1.6);
        double carbohydrate = Math.round((bmr * 0.4) / 4);
        double fat = Math.round(memberSpec.getWeight() * 0.8);
        return new Nutrition(protein, carbohydrate, fat);
    }
    @Override
    public HashMap<String, Object> makePartition(Integer times, Goals goals, Level level){
        // 임시 //
        String sets = setMap.makeSets(times);
        String reps = setMap.makeReps(goals, level, "main");
        String subSets = setMap.makeSubSets(times);
        String subReps = setMap.makeReps(goals, level, "sub");

        String back = null, chest = null, shoulder = null, leg = null;
        String subBack = null, subChest = null, subShoulder = null, subLeg = null;

        switch (level) {
            case UNTRAINED -> {
                back = routineMap.getBack().get("PullDown").get("Level1");
                chest = routineMap.getChest().get("Press").get("Level1");
                shoulder = routineMap.getShoulder().get("Press").get("Level1");
                leg = routineMap.getLeg().get("FreeWeight").get("Level1");

                subBack = "None";
                subChest = "None";
                subShoulder = "None";
                subLeg = "None";
            }
            case BEGINNER -> {
                back = routineMap.getBack().get("PullDown").get("Level1");
                chest = routineMap.getChest().get("Press").get("Level2");
                shoulder = routineMap.getShoulder().get("Press").get("Level2");
                leg = routineMap.getLeg().get("FreeWeight").get("Level1");

                subBack = routineMap.getBack().get("Row").get("Level1");
                subChest = routineMap.getChest().get("Press").get("Level1");
                subShoulder = routineMap.getShoulder().get("Press").get("Level1");
                subLeg = routineMap.getLeg().get("Machine").get("Level1") + "&" + routineMap.getLeg().get("Machine").get("Level2");
            }
            case NOVICE -> {
                back = routineMap.getBack().get("Row").get("Level2");
                chest = routineMap.getChest().get("Press").get("Level3");
                shoulder = routineMap.getShoulder().get("Press").get("Level2");
                leg = routineMap.getLeg().get("FreeWeight").get("Level2");

                subBack = routineMap.getBack().get("PullDown").get("Level1");
                subChest = routineMap.getChest().get("Press").get("Level1");
                subShoulder = routineMap.getShoulder().get("Press").get("Level3");
                subLeg = routineMap.getLeg().get("FreeWeight").get("Level1");
            }
            case INTERMEDIATE ->{
                back = "Choose Your Self";
                chest = "Choose Your Self";
                shoulder = "Choose Your Self";
                leg = "Choose Your Self";

                subBack = "Choose Your Self";
                subChest = "Choose Your Self";
                subShoulder = "Choose Your Self";
                subLeg = "Choose Your Self";
            }

        }

        HashMap<String, Object> partList = new HashMap<>();

        partList.put("MainPartition",new MainPartition(back, chest, shoulder, leg, reps, sets));
        partList.put("SubPartition", new SubPartition(subBack, subChest, subShoulder, subLeg, subReps, subSets));
        return partList;
    }


    @Override
    public void increaseCareer(MemberSpec memberSpec) {
        if (Objects.equals(memberSpec.getAttendanceChecker(), memberSpec.getTimes() - 1)) {
            memberSpec.setCareer(memberSpec.getCareer() + 0.25);
        }
        memberSpec.setAttendanceChecker(memberSpec.getAttendanceChecker() + 1);
    }
    @Override
    public void decreaseCareer(MemberSpec memberSpec) {
        if (Objects.equals(memberSpec.getAttendanceChecker(), 0)) {
            memberSpec.setCareer(memberSpec.getCareer() - 0.25);
        }
        memberSpec.setAttendanceChecker(memberSpec.getAttendanceChecker() - 1);
    }


    @Override
    public MemberSpecDTO buildMemberSpec(MemberSpec memberSpec) {
        return MemberSpecDTO.builder()
                .id(memberSpec.getId())
                .nickName(memberSpec.getMember().getNickName())
                .height(memberSpec.getHeight())
                .weight(memberSpec.getWeight())
                .hip(memberSpec.getHip())
                .waist(memberSpec.getWaist())
                .times(memberSpec.getTimes())
                .career(memberSpec.getCareer())
                .age(memberSpec.getAge())
                .gender(memberSpec.getGender().toString())
                .goals(memberSpec.getGoals().toString())
                .level(memberSpec.getLevel().toString())
                .routine(memberSpec.getRoutine())
                .build();
    }
    @Override
    public RoutineDTO buildRoutine(MemberSpec memberSpec) {
        return RoutineDTO.builder()
                .nickName(memberSpec.getMember().getNickName())
                .BMR(memberSpec.getRoutine().getBMR())
                .mainPartition(memberSpec.getRoutine().getMainPartition())
                .subPartition(memberSpec.getRoutine().getSubPartition())
                .nutrition(memberSpec.getRoutine().getNutrition())
                .build();
    }
}
