package com.shark.base.repository.mysql.member;

import com.shark.base.repository.mysql.member.dao.MemberDaoEntity;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<MemberDaoEntity, Long> {

    public MemberDaoEntity findByAccount(String account);
}
