package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.Plot;
import de.onetwotree.margaux.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
        Session session = sessionFactory.getCurrentSession();
        System.out.println(session.toString());
        List<Plot> plots = (List<Plot>) session.createCriteria(Plot.class).list();
        return plots;
    }

    @Override
    public void addPlot(Plot plot) {
        Session session = sessionFactory.getCurrentSession();
        User user = plot.getManager();
        if(! session.contains(user)) {
            session.refresh(user);
        }
        session.save(plot);
    }

}
