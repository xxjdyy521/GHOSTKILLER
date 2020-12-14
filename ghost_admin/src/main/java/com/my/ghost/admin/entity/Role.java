package com.my.ghost.admin.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.ToString;

/**
 * 角色表
 * @author l
 *
 */
@Entity
@Table(name = "role")
@Data
/*此属性不进行tostring，防止栈溢出*/
@ToString(exclude = {"authorityList","userList"})
/*不查询此属性，防止栈溢出*/
@JsonIgnoreProperties(value = "userList")
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String roleId;

    @Column(name = "name")
    private String name;

    @Column(name = "authorities")
    private String authorities;

    @Column(name = "role_describe")
    private String role_Describe;

    /*与user相关联*/
    /*@LazyCollection用于不能重复使用fetch = FetchType.EAGER*/
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(mappedBy = "roleList")
    private List<User> userList;

    /*与Authority相关联*/
    @ManyToMany(targetEntity = Authority.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "role_authority",
            joinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id",referencedColumnName = "authority_id")})
    private List<Authority> authorityList;

}
