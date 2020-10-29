package com.shark.application.dao.repository.menu.pojo;

import com.shark.application.dao.repository.menu.pojo.id.MenuRoleIdEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "menu_role")
@IdClass(MenuRoleIdEntity.class)
public class MenuRoleDo implements Serializable {

    @Id
    @Column(name = "menu_id")
    private Long menuId;

    @Id
    @Column(name = "role_id")
    private Long roleId;

}
