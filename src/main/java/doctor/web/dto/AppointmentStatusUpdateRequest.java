package doctor.web.dto;

import lombok.Data;

@Data
public class AppointmentStatusUpdateRequest {
    private Long appointmentId;
    private String status;
}
