package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by SebUndefined on 17/07/17.
 */
@Repository("userDao")
public class HibernateUserDAO implements UserDao {

    //@PersistenceContext
    //private EntityManager entityManager;
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User getUser(long id) {
        return (User)sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public  List<User> getAllUsers() {
        return (List<User>)sessionFactory.getCurrentSession().createCriteria(User.class).list();
    }

    /*@Override
    public List<User> findAll() {
        Query query = entityManager.createQuery("FROM User");
        List<User> users = query.getResultList();
        return users;
    }
    @Override
    public User findUser(Long id) {
        Session session = entityManager.unwrap(Session.class);
        User user1 = entityManager.find(User.class, id);
        user1.setEmail("test@sebundefined.fr");
        entityManager.merge(user1);
        user1 = entityManager.find(User.class, id);
        return user1;
    }*/
}
