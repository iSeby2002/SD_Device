package demo.device.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    private String message;
    private UUID deviceId;
    private String description;
    private String address;
    private Long consumption;
    private UUID personId;
}
