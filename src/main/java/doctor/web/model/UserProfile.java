package doctor.web.model;

import doctor.web.utils.Roles;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class UserProfile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	@NonNull
	private String name;
	@NonNull
	private String email;
	@NonNull
	private String mobile;
	@NonNull
	private Roles role;
	@NonNull
	private String password;
	@NonNull
	private Timestamp createdAt;

}
