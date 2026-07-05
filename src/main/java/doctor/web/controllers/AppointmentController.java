package doctor.web.controllers;

import doctor.web.dto.AddNewAppointmentRequest;
import doctor.web.dto.ApiResponse;
import doctor.web.dto.AppointmentHistoryResponse;
import doctor.web.dto.AppointmentStatusUpdateRequest;
import doctor.web.model.Appointment;
import doctor.web.service.ServiceImpl.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/bookAppointment")
    public ResponseEntity<ApiResponse<Appointment>> bookAppointment(@RequestBody AddNewAppointmentRequest newAppointmentRequest) {
        try {
            Appointment savedAppointment = appointmentService.bookAppointment(newAppointmentRequest);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(201, true, "New Appointment is booked successfully", savedAppointment));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(400, false, e.getLocalizedMessage(), null));
        }
    }

    @GetMapping("/getAppointmentHistory/{patientId}")
    public ResponseEntity<ApiResponse<List<AppointmentHistoryResponse>>> getAppointmentHistory(@PathVariable Long patientId) {
        try {
            List<AppointmentHistoryResponse> appointmentList = appointmentService.getAppointmentHistory(patientId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse<>(200, true, "Appointment history is fetched successfully", appointmentList));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(400, false, e.getLocalizedMessage(), null));
        }
    }

    @PatchMapping("/updateAppointmentStatus")
    public ResponseEntity<ApiResponse<Appointment>> updateAppointmentStatus(@RequestBody AppointmentStatusUpdateRequest request) {
        try {
            appointmentService.updateAppointmentStatus(request.getAppointmentId(), request.getStatus());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse<>(200, true, "Appointment status is updated successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(400, false, e.getLocalizedMessage(), null));
        }
    }

}
