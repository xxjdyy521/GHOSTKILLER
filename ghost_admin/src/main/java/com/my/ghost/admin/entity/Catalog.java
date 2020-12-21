package com.my.ghost.admin.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 菜单目录表
 */

@Data
@Entity
@Table(name = "catalog_tb")
public class Catalog {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "catalog_name")
    private String catalogName;

    @Column(name = "key")
    private String key;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "route_id")
    private String routeId;

    @Column(name = "children_ids")
    private String[] childrenIds;

    @Column(name = "parent_id")
    private String parentId;
}
