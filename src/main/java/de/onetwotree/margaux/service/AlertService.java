package de.onetwotree.margaux.service;

import de.onetwotree.margaux.Enum.AlertStatus;
import de.onetwotree.margaux.entity.Alert;
import de.onetwotree.margaux.entity.MainEntity;

import java.util.List;

/**
 * Created by SebUndefined on 02/10/17.
 */
public interface AlertService {


    List<Alert> findLast10ByMainEntityId(Long idMainEntity, AlertStatus alertStatus);

    String addAlertToMainEntity(MainEntity mainEntity, Alert alert);
}
