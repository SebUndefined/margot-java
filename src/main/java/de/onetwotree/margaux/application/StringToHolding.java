package de.onetwotree.margaux.application;

import de.onetwotree.margaux.dao.HoldingRepository;
import de.onetwotree.margaux.entity.Holding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by SebUndefined on 21/07/17.
 */
public class StringToHolding implements Converter<String, Holding> {

    @Autowired
    HoldingRepository holdingRepository;

    public Holding convert(String id) {
        Holding holding = holdingRepository.findOne(Long.valueOf(id));
        return holding;
    }
}
