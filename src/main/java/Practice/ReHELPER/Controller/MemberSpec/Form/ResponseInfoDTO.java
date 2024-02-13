package Practice.ReHELPER.Controller.MemberSpec.Form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseInfoDTO {

    private String resultName;
    private Object result;
}
