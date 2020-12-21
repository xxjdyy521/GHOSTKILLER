package com.my.ghost.admin.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 菜单目录表--用于构建树
 */
@Data
@Entity
@Table(name = "catalog_tb")
public class Catalog {
    /*主键*/
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    /*目录名称*/
    @Column(name = "catalog_name")
    private String catalogName;

    /*唯一标识key*/
    @Column(name = "key")
    private String key;

    /*显示状态---》true-显示 / false-隐藏*/
    @Column(name = "status")
    private Boolean status;

    /*创建时间*/
    @Column(name = "create_time")
    private Date createTime;

    /*更新时间*/
    @Column(name = "update_time")
    private Date updateTime;

    /*路由id*/
    @Column(name = "route_id")
    private String routeId;

    /*子节点id*/
    @Column(name = "children_ids")
    private String[] childrenIds;

    /*父id*/
    @Column(name = "parent_id")
    private String parentId;
}
