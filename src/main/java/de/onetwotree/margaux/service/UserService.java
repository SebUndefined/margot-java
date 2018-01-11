package de.onetwotree.margaux.service;

import de.onetwotree.margaux.dto.UserDTO;
import de.onetwotree.margaux.entity.UserCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * Created by SebUndefined on 21/07/17.
 */
public interface UserService {


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    Page<UserCustom> findAll(Pageable pageable);

    UserCustom save(UserDTO userDTO);

    UserCustom findByUsername(String username);

    UserCustom getUser(Long id);

    List<UserCustom> getAllUsers();
}
