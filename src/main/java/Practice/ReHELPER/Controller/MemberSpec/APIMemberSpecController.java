package Practice.ReHELPER.Controller.MemberSpec;

import Practice.ReHELPER.Config.LoggedMemberHolder;
import Practice.ReHELPER.Controller.MemberSpec.Form.CreateMemberSpecForm;
import Practice.ReHELPER.Controller.MemberSpec.Form.ResponseInfoDTO;
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
import Practice.ReHELPER.Exception.NotFoundResultException;
import Practice.ReHELPER.Exception.NotLoggedInException;

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


@RestController
@RequiredArgsConstructor
@RequestMapping("/apiMemberSpec")
public class APIMemberSpecController {

    private final MemberSpecService memberSpecService;
    private final MemberSpecHistoryService memberSpecHistoryService;
    private final LoggedMemberHolder loggedMemberHolder;

    public Member loadLoginMember() throws NotLoggedInException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            throw new NotLoggedInException("Not Logged in Yet");
        }
        return loggedMemberHolder.getLoggedMember().get(authentication.getName());
    }

    @PostMapping("/createMemberSpec")
    public ResponseEntity<MessageResponseDTO> createMemberSpec(@Valid @RequestBody CreateMemberSpecForm createMemberSpecForm)
            throws NotLoggedInException, NotFoundResultException {
        Member member = loadLoginMember();

        Gender gender = switch (createMemberSpecForm.getGender()) {
            case "female" -> Gender.FEMALE;
            case "male" -> Gender.MALE;
            default -> throw new NotFoundResultException("Not found Value in : createMemberSpecForm.getGender()");
        };
        Goals goals = switch (createMemberSpecForm.getGoals()) {
            case "diet" -> Goals.DIET;
            case "str" -> Goals.STRENGTH;
            case "beauty" -> Goals.BEAUTY;
            default -> throw new NotFoundResultException("Not found Value in : createMemberSpecForm.getGoals()");
        };

        MemberSpec memberSpec = memberSpecService.createMemberSpec(
                new MemberSpec(createMemberSpecForm.getHeight(), createMemberSpecForm.getWeight(),
                        createMemberSpecForm.getWaist(), createMemberSpecForm.getHip(),
                        createMemberSpecForm.getCareer(), createMemberSpecForm.getAge(),
                        createMemberSpecForm.getTimes(), gender, goals), member);
        MemberSpecHistory memberSpecHistory = new MemberSpecHistory(memberSpec.getWeight(), memberSpec.getCareer());
        memberSpecHistory.setMemberSpec(memberSpec);

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
    public ResponseEntity<MessageResponseDTO> updateMemberSpec(@Valid @RequestBody UpdateMemberSpecForm updateMemberSpecForm)
            throws NotLoggedInException{
        MemberSpec memberSpec = memberSpecService.findMemberSpecByMemberId(loadLoginMember().getId());

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
    public ResponseEntity<MessageResponseDTO> resetMemberSpec()
            throws NotLoggedInException {
        MemberSpec memberSpec = memberSpecService.findMemberSpecByMemberId(loadLoginMember().getId());
        Long historyRestCount = memberSpecHistoryService.resetHistory(memberSpec.getMember().getId());
        Long specResetCount = memberSpecService.resetMemberSpec(loadLoginMember().getId());

        String message = memberSpec.getMember().getNickName() + "'s " + specResetCount + "cases MemberSpec Data have been Deleted. \n"
                + "And " + historyRestCount + "cases MemberSpecHistory Data have been Deleted.";

        MessageResponseDTO messageResponseDTO = new MessageResponseDTO(
                "Reset Success", HttpStatus.OK.value(), message);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(messageResponseDTO, httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/info")
    public ResponseEntity<MessageResponseDTO> getMemberSpecInfo()
            throws NotLoggedInException, NotFoundResultException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        if (loadLoginMember().getMemberSpec() == null) {
            return new ResponseEntity<>(
                    new MessageResponseDTO("MemberSpec has not been made yet."
                            , HttpStatus.BAD_REQUEST.value()
                            , "Please Create MemberSpec First"), httpHeaders, HttpStatus.BAD_REQUEST);
        }

        MemberSpecDTO memberSpecDTO = memberSpecService.findMemberSpecDTOById(loadLoginMember().getMemberSpec().getId());
        List<MemberSpecHistoryDTO> allHistory = memberSpecHistoryService.findAllHistory(loadLoginMember().getId());

        Object firstRecord = "Record is not exist";
        if (allHistory != null) {
            if (allHistory.stream().findFirst().isPresent()) {
                firstRecord = allHistory.stream().findFirst();
            }
        }

        List<ResponseInfoDTO> infoList = new ArrayList<>();
        infoList.add(new ResponseInfoDTO("MemberSpecInfo", memberSpecDTO));
        infoList.add(new ResponseInfoDTO("HistoryFirstRecord", firstRecord));
        infoList.add(new ResponseInfoDTO("HistoryAllRecord", allHistory));


        MessageResponseDTO messageResponseDTO = new MessageResponseDTO("Find Success", HttpStatus.OK.value(),
                infoList);
        return new ResponseEntity<>(messageResponseDTO, httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/info/Routine")
    public ResponseEntity<MessageResponseDTO> getRoutineInfo()
            throws NotLoggedInException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        MemberSpecDTO memberSpecDTO = memberSpecService.findMemberSpecDTOById(loadLoginMember().getMemberSpec().getId());
        RoutineDTO routineDTO = RoutineDTO.builder()
                .nickName(memberSpecDTO.getNickName())
                .mainPartition(memberSpecDTO.getRoutine().getMainPartition())
                .subPartition(memberSpecDTO.getRoutine().getSubPartition())
                .nutrition(memberSpecDTO.getRoutine().getNutrition())
                .BMR(memberSpecDTO.getRoutine().getBMR())
                .build();

        MessageResponseDTO messageResponseDTO = new MessageResponseDTO("Find Success", HttpStatus.OK.value(),
                routineDTO);

        return new ResponseEntity<>(messageResponseDTO, httpHeaders, HttpStatus.OK);
    }
}
