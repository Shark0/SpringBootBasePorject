package com.shark.application.dao.repository.menu.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "menu")
public class MenuDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigserial")
    private Long id;

    @Column(name = "parent_id")
    private Long parentId;

    private String name;

    private String path;

    private Double sort;

    @Column(name = "icon_url")
    private String iconUrl;
}
