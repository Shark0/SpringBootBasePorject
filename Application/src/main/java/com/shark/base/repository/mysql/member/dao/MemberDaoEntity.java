package com.shark.base.repository.mysql.member.dao;


import com.shark.base.dto.member.MemberDtoEntity;

import javax.persistence.*;

@Entity
@Table(name = "Member")
public class MemberDaoEntity extends MemberDtoEntity {

    public static final String ACCOUNT = "account";
    public static final String PASSWORD = "password";
    public static final String NAME = "name";

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    protected long id;

    private String account;

    private String password;

    private String name;


    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getAccount() {
        return account;
    }

    @Override
    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
