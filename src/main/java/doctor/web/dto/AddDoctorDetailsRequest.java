package doctor.web.dto;

import lombok.Data;

@Data
public class AddDoctorDetailsRequest {
    private Long userId;
    private String specialization;
    private int yearsOfExperience;
    private Long consultationFee;
}
