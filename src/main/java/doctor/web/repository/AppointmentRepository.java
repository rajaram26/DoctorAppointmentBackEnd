package doctor.web.repository;

import doctor.web.model.Appointment;
import doctor.web.utils.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {

    @Query("SELECT a FROM Appointment a join fetch a.timeSlot where a.patientUserId = :patientUserId")
    List<Appointment> getAppointmentsByPatientUserId(@Param("patientUserId") Long patientUserId);

    @Query("update Appointment a set a.status = :status where a.appointmentId = :appointmentId")
    @Modifying
    @Transactional
    int updateStatusByAppointmentId(@Param("status") AppointmentStatus status, @Param("appointmentId") Long appointmentId);
}
