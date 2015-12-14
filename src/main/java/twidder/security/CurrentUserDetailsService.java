package twidder.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import twidder.domain.entity.User;
import twidder.service.UserService;

/**
 * Created by maks on 12/14/15.
 */

@Service
public class CurrentUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public CurrentUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userService.getUserByName(name);
        if(user == null){
            throw  new UsernameNotFoundException(String.format("User with name=%s was not found!",name));
        }

        return new CurrentUser(user);
    }
}
