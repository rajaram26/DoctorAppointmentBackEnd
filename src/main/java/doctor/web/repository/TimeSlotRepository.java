package doctor.web.repository;

import doctor.web.model.MasterTimeSlots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeSlotRepository extends JpaRepository<MasterTimeSlots, Long> {
    MasterTimeSlots getMasterTimeSlotsBySlotId(Long slotId);
}
