package server.com.zbm.dao;

import server.com.zbm.entity.User;

import java.util.List;

public interface UserDao  {
    List<User> query();
    User load(Long id);
    User get(Long id);
}
