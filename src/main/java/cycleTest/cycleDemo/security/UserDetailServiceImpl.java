package cycleTest.cycleDemo.security;

import cycleTest.cycleDemo.entity.User;
import cycleTest.cycleDemo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Entering LoadUserByUserName method::");
        User user = userRepository.findByName(username).orElseThrow(() -> {
            log.error("UserName Not Found" + username);
            throw new UsernameNotFoundException("User Not Found");
        });

        log.info("User Authenticated Successfully");
        return new CustomUserDetails(user);
    }
}
