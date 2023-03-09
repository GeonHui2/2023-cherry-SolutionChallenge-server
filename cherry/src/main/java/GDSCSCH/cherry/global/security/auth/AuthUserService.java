package GDSCSCH.cherry.global.security.auth;

import GDSCSCH.cherry.domain.admin.domain.Admin;
import GDSCSCH.cherry.domain.admin.domain.repository.AdminRepository;
import GDSCSCH.cherry.domain.user.domain.User;
import GDSCSCH.cherry.domain.user.domain.repository.UserRepository;
import GDSCSCH.cherry.global.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthUserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    @Override
    public AuthUser loadUserByUsername(String userEmail) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUserEmail(userEmail);
        Optional<Admin> admin = adminRepository.findByAdminEmail(userEmail);

        if (user.isEmpty() && admin.isEmpty()) {
            throw new UsernameNotFoundException("회원이 존재하지 않습니다.");
        } else if (user.isEmpty()) {
            return new AuthUser(admin.get().getId(), admin.get().getAdminEmail(), admin.get().getRole());
        }
        else return new AuthUser(user.get().getId(), user.get().getUserEmail(), user.get().getRole());
    }


}
