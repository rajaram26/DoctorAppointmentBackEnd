package doctor.web.repository;

import doctor.web.model.DoctorDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorDetailsRepository extends JpaRepository<DoctorDetails, String> {

    int deleteDoctorDetailsByDoctorId(Long doctorId);
}
