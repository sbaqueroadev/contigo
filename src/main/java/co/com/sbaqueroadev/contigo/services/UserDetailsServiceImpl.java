package co.com.sbaqueroadev.contigo.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.com.sbaqueroadev.contigo.dao.ApplicationUserRepository;
import co.com.sbaqueroadev.contigo.model.implementation.ApplicationUser;
import co.com.sbaqueroadev.contigo.model.implementation.Privilege;
import co.com.sbaqueroadev.contigo.model.implementation.Role;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private ApplicationUserRepository applicationUserRepository;

	public UserDetailsServiceImpl(ApplicationUserRepository applicationUserRepository) {
		this.applicationUserRepository = applicationUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
		if (applicationUser == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(applicationUser.getUsername(), applicationUser.getPassword(),
				getAuthorities(applicationUser.getRole()));
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(
			Role role) {

		return getGrantedAuthorities(getPrivileges(role));
	}
	
	private List<String> getPrivileges(Role role) {
		  
        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        collection.addAll(role.getPrivileges());
        for (Privilege item : collection) { 
            privileges.add(item.getName());
        }
        return privileges;
    }
	
	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
