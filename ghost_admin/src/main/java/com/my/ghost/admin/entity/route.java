package com.my.ghost.admin.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "route_tb")
@Entity
public class route {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private String id;

    @Column(name = "route_name")
    private String routeName;

    @Column(name = "route_url")
    private String routeUrl;
}
