package de.onetwotree.margaux.service;

import de.onetwotree.margaux.entity.Alert;
import de.onetwotree.margaux.entity.MainEntity;

/**
 * Created by SebUndefined on 02/10/17.
 */
public interface AlertService {
    String addAlertToMainEntity(MainEntity mainEntity, Alert alert);
}
