package com.my.ghost.admin.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.my.ghost.common.entity.admin.GhostResponseEntity;

/**
 * 管理员信息接口
 * @author l
 *
 */
@RestController
@RequestMapping("admin")
public class AdminController {

    @SuppressWarnings("rawtypes")
    @PostMapping("/login")
    public GhostResponseEntity login(@RequestParam(value = "username") String username,@RequestParam(value = "password")String password) {
        return new GhostResponseEntity(400, "??");
    }
}
