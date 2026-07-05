package doctor.web.service.ServiceImpl;

import doctor.web.dto.AddNewAppointmentRequest;
import doctor.web.dto.AppointmentHistoryResponse;
import doctor.web.model.Appointment;
import doctor.web.model.MasterTimeSlots;
import doctor.web.repository.AppointmentRepository;
import doctor.web.service.AppointmentService_1;
import doctor.web.utils.AppointmentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class AppointmentService implements AppointmentService_1 {

    private final AppointmentRepository appointmentRepository;
    private final TimeSlotService timeSlotService;

    @Override
    public Appointment bookAppointment(AddNewAppointmentRequest newAppointmentRequest) {
        MasterTimeSlots timeSlot = timeSlotService.getTimeSlotBySlotId(newAppointmentRequest.getSlot_id());
        Appointment appointment = new Appointment(
                newAppointmentRequest.getPatientUserId(),
                newAppointmentRequest.getDoctorId(),
                newAppointmentRequest.getAppointmentDate(),
                timeSlot,
                AppointmentStatus.valueOf(newAppointmentRequest.getStatus().toUpperCase(Locale.ROOT)),
                newAppointmentRequest.getPrescriptionText(),
                newAppointmentRequest.getCreatedAt()
        );
        return appointmentRepository.save(appointment);
    }

    @Override
    public List<AppointmentHistoryResponse> getAppointmentHistory(Long patientUserId) {
        List<Appointment> appointmentList = appointmentRepository.getAppointmentsByPatientUserId(patientUserId);
        List<AppointmentHistoryResponse> historyResponses = appointmentList.stream()
                .map(this::convertToHistoryDTO)
                .toList();

        if (appointmentList.isEmpty()) {
            throw new RuntimeException("Appointment history is empty");
        }
        return historyResponses;
    }

    @Override
    public void updateAppointmentStatus(Long appointmentId, String status) {
        int updatedRows = appointmentRepository.updateStatusByAppointmentId(AppointmentStatus.valueOf(status.toUpperCase(Locale.ROOT)), appointmentId);
        if (updatedRows == 0) {
            throw new RuntimeException(String.format("Appointment ID with id - %s not found", appointmentId));
        }
    }

    private AppointmentHistoryResponse convertToHistoryDTO(Appointment appointment) {
        return new AppointmentHistoryResponse(
                appointment.getAppointmentId(),
                appointment.getPatientUserId(),
                appointment.getDoctorId(),
                appointment.getAppointmentDate(),
                appointment.getTimeSlot().getDisplayLabel(),
                appointment.getStatus().name(),
                appointment.getPrescriptionText(),
                appointment.getCreatedAt()
        );
    }
}
