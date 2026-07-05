package doctor.web.service.ServiceImpl;

import doctor.web.dto.AddUserRequest;
import doctor.web.model.UserProfile;
import doctor.web.repository.UserRepository;
import doctor.web.service.UserService_1;
import doctor.web.utils.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class UserService implements UserService_1 {

    private final UserRepository userRepository;

    @Override
    public UserProfile addUser(AddUserRequest addUserRequest) {
        UserProfile userProfile = new UserProfile(
                addUserRequest.getName(),
                addUserRequest.getEmail(),
                addUserRequest.getMobile(),
                Roles.valueOf(addUserRequest.getRole().toUpperCase(Locale.ROOT)),
                addUserRequest.getPassword(),
                addUserRequest.getCreatedAt()
        );
        return userRepository.save(userProfile);
    }

    @Override
    public void authenticateUser(String email, String password) {
        UserProfile userProfile = userRepository.getUserProfileByEmail(email);
        if (!userProfile.getPassword().equals(password)) {
            throw new RuntimeException("Email / Password is incorrect");
        }
    }

    @Override
    public void changePassword(String email, String oldPassword, String newPassword) {
        UserProfile userProfile = userRepository.getUserProfileByEmail(email);
        if (userProfile.getPassword().equalsIgnoreCase(oldPassword)) {
            userRepository.updatePassword(userProfile.getUserId(), newPassword);
        } else {
            throw new RuntimeException("Old password is not valid, Enter valid password");
        }
    }

    @Override
    public UserProfile getUserById(Long userId) {
        return userRepository.getUserProfilesByUserId(userId);
    }
}
