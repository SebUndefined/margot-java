package de.onetwotree.margaux.service;

import de.onetwotree.margaux.dto.UserDTO;
import de.onetwotree.margaux.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Created by SebUndefined on 21/07/17.
 */
public interface UserService {


    Page<User> findAll(PageRequest pageRequest);

    User save(UserDTO userDTO);

    User findByUsername(String username);

    User getUser(Long id);

    List<User> getAllUsers();
}
