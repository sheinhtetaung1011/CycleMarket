package cycleTest.cycleDemo.security;

import cycleTest.cycleDemo.entity.User;
import cycleTest.cycleDemo.enums.Status;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    @Getter
    private Long id;
    private String username;
    private String password;
    private User user;
    Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(User user) {
        this.id= user.getId();
        this.username = user.getName();
        this.password = user.getPassword();
        this.user = user;
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority(
                user.getUserRole().toString()));
    }




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if (user != null) {
            if (user.getStatus().equals(Status.ACTIVE)) {
                return true;
            }
        }

        return false;
    }

}
