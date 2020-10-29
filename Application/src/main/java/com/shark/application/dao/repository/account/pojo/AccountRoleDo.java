package com.shark.application.dao.repository.account.pojo;

import com.shark.application.dao.repository.account.pojo.id.AccountRoleIdDo;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "account_role")
@IdClass(AccountRoleIdDo.class)
public class AccountRoleDo implements Serializable {

    @Id
    @Column(name = "account_id")
    private Long accountId;

    @Id
    @Column(name = "role_id")
    private Long roleId;
}
