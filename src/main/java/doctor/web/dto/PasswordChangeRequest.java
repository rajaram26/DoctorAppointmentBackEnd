package doctor.web.dto;

import lombok.Data;

@Data
public class PasswordChangeRequest {
    private String emailAddress;
    private String oldPassword;
    private String newPassword;
}
