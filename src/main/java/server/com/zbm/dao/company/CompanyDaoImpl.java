package server.com.zbm.dao.company;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import server.com.zbm.entity.Company;
import java.util.List;

@Repository
public class CompanyDaoImpl implements CompanyDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }

    @Override
    public Company load(Long id) {
        return null;
    }

    @Override
    public Company get(Long id) {
        return null;
    }

    @Override
    public List<Company> findAll(Company entity, int begin, int pageSize) {
        String hql = "from Company where 1=1";
        hql = getHqlWhere(entity, hql);
        Query query = getCurrentSession().createQuery(hql);
        query.setFirstResult(begin);
        query.setMaxResults(pageSize);
        getCurrentSession().close();
        return query.list();
    }

    @Override
    public void persist(Company entity) {

    }

    @Override
    public Long save(Company entity) {
        return null;
    }

    @Override
    public void saveOrUpdate(Company entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void flush() {

    }

    @Override
    public int getTotalCount() {

        //2.定义查询总条数hql语句
        String hqlcount = "select count(*) from Company";
        //3.利用Session创建Query对象
        Query querycount = getCurrentSession().createQuery(hqlcount);

        //4.获取总条数(返回单行数据uniqueResult())
        Integer totalCount = Integer.parseInt(querycount.uniqueResult().toString());
        getCurrentSession().close();
        return totalCount;
    }

    //hql查询条件拼装方法
    private String getHqlWhere(Company company, String hql) {
        if (company != null) {
            if (company.getName() != null && !company.getName().isEmpty()) {
                hql += " and c_name like '%" + company.getName() + "%'";
            }
        }
        return hql;
    }

}
