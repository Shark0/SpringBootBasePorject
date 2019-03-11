package com.shark.application.repository.account.dao;



import javax.persistence.*;

@Entity
@Table(name = "account")
public class AccountDaoEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Long id;

    @Column(unique = true)
    private String account;

    private String password;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
