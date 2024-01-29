package Practice.ReHELPER.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MemberDTO {
    private String nickName;
    private String username;
    private String role;
}
