package com.shark.base.service.member;

import com.shark.base.repository.mysql.member.MemberRepository;
import com.shark.base.repository.mysql.member.dao.MemberDaoEntity;
import com.shark.base.service.BaseStringFromResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UpdateNameService extends BaseStringFromResponseService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        List<String> list = new ArrayList<>();
        list.add(MemberDaoEntity.NAME);
        return list;
    }

    @Override
    protected Void dataAccess(HashMap<String, String> parameters) throws Exception {
        MemberDaoEntity memberDaoEntity = memberRepository.findOne(Long.valueOf(memberId));
        memberDaoEntity.setName(parameters.get(MemberDaoEntity.NAME));
        memberRepository.save(memberDaoEntity);
        return null;
    }
}
