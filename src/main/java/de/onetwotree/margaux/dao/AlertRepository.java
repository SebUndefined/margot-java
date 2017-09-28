package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.Enum.AlertStatus;
import de.onetwotree.margaux.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SebUndefined on 27/09/17.
 */
@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {

    List<Alert> findFirst10ByMainEntityIdAndStatusOrderByDateDesc(Long id, AlertStatus alertStatus );
}
