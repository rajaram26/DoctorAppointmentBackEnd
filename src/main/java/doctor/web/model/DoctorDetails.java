package doctor.web.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId", nullable = false)
    private UserProfile userProfile;
    private String specialization;
    private int yearsOfExperience;
    private Long consultationFee;

    public DoctorDetails(UserProfile userProfile, String specialization, int yearsOfExperience, Long consultationFee) {
        this.userProfile = userProfile;
        this.specialization = specialization;
        this.yearsOfExperience = yearsOfExperience;
        this.consultationFee = consultationFee;
    }

}
