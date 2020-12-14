package com.my.ghost.admin.service;

import com.my.ghost.admin.entity.User;
import com.my.ghost.common.entity.admin.DataPageInfo;
import com.my.ghost.common.entity.admin.QueryResponseEntity;

public interface UserService {
    public QueryResponseEntity<User> getUserList(DataPageInfo<User> pageInfo);

    public User saveUserInfo(User user);
}

