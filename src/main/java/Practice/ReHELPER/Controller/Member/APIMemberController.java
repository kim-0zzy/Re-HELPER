package Practice.ReHELPER.Controller.Member;


import Practice.ReHELPER.Config.LoggedMemberHolder;
import Practice.ReHELPER.Controller.Member.Form.ChangePasswordForm;
import Practice.ReHELPER.Controller.Member.Form.SighUpMemberForm;
import Practice.ReHELPER.DTO.MemberDTO;
import Practice.ReHELPER.DTO.MessageResponseDTO;
import Practice.ReHELPER.DTO.SimpleGymInfoDTO;
import Practice.ReHELPER.Entity.Member;
import Practice.ReHELPER.Exception.ExistMemberException;
import Practice.ReHELPER.Exception.NotFoundResultException;
import Practice.ReHELPER.Exception.NotLoggedInException;
import Practice.ReHELPER.Exception.PasswordException;
import Practice.ReHELPER.SearchAPI.DTO.ItemDTO;
import Practice.ReHELPER.SearchAPI.DTO.SearchDTO;
import Practice.ReHELPER.SearchAPI.Util.WebClientUtil;
import Practice.ReHELPER.Service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/apiMember")
public class APIMemberController {

    private final MemberService memberService;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    private final WebClientUtil webClientUtil;
    private final LoggedMemberHolder loggedMemberHolder;

    public Member loadLoginMember() throws NotLoggedInException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().equals("ROLE_ANONYMOUS") || authentication.getPrincipal().equals("anonymousUser")) {
            throw new NotLoggedInException("Not Logged in Yet");
        }
        return loggedMemberHolder.getLoggedMember().get(authentication.getName());
    }

    @PostMapping("/signUp")
    public ResponseEntity<MessageResponseDTO> signUp(@RequestBody SighUpMemberForm sighUpMemberForm)
            throws ExistMemberException {
        if (memberService.validToNotDuplicatedMember(sighUpMemberForm.getUsername())) {
            throw new ExistMemberException("이미 존재하는 회원입니다. 다른 ID를 사용해주세요.");
        }
        Member member = new Member(sighUpMemberForm.getNickname(), sighUpMemberForm.getUsername(),
                passwordEncoder.encode(sighUpMemberForm.getPassword()), sighUpMemberForm.getAddress());

        memberService.signIn(member);
        MemberDTO memberDTO = memberService.buildMemberDTO(member);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(new MessageResponseDTO("SignUp Success", HttpStatus.CREATED.value(), memberDTO), httpHeaders, HttpStatus.CREATED);
    }

    @GetMapping("/findOne")
    public ResponseEntity<MessageResponseDTO> findOneMember(@RequestParam String memberName)
            throws NotFoundResultException {
        MemberDTO memberDTO = memberService.findByUsername(memberName);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return new ResponseEntity<>(new MessageResponseDTO("Find Success", HttpStatus.OK.value(), memberDTO), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<MessageResponseDTO> findAllMember()
            throws NotFoundResultException {
        List<MemberDTO> memberDTOList = memberService.findAllMembers();

        if (memberDTOList.isEmpty()) {
            throw new NotFoundResultException("List is Empty");
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return new ResponseEntity<>(new MessageResponseDTO("Find Success", HttpStatus.OK.value(), memberDTOList), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/loggedMember")
    public ResponseEntity<MessageResponseDTO> loggedMember()
            throws NotLoggedInException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        Member member = loadLoginMember();
        MemberDTO memberDTO = memberService.buildMemberDTO(member);

        return new ResponseEntity<>(new MessageResponseDTO("Find Success", HttpStatus.OK.value(),
                memberDTO), httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<MessageResponseDTO> changePassword(@Valid @RequestBody ChangePasswordForm changePasswordForm)
            throws NotLoggedInException, PasswordException {

        String message = memberService.updatePassword(loadLoginMember(), changePasswordForm.getPresentPassword(), changePasswordForm.getChangePassword());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return new ResponseEntity<>(new MessageResponseDTO("Update Success", HttpStatus.OK.value(),
                message), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/nearByGym5")
    public ResponseEntity<MessageResponseDTO> nearByGymList5() throws NotLoggedInException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        SearchDTO searchDTO = webClientUtil.searchData(loadLoginMember().getAddress());

        List<ItemDTO> itemDTOList = searchDTO.getItems();
        List<SimpleGymInfoDTO> simpleGymInfoDTOList = new ArrayList<>();

        for (ItemDTO itemDTO : itemDTOList) {
            SimpleGymInfoDTO simpleGymInfoDTO = SimpleGymInfoDTO.builder()
                    .title(itemDTO.getTitle())
                    .category(itemDTO.getCategory())
                    .address(itemDTO.getAddress())
                    .roadAddress(itemDTO.getRoadAddress())
                    .link(itemDTO.getLink())
                    .build();
            simpleGymInfoDTOList.add(simpleGymInfoDTO);
        }

        return new ResponseEntity<>(new MessageResponseDTO("Find Success", HttpStatus.OK.value(),
                simpleGymInfoDTOList), httpHeaders, HttpStatus.OK);
    }
}
