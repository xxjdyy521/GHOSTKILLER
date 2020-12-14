package com.my.ghost.admin.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.my.ghost.admin.dao.UserJpaRepos;
import com.my.ghost.admin.entity.User;
import com.my.ghost.admin.service.UserService;
import com.my.ghost.common.entity.admin.DataPageInfo;
import com.my.ghost.common.entity.admin.QueryResponseEntity;
import com.my.ghost.common.utils.mq.activemq.ActiveMQUtils;


@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserJpaRepos userRepository;

    @Override
    public QueryResponseEntity<User> getUserList(DataPageInfo<User> pageInfo) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("username",GenericPropertyMatchers.contains())
                .withMatcher("telephone", GenericPropertyMatchers.contains())
                .withMatcher("email",GenericPropertyMatchers.contains())
                .withMatcher("birthDate", GenericPropertyMatchers.contains())
                .withMatcher("identityNum",GenericPropertyMatchers.contains())
                .withMatcher("describe",GenericPropertyMatchers.contains())
                .withMatcher("nickname",GenericPropertyMatchers.contains())
                .withIgnorePaths("id");

        /*LinkHashMap转为实体类，T类型参数无法直接转为实体类类型*/
        String jsonStr = JSON.toJSONString(pageInfo.getData());
        JSONObject obj = JSONObject.parseObject(jsonStr);
        User user = JSONObject.toJavaObject(obj, User.class);

        /*进行匹配*/
        Example<User> example = Example.of(user,matcher);
        int page = pageInfo.getCurrentPage();
        int size = pageInfo.getPageSize();
        if (page <= 0) {
            page = 1;
        }
        if (size <= 0) {
            size = 10;
        }
        page = page - 1;

        /*排序设计*/
        Sort sort = new Sort(Sort.Direction.DESC,"updateTime");
        Pageable pageAble = PageRequest.of(page, size,sort);
        Page<User> pageList = userRepository.findAll(example,pageAble);

        /*封装返回结果*/
        QueryResponseEntity<User> queryRes = new QueryResponseEntity<>();
        queryRes.setList(pageList.getContent());
        queryRes.setTotal(pageList.getTotalElements());
        return queryRes;
    }

    @Override
    public User saveUserInfo(User user) {
        return null;
        //return userRepository.save(user);
    }
}

