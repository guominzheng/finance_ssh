package server.com.zbm.dao.user;

import org.hibernate.query.Query;
import server.com.zbm.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }

    @Override
    public User load(Long id) {

        User user = getCurrentSession().get(User.class, id);
        getCurrentSession().close();
        return user;
    }

    @Override
    public User get(Long id) {
        User user = getCurrentSession().get(User.class, id);
        getCurrentSession().close();
        return user;
    }

    @Override
    public List<User> findAll(User user, int begin, int pageSize) {
        String hql = "from User where 1=1";
        hql = getHqlWhere(user,hql);
        Query query = getCurrentSession().createQuery(hql);
        query.setFirstResult(begin);
        query.setMaxResults(pageSize);
        getCurrentSession().close();
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

    //hql查询条件拼装方法
    private String getHqlWhere(User user, String hql) {
        if (user != null) {
            if (user.getUsername() != null && !user.getUsername().isEmpty()) {
                hql += " and username like '%" + user.getUsername() + "%'";
            }
        }
        return hql;
    }

    /**
     * 8.查询总条数
     */
    public int getTotalCount() {

        //2.定义查询总条数hql语句
        String hqlcount = "select count(*) from User";
        //3.利用Session创建Query对象
        Query querycount = getCurrentSession().createQuery(hqlcount);

        //4.获取总条数(返回单行数据uniqueResult())
        Integer totalCount = Integer.parseInt(querycount.uniqueResult().toString());
        getCurrentSession().close();
        return totalCount;
    }

}
