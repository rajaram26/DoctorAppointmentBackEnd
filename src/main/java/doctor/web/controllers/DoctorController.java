package doctor.web.controllers;

import doctor.web.dto.AddDoctorDetailsRequest;
import doctor.web.dto.ApiResponse;
import doctor.web.dto.DoctorDetailsResponse;
import doctor.web.model.DoctorDetails;
import doctor.web.service.ServiceImpl.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping("/addDoctor")
    public ResponseEntity<ApiResponse<DoctorDetails>> addDoctor(@RequestBody AddDoctorDetailsRequest addDoctorDetailsRequest) {
        try {
            doctorService.addDoctorDetails(addDoctorDetailsRequest);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(201, true, "New doctor is added successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(400, false, e.getLocalizedMessage(), null));
        }
    }

    @PostMapping("/addTimeSlot")
    public ResponseEntity<ApiResponse<DoctorDetails>> addTimeSlot(@RequestParam("timeSlot") String timeSlot) {
        try {
            doctorService.addMasterTimeSlot(timeSlot);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(201, true, "Time slot is added successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(400, false, e.getLocalizedMessage(), null));
        }
    }

    @GetMapping("/getAllDoctors")
    public ResponseEntity<ApiResponse<List<DoctorDetailsResponse>>> getDoctorList() {
        List<DoctorDetailsResponse> doctorDetails = doctorService.getAllDoctor();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(200, true, "Fetched all doctors successfully", doctorDetails));
    }

    @DeleteMapping("/deleteDoctor/{doctorId}")
    public ResponseEntity<ApiResponse<DoctorDetails>> deleteDoctor(@PathVariable Long doctorId) {
        try {
            doctorService.removeDoctor(doctorId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse<>(200, true, "Doctor deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(400, false, e.getLocalizedMessage(), null));
        }
    }

}
