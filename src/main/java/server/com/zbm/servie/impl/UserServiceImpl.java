package server.com.zbm.servie.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.com.zbm.dao.UserDao;
import server.com.zbm.dao.UserRepository;
import server.com.zbm.entity.User;
import server.com.zbm.servie.IUserService;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User load(Long id) {
        return userRepository.load(id);
    }

    @Override
    public User get(Long id) {
        return userRepository.get(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
