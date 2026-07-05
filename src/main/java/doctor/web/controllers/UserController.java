package doctor.web.controllers;

import doctor.web.dto.AddUserRequest;
import doctor.web.dto.ApiResponse;
import doctor.web.dto.PasswordChangeRequest;
import doctor.web.dto.UserAuthenticationRequest;
import doctor.web.model.UserProfile;
import doctor.web.service.ServiceImpl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<ApiResponse<UserProfile>> createUser(@RequestBody AddUserRequest addUserRequest) {
        try {
            UserProfile savedUser = userService.addUser(addUserRequest);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(201, true, "New user was added successfully", savedUser));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(400, false, e.getLocalizedMessage(), null));
        }
    }

    @PatchMapping("/changePassword")
    public ResponseEntity<ApiResponse<UserProfile>> changeUserPassword(@RequestBody PasswordChangeRequest passwordChangeRequest) {
        try {
            userService.changePassword(
                    passwordChangeRequest.getEmailAddress(),
                    passwordChangeRequest.getOldPassword(),
                    passwordChangeRequest.getNewPassword()
            );
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse<>(200, true, "User password is updates successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(400, false, e.getLocalizedMessage(), null));
        }
    }

    @GetMapping("/authenticateUser")
    public ResponseEntity<ApiResponse<UserProfile>> authenticateUser(@RequestBody UserAuthenticationRequest authenticationRequest) {
        try {
            userService.authenticateUser(authenticationRequest.getEmail(), authenticationRequest.getPassword());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse<>(200, true, "User is authenticated successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(400, false, e.getLocalizedMessage(), null));
        }
    }

}
