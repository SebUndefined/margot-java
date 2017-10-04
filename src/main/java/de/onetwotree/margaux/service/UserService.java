package de.onetwotree.margaux.service;

import de.onetwotree.margaux.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Created by SebUndefined on 21/07/17.
 */
public interface UserService {


    Page<User> findAll(PageRequest pageRequest);

    void save(User user);

    User findByUsername(String username);

    User getUser(Long id);

    List<User> getAllUsers();
}
