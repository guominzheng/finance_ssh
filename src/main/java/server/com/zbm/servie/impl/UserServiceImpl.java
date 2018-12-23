package server.com.zbm.servie.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.com.zbm.dao.user.UserDao;
import server.com.zbm.entity.PageBean.Page;
import server.com.zbm.entity.User;
import server.com.zbm.servie.IUserService;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User load(Long id) {
        return userDao.load(id);
    }

    @Override
    public User get(Long id) {
        return userDao.get(id);
    }

    @Override
    public List<User> findAll(User user, int begin, int pageSize) {
        Page page = new Page();
        page.setPageno(begin);
        page.setTotalpage(userDao.getTotalCount(), pageSize);
        return userDao.findAll(user, page.getStartrow(), page.getPagesize());
    }
}
