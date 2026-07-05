package doctor.web.service.ServiceImpl;


import doctor.web.dto.AddDoctorDetailsRequest;
import doctor.web.dto.DoctorDetailsResponse;
import doctor.web.model.DoctorDetails;
import doctor.web.model.UserProfile;
import doctor.web.repository.DoctorDetailsRepository;
import doctor.web.service.DoctorService_1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService implements DoctorService_1 {

    private final DoctorDetailsRepository doctorRepository;
    private final UserService userService;
    private final TimeSlotService timeSlotService;

    @Override
    public void addDoctorDetails(AddDoctorDetailsRequest doctorDetailsRequest) {
        UserProfile userProfile = userService.getUserById(doctorDetailsRequest.getUserId());
        DoctorDetails doctorDetails = new DoctorDetails(
                userProfile,
                doctorDetailsRequest.getSpecialization(),
                doctorDetailsRequest.getYearsOfExperience(),
                doctorDetailsRequest.getConsultationFee()
        );
        doctorRepository.save(doctorDetails);
    }

    @Override
    public List<DoctorDetailsResponse> getAllDoctor() {
        List<DoctorDetails> doctorDetails = doctorRepository.findAll();
        return doctorDetails.stream()
                .map(this::convertToDoctorDetailsResponse)
                .toList();
    }

    @Override
    public void removeDoctor(Long doctorId) {
        int updatedRow = doctorRepository.deleteDoctorDetailsByDoctorId(doctorId);
        if (updatedRow != 1) {
            throw new RuntimeException("Doctor with specific id is not available");
        }
    }

    @Override
    public void addMasterTimeSlot(String timeSlot) {
        timeSlotService.addMasterTimeSlot(timeSlot);
    }

    private DoctorDetailsResponse convertToDoctorDetailsResponse(DoctorDetails doctorDetails) {
        return new DoctorDetailsResponse(
                doctorDetails.getDoctorId(),
                doctorDetails.getUserProfile().getUserId(),
                doctorDetails.getSpecialization(),
                doctorDetails.getYearsOfExperience(),
                doctorDetails.getConsultationFee()
        );
    }

}
