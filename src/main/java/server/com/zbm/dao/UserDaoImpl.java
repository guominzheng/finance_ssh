package server.com.zbm.dao;

import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import server.com.zbm.entity.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<User> query() {
        String hql ="from User";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public User load(Long id) {
        return sessionFactory.getCurrentSession().load(User.class,id);
    }

    @Override
    public User get(Long id) {
        return sessionFactory.getCurrentSession().get(User.class,id);
    }
}
