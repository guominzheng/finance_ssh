package server.com.zbm.servie;

import server.com.zbm.entity.User;

import java.util.List;

public interface IUserService {
     User load(Long id);
     User get(Long id);
     List<User> findAll(User user,int begin,int pageSize);
}
