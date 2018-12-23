package server.com.zbm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import server.com.zbm.entity.User;
import server.com.zbm.servie.IUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService userService;


    @RequestMapping(value = "list", produces = "text/html;charset=utf-8")
    public String list(HttpServletRequest request, User user,Integer pageNum) {
        List<User> users;
        if(pageNum == null ){
            pageNum = 1;
        }
        users = userService.findAll(user, pageNum, 10);
        System.out.println("----------------------->" + users.size());
        request.setAttribute("varList", users);
        return "user/userList";
    }
}
