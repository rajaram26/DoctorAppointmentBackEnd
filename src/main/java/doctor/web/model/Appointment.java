package doctor.web.model;

import doctor.web.utils.AppointmentStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;
    private Long patientUserId;
    private Long doctorId;
    private Date appointmentDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "slot_id", referencedColumnName = "slotId", nullable = false)
    private MasterTimeSlots timeSlot;
    private AppointmentStatus status;
    private String prescriptionText;
    private Timestamp createdAt;


    public Appointment(Long patientUserId, Long doctorId, Date appointmentDate, MasterTimeSlots timeSlot, AppointmentStatus status, String prescriptionText, Timestamp createdAt) {
        this.patientUserId = patientUserId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.timeSlot = timeSlot;
        this.status = status;
        this.prescriptionText = prescriptionText;
        this.createdAt = createdAt;
    }
}
