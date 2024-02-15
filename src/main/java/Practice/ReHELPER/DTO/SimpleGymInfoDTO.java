package Practice.ReHELPER.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimpleGymInfoDTO {
    private String title;
    private String address;
    private String roadAddress;
    private String category;
    private String link;
}
