package Practice.ReHELPER.Controller.MemberSpec;

import Practice.ReHELPER.Controller.MemberSpec.Form.CreateMemberSpecForm;
import Practice.ReHELPER.Controller.MemberSpec.Form.UpdateMemberSpecForm;
import Practice.ReHELPER.DTO.MemberSpecDTO;
import Practice.ReHELPER.DTO.MemberSpecHistoryDTO;
import Practice.ReHELPER.DTO.MessageResponseDTO;
import Practice.ReHELPER.DTO.RoutineDTO;
import Practice.ReHELPER.Entity.E_type.Gender;
import Practice.ReHELPER.Entity.E_type.Goals;
import Practice.ReHELPER.Entity.Member;
import Practice.ReHELPER.Entity.MemberSpec;
import Practice.ReHELPER.Entity.MemberSpecHistory;
import Practice.ReHELPER.Exception.NotFoundIdException;
import Practice.ReHELPER.Exception.NotFoundResultException;
import Practice.ReHELPER.Exception.NotLoggedInException;
import Practice.ReHELPER.Redis.MemberSpec.MemberSpecRedisRepository;
import Practice.ReHELPER.Redis.MemberSpec.cacheMemberSpec;
import Practice.ReHELPER.Service.MemberService;
import Practice.ReHELPER.Service.MemberSpecHistoryService;
import Practice.ReHELPER.Service.MemberSpecService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apiMemberSpec")
public class APIMemberSpecController {

    private final MemberSpecService memberSpecService;
    private final MemberSpecHistoryService memberSpecHistoryService;
    private final MemberService memberService;
    private final MemberSpecRedisRepository memberSpecRedisRepository;

