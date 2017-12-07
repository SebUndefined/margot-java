package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.UserCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

/**
 * Created by SebUndefined on 22/08/17.
 */
@Repository
public interface UserRepository extends JpaRepository<UserCustom, Long> {


    UserCustom findByUsername(String username);
}
