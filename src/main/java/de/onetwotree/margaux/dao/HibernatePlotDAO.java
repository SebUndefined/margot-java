package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.Plot;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SebUndefined on 29/07/17.
 */
@Repository("plotDao")
public class HibernatePlotDAO implements PlotDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Plot> getAllPlot() {
        return (List<Plot>) sessionFactory.getCurrentSession().createCriteria(Plot.class).list();
    }

}
