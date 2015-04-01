package core.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.meesig.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by byungwoo on 15. 2. 2..
 * Meesig
 */
public class AdminDetails implements UserDetails {

	private static final long serialVersionUID = -689784853761116500L;
	private User adminUser;

    public AdminDetails(User adminUser) {
		this.adminUser = adminUser;
	}

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + adminUser.getUser_role()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return adminUser.getUser_password();
    }

    @Override
    public String getUsername() {
        return adminUser.getUser_login_id();
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
    	return adminUser.isUser_enabled();
    }
}
