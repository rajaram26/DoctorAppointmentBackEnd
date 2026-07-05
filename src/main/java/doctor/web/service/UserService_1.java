package doctor.web.service;

import doctor.web.dto.AddUserRequest;
import doctor.web.model.UserProfile;

public interface UserService_1 {

    UserProfile addUser(AddUserRequest userProfile);

    void authenticateUser(String email, String password);

    void changePassword(String email, String oldPassword, String newPassword);

    UserProfile getUserById(Long userId);

}
