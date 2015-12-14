package twidder.security;


import org.springframework.security.core.authority.AuthorityUtils;
import twidder.domain.entity.User;

/**
 * Created by maks on 12/14/15.
 */


public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user) {
        super(user.getUsername(), user.getPasswordHash(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }


    public Long getId(){
        return user.getId();
    }

    public User getUser() {
        return user;
    }

    public User.Role getRole() {
        return user.getRole();
    }
}
