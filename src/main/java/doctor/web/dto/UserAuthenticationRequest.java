package doctor.web.dto;

import lombok.Data;

@Data
public class UserAuthenticationRequest {
    private String email;
    private String password;
}
