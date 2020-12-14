package com.my.ghost.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.my.ghost.common.entity.admin.GhostResponseEntity;
/**
 * 登录验证
 * @author l
 *
 */
@RestController
@RequestMapping(value = "authority")
public class AuthorityController {

    @SuppressWarnings("rawtypes")
    @PostMapping(value = "/login")
    public GhostResponseEntity login(@RequestParam(value = "username")String username,@RequestParam(value = "password")String password) {
        return new GhostResponseEntity(400, "");
    }

    @SuppressWarnings("rawtypes")
    @GetMapping(value = "/loginPage")
    public GhostResponseEntity login() {
        return new GhostResponseEntity(400,"请登录！");
    }
}
