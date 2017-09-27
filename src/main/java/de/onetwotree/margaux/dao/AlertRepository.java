package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by SebUndefined on 27/09/17.
 */
@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
}
