package Practice.ReHELPER.DTO;

import lombok.Data;

@Data
public class MessageResponseDTO {

    private String message;
    private Integer statusCode;
    private Object object;

    public MessageResponseDTO(String message, Integer statusCode, Object object) {
        this.message = message;
        this.statusCode = statusCode;
        this.object = object;
    }

    public MessageResponseDTO(String message, Integer statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
