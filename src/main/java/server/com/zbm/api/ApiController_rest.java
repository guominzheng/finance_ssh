package server.com.zbm.api;

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.com.zbm.entity.User;
import server.com.zbm.servie.IUserService;


@RestController
@RequestMapping(value="/api/userInfo",produces = "text/html;charset=utf-8")
public class ApiController_rest {

    @Autowired
    private IUserService userService;
    /**
     *
     * @return
     */
    @RequestMapping(value="/getUserInfo",produces = "text/html;charset=utf-8")
    public String getUser(){
        return JSONArray.toJSONString(userService.load(1L));
    }


    @RequestMapping(value="/getUserInfo1",produces = "text/html;charset=utf-8")
    public String getUser1(){
        User user =new User();
        user.setAddress("郑州");
        return JSONArray.toJSONString(userService.findAll());
    }
}