    public Long loadLoginMember() throws NotLoggedInException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            throw new NotLoggedInException("Not Yet Logged in");
        }
        Member member = (Member) authentication.getPrincipal();
        return member.getId();
    }

    @PostMapping("/createMemberSpec")
    public ResponseEntity<MessageResponseDTO> createMemberSpec(@Valid @RequestBody CreateMemberSpecForm createMemberSpecForm)
            throws NotLoggedInException, NotFoundIdException, NotFoundResultException {
        Member member = memberService.findOneById(loadLoginMember()).orElseThrow(() -> new NotFoundIdException("존재하지 않는 회원입니다."));

        Gender gender = switch (createMemberSpecForm.getGender()) {
            case "female" -> Gender.FEMALE;
            case "male" -> Gender.MALE;
            default -> throw new NotFoundResultException("Not found Value in : createMemberSpecForm.getGender()");
        };
        Goals goals = switch (createMemberSpecForm.getGoals()) {
            case "diet" -> Goals.DIET;
            case "str" -> Goals.STRENGTH;
            case "end" -> Goals.ENDURE;
            case "bulk" -> Goals.BULK;
            default -> throw new NotFoundResultException("Not found Value in : createMemberSpecForm.getGoals()");
        };

        MemberSpec memberSpec = memberSpecService.createMemberSpec(
                new MemberSpec(member, createMemberSpecForm.getHeight(), createMemberSpecForm.getWeight(),
                        createMemberSpecForm.getWaist(), createMemberSpecForm.getHip(),
                        createMemberSpecForm.getCareer(), createMemberSpecForm.getAge(),
                        createMemberSpecForm.getTimes(), gender, goals));
        MemberSpecHistory memberSpecHistory = new MemberSpecHistory(memberSpec.getWeight(), memberSpec.getCareer());

        memberSpecService.saveMemberSpec(memberSpec);
        memberSpecHistoryService.saveHistory(memberSpecHistory);

        MemberSpecDTO memberSpecDTO = memberSpecService.buildMemberSpec(memberSpec);
        MemberSpecHistoryDTO memberSpecHistoryDTO = memberSpecHistoryService.buildMemberSpecHistory(memberSpecHistory);

        List<Object> responseList = new ArrayList<>();
        responseList.add(memberSpecDTO);
        responseList.add(memberSpecHistoryDTO);

        MessageResponseDTO messageResponseDTO = new MessageResponseDTO("Create Success", HttpStatus.CREATED.value(),
                responseList);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(messageResponseDTO, httpHeaders, HttpStatus.CREATED);
    }

    @PostMapping("/updateMemberSpec")
    public ResponseEntity<MessageResponseDTO> updateMemberSpec(@Valid @RequestBody UpdateMemberSpecForm updateMemberSpecForm) throws NotLoggedInException{
        MemberSpec memberSpec = memberSpecService.findMemberSpecByMemberId(loadLoginMember());

        memberSpecService.updateBasicMemberSpec(memberSpec, updateMemberSpecForm);

        MemberSpecHistory memberSpecHistory = new MemberSpecHistory(memberSpec.getWeight(), memberSpec.getCareer());
        memberSpecHistoryService.saveHistory(memberSpecHistory);

        MemberSpecDTO memberSpecDTO = memberSpecService.buildMemberSpec(memberSpec);
        MemberSpecHistoryDTO memberSpecHistoryDTO = memberSpecHistoryService.buildMemberSpecHistory(memberSpecHistory);

        List<Object> responseList = new ArrayList<>();
        responseList.add(memberSpecDTO);
        responseList.add(memberSpecHistoryDTO);

        MessageResponseDTO messageResponseDTO = new MessageResponseDTO("Update Success", HttpStatus.OK.value(),
                responseList);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(messageResponseDTO, httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/resetMemberSpec")
    public ResponseEntity<MessageResponseDTO> resetMemberSpec() throws NotLoggedInException {
        MemberSpec memberSpec = memberSpecService.findMemberSpecByMemberId(loadLoginMember());
        Long historyRestCount = memberSpecHistoryService.resetHistory(memberSpec.getId());
        Long specRestCount = memberSpecService.resetMemberSpec(loadLoginMember());

        String message = memberSpec.getMember().getNickName() + "'s " + specRestCount + "cases MemberSpec Data have been Deleted. \n"
                + "And " + historyRestCount + "cases MemberSpecHistory Data have been Deleted.";

        MessageResponseDTO messageResponseDTO = new MessageResponseDTO(
                "Reset Success", HttpStatus.OK.value(), message);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(messageResponseDTO, httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/info")
    public ResponseEntity<MessageResponseDTO> getMemberSpecInfo() throws NotLoggedInException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        Optional<cacheMemberSpec> cachedMemberSpec = memberSpecRedisRepository.findById(loadLoginMember());
        if (cachedMemberSpec.isPresent()) {
            return new ResponseEntity<>(new MessageResponseDTO("Find Success", HttpStatus.OK.value(),
                    cachedMemberSpec.get()), httpHeaders, HttpStatus.OK);
        }

        MemberSpec memberSpec = memberSpecService.findMemberSpecByMemberId(loadLoginMember());
        // service에서 저장해놓고 if문으로 찾아올까?
        memberSpecRedisRepository.save(cacheMemberSpec.builder()
                .nickName(memberSpec.getMember().getNickName())
                .originId(memberSpec.getId())
                .height(memberSpec.getHeight())
                .weight(memberSpec.getWeight())
                .waist(memberSpec.getWaist())
                .hip(memberSpec.getHip())
                .career(memberSpec.getCareer())
                .age(memberSpec.getAge())
                .times(memberSpec.getTimes())
                .gender(memberSpec.getGender())
                .goals(memberSpec.getGoals())
                .level(memberSpec.getLevel())
                .routine(memberSpec.getRoutine())
                .build());

        MemberSpecDTO memberSpecDTO = memberSpecService.buildMemberSpec(memberSpec);
        MessageResponseDTO messageResponseDTO = new MessageResponseDTO("Find Success", HttpStatus.OK.value(),
                memberSpecDTO);
        return new ResponseEntity<>(messageResponseDTO, httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/info/Routine")
    public ResponseEntity<MessageResponseDTO> getRoutineInfo() throws NotLoggedInException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        Optional<cacheMemberSpec> cachedMemberSpec = memberSpecRedisRepository.findById(loadLoginMember());
        if (cachedMemberSpec.isPresent()) {
            return new ResponseEntity<>(new MessageResponseDTO("Find Success", HttpStatus.OK.value(),
                    new RoutineDTO(cachedMemberSpec.get().getNickName(), cachedMemberSpec.get().getRoutine().getMainPartition(),
                            cachedMemberSpec.get().getRoutine().getSubPartition(), cachedMemberSpec.get().getRoutine().getNutrition(),
                            cachedMemberSpec.get().getRoutine().getBMR())),
                    httpHeaders, HttpStatus.OK);
        }

        MemberSpec memberSpec = memberSpecService.findMemberSpecByMemberId(loadLoginMember());
        RoutineDTO routineDTO = memberSpecService.buildRoutine(memberSpec);

        MessageResponseDTO messageResponseDTO = new MessageResponseDTO("Find Success", HttpStatus.OK.value(),
                routineDTO);

        return new ResponseEntity<>(messageResponseDTO, httpHeaders, HttpStatus.OK);
    }
}
