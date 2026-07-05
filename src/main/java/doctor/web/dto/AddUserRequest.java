package doctor.web.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class AddUserRequest {
    private String name;
    private String email;
    private String mobile;
    private String role;
    private String password;
    private Timestamp createdAt;
}
