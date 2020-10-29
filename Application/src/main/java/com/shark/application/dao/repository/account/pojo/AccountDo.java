package com.shark.application.dao.repository.account.pojo;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "account")
public class AccountDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigserial")
    protected Long id;

    @Column(unique = true)
    private String account;

    private String password;

    private String name;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;
}
