package doctor.web.service;

import doctor.web.dto.AddNewAppointmentRequest;
import doctor.web.dto.AppointmentHistoryResponse;
import doctor.web.model.Appointment;

import java.util.List;

public interface AppointmentService_1 {

    Appointment bookAppointment(AddNewAppointmentRequest appointment);

    List<AppointmentHistoryResponse> getAppointmentHistory(Long patientUserId);

    void updateAppointmentStatus(Long appointmentId, String status);
}
