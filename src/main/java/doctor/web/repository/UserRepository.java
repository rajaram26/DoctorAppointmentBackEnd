package doctor.web.repository;

import doctor.web.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserProfile, String> {

    @Query(value = "SELECT u FROM UserProfile u WHERE u.email =:email")
    UserProfile getUserProfileByEmail(@Param("email") String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE UserProfile u SET u.password =:password WHERE u.userId =: userId")
    void updatePassword(@Param("userId") Long userId, @Param("password") String password);

    UserProfile getUserProfilesByUserId(Long userId);
}
