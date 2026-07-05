package doctor.web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class MasterTimeSlots {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long slotId;
    @NonNull
    private String displayLabel;

}
