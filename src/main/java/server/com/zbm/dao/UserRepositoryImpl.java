package server.com.zbm.dao;

import org.hibernate.query.Query;
import server.com.zbm.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.io.Serializable;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }

    @Override
    public User load(Long id) {

        User user = getCurrentSession().get(User.class,id);
        getCurrentSession().close();
        return user;
    }

    @Override
    public User get(Long id) {
        User user = getCurrentSession().get(User.class,id);
        getCurrentSession().close();
        return user;
    }

    @Override
    public List<User> findAll() {
        Query query= getCurrentSession().createQuery("from User");
        return query.list();
    }

    @Override
    public void persist(User entity) {

    }

    @Override
    public Long save(User entity) {
        return null;
    }

    @Override
    public void saveOrUpdate(User entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void flush() {

    }
}
