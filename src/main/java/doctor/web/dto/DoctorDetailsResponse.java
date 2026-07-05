package doctor.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DoctorDetailsResponse {

    private Long doctorId;
    private Long userId;
    private String specialization;
    private int yearsOfExperience;
    private Long consultationFee;

}
