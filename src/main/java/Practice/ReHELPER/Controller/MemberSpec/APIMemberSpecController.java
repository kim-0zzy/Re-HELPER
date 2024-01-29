package Practice.ReHELPER.Controller.MemberSpec;

import Practice.ReHELPER.Service.MemberService;
import Practice.ReHELPER.Service.MemberSpecHistoryService;
import Practice.ReHELPER.Service.MemberSpecService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apiMemberSpec")
public class APIMemberSpecController {

    private final MemberSpecService memberSpecService;
    private final MemberSpecHistoryService memberSpecHistoryService;
    private final MemberService memberService;


    //    MessageResponseDTO messageResponseDTO = new MessageResponseDTO("Rental Success", HttpStatus.CREATED.value(),
//            responseBorrowDTOList);
//
//    HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(new MediaType("application", "json",StandardCharsets.UTF_8));
//
//        return new ResponseEntity<>(messageResponseDTO, httpHeaders, HttpStatus.OK);

}
