package doctor.web.service;

import doctor.web.dto.AddDoctorDetailsRequest;
import doctor.web.dto.DoctorDetailsResponse;

import java.util.List;

public interface DoctorService_1 {

    void addDoctorDetails(AddDoctorDetailsRequest doctorDetails);

    List<DoctorDetailsResponse> getAllDoctor();

    void removeDoctor(Long doctorId);

    void addMasterTimeSlot(String timeSlot);

}
