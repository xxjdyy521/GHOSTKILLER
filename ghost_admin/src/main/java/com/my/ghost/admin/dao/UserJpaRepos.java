package com.my.ghost.admin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.my.ghost.admin.entity.User;

/**
 * user表相关
 * @author l
 *
 */
public interface UserJpaRepos extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    @Query(value = "SELECT * FROM User AS u WHERE u.username = ?1",nativeQuery = true)
    public User getUserByUsername(String username);
}
