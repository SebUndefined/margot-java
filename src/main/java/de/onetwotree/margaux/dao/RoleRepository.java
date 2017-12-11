package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by SebUndefined on 03/10/17.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findById(String id);
}
