package com.my.ghost.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.my.ghost.admin.entity.User;
import com.my.ghost.admin.service.UserService;
import com.my.ghost.common.entity.admin.GhostResponseEntity;
import com.my.ghost.common.entity.admin.DataPageInfo;
import com.my.ghost.common.entity.admin.QueryResponseEntity;

/**
 * user信息接口
 * @author l
 *
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userServiceImp;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @PostMapping("/getList")
    public GhostResponseEntity<QueryResponseEntity<User>> getUserList(@RequestBody DataPageInfo pageInfo){
        try {
            QueryResponseEntity<User> res = userServiceImp.getUserList(pageInfo);
            return new GhostResponseEntity(200,"查询成功！",res);
        } catch (Exception e) {
            return new GhostResponseEntity(400,"查询失败");
        }
    }

    @SuppressWarnings("rawtypes")
    @PostMapping(value = "/save")
    public GhostResponseEntity userSave(@RequestBody User user) {
        User res = userServiceImp.saveUserInfo(user);
        if(res != null) {
            return new GhostResponseEntity<>(200, "注册成功！");
        }else {
            return new GhostResponseEntity<>(400,"注册失败");
        }
    }
}
