package GDSCSCH.cherry.global.utils.user;


import GDSCSCH.cherry.domain.user.domain.User;
import GDSCSCH.cherry.domain.user.domain.repository.UserRepository;
import GDSCSCH.cherry.global.exception.UserNotFoundException;
import GDSCSCH.cherry.global.utils.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserUtilsImpl implements UserUtils {

    private final UserRepository userRepository;

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByUserEmail(email).orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    @Override
    public User getUserFromSecurityContext() {
        String currentUserEmail = SecurityUtils.getCurrentUserId();
        User user = getUserByEmail(currentUserEmail);
        return user;
    }
}
