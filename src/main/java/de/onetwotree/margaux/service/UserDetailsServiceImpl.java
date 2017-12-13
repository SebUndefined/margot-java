package de.onetwotree.margaux.service;

import de.onetwotree.margaux.dao.UserRepository;
import de.onetwotree.margaux.entity.CustomUserDetails;
import de.onetwotree.margaux.entity.Role;
import de.onetwotree.margaux.entity.UserCustom;
import de.onetwotree.margaux.entityJson.MainEntityView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by SebUndefined on 03/10/17.
 */
@Service("UserDetailsService")
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {
    private final
    UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserCustom userCustom = userRepository.findByUsername(username);
        CustomUserDetails customUserDetails = new CustomUserDetails(
                userCustom.getUsername(),
                userCustom.getPassword(),
                userCustom.getFirstname(),
                userCustom.getLastname(),
                userCustom.getPicture(),
                userCustom.getEmail(),
                userCustom.isEnabled(),
                userCustom.isAccountNonExpired(),
                userCustom.isCredentialsNonExpired(),
                userCustom.isAccountNonLocked(),
                getAuthorities(userCustom)
        );
        //return new org.springframework.security.core.userdetails.UserCustom(userCustom.getUserName(), userCustom.getPassword(), grantedAuthorities);
        return customUserDetails;
    }

    private List<GrantedAuthority> getAuthorities(UserCustom userCustom) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(Role role : userCustom.getRoles()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getId());
            authorities.add(grantedAuthority);
        }

        return authorities;
    }
}
