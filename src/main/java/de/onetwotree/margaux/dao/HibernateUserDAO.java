package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by SebUndefined on 17/07/17.
 */
@Repository
@Transactional
public class HibernateUserDAO implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {

    }
    @Override
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
    }
}
