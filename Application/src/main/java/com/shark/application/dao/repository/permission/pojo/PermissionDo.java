package com.shark.application.dao.repository.permission.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "permission")
public class PermissionDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigserial")
    private Long id;

    private String code;

    private String name;
}
