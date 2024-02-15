package Practice.ReHELPER.SearchAPI.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchDTO {

    private String lastBuildDate;
    private Integer total;
    private Integer start;
    private Integer display;
    private List<ItemDTO> items;

}
