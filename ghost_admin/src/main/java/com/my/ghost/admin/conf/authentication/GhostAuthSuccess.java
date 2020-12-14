package com.my.ghost.admin.conf.authentication;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
/**
 * 登录成功操作
 * @author l
 *
 */
@Component
public class GhostAuthSuccess implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        //response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        /*
         * Map<String, String> map = new HashMap<>(); map.put("200","登录成功");
         */
        JSONObject res = new JSONObject();
        res.put("code", 200);
        res.put("msg", "登陆成功！");
        writer.write(res.toString());
    }
}
