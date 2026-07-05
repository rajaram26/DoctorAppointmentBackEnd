package doctor.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
public class AppointmentHistoryResponse {
    private Long appointmentId;
    private Long patientUserId;
    private Long doctorId;
    private Date appointmentDate;
    private String displayLabel;
    private String status;
    private String prescriptionText;
    private Timestamp createdAt;
}
