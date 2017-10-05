package de.onetwotree.margaux.service;

import de.onetwotree.margaux.Enum.AlertStatus;
import de.onetwotree.margaux.dao.AlertRepository;
import de.onetwotree.margaux.dao.MainEntityRepository;
import de.onetwotree.margaux.entity.Alert;
import de.onetwotree.margaux.entity.MainEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * Created by SebUndefined on 02/10/17.
 */

@Service("AlertService")
public class AlertServiceImpl implements AlertService {

    @Autowired
    AlertRepository alertRepository;
    @Autowired
    MainEntityRepository mainEntityRepository;

    @Override
    public List<Alert> findLast10ByMainEntityId(Long idMainEntity, AlertStatus alertStatus) {
        return alertRepository.findFirst10ByMainEntityIdAndStatusOrderByDateDesc(idMainEntity, alertStatus);
    }

    @Override
    public String addAlertToMainEntity(MainEntity mainEntity, Alert alert) {
        try {
            alert.setMainEntity(mainEntity);
            mainEntity.getAlerts().add(alert);
            mainEntityRepository.save(mainEntity);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
        }
        String className = mainEntity.getClass().getSimpleName();
        String redirectUrl = "/";
        switch (className) {
            case "MainCompany":
                redirectUrl = "/maincompany/view/" + mainEntity.getId();
                break;
            case "Company":
                redirectUrl = "/company/view/" + mainEntity.getId();
                break;
            case "Project":
                redirectUrl = "/project/view/" + mainEntity.getId();
                break;
            case "Plot":
                redirectUrl = "/plot/view/" + mainEntity.getId();
                break;
            case "Harvest":
                redirectUrl = "/plot/view/" + mainEntity.getId();
                break;
        }
        return redirectUrl;
    }



}
