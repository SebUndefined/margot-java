package de.onetwotree.margaux.service;

import de.onetwotree.margaux.dao.UserRepository;
import de.onetwotree.margaux.entity.UserCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

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
        System.out.println(userCustom.getPassword());
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (GrantedAuthority grantedAuthority : userCustom.getAuthorities()){
            System.out.println(grantedAuthority.getAuthority());
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(grantedAuthority.getAuthority());
            grantedAuthorities.add(simpleGrantedAuthority);
            System.out.println(simpleGrantedAuthority);
        }


        //return new org.springframework.security.core.userdetails.UserCustom(userCustom.getUserName(), userCustom.getPassword(), grantedAuthorities);
        return userCustom;
    }
}
