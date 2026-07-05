package doctor.web.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class AddNewAppointmentRequest {

    private Long patientUserId;
    private Long doctorId;
    private Date appointmentDate;
    private Long slot_id;
    private String status;
    private String prescriptionText;
    private Timestamp createdAt;
}
