package com.my.ghost.admin.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.ToString;

/**
 * 权限表
 * @author l
 *
 */
@Entity
@Table(name = "authority")
@Data
/*此属性不进行tostring，防止栈溢出*/
@ToString(exclude = "roleList")
/*不查询此属性，防止栈溢出*/
@JsonIgnoreProperties(value = {"roleList"})
public class Authority implements GrantedAuthority{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "authority_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String authorityId;

    @Column(name = "name")
    private String name;

    @Column(name = "content")
    private String content;

    @Column(name = "authority_describe")
    private String authorityDescribe;

    @Column(name = "roles")
    private String roles;

    @Column(name = "parentId")
    private String parentId;

    /*角色权限*/
    @ManyToMany(mappedBy = "authorityList",fetch = FetchType.EAGER)
    private List<Role> roleList;

    @Override
    public String getAuthority() {
        return this.name;
    }
}
