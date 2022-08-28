package whz.pti.eva.pizza_service_g11.security.service.currentuser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import whz.pti.eva.pizza_service_g11.security.domain.CurrentUser;
import whz.pti.eva.pizza_service_g11.security.domain.User;
import whz.pti.eva.pizza_service_g11.security.service.user.UserService;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(CurrentUserDetailsService.class);
    private UserService userService;

    @Autowired
    public CurrentUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CurrentUser loadUserByUsername(String email) throws UsernameNotFoundException {
//    	log.debug("Authenticating user with email={}", email.replaceFirst("@.*", "@***"));
        User user = 
        		userService.getUserByEmail(email).orElseThrow(() -> 
        		new UsernameNotFoundException("User with email= " + email + " cannot be not found"));
        
        return new CurrentUser(user);
    }



}
