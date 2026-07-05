package doctor.web.service.ServiceImpl;

import doctor.web.model.MasterTimeSlots;
import doctor.web.repository.TimeSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;

    public void addMasterTimeSlot(String timeSlot) {
        timeSlotRepository.save(new MasterTimeSlots(timeSlot));
    }

    public MasterTimeSlots getTimeSlotBySlotId(Long slotId) {
        return timeSlotRepository.getMasterTimeSlotsBySlotId(slotId);
    }

}
