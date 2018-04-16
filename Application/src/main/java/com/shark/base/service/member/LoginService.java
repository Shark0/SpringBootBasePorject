package com.shark.base.service.member;

import com.shark.base.dto.ResponseDataEntity;
import com.shark.base.dto.member.MemberDtoEntity;
import com.shark.base.exception.ResponseException;
import com.shark.base.repository.mysql.member.MemberRepository;
import com.shark.base.repository.mysql.member.dao.MemberDaoEntity;
import com.shark.base.service.BaseStringFromResponseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class LoginService extends BaseStringFromResponseDataService<MemberDaoEntity, MemberDtoEntity> {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    protected List<String> generateCheckKeyList() {
        List<String> list = new ArrayList<>();
        list.add(MemberDaoEntity.ACCOUNT);
        list.add(MemberDaoEntity.PASSWORD);
        return list;
    }

    @Override
    protected MemberDaoEntity dataAccess(HashMap<String, String> parameters) throws Exception {
        String account = parameters.get(MemberDaoEntity.ACCOUNT);
        String password = parameters.get(MemberDaoEntity.PASSWORD);
        MemberDaoEntity memberDaoEntity = memberRepository.findByAccount(account);
        if(memberDaoEntity == null){
            ResponseException exception = new ResponseException();
            exception.setReturnCode(-1);
            exception.setReturnMessage("Account doesn't exist");
            throw exception;
        }

        if(!password.equalsIgnoreCase(memberDaoEntity.getPassword())) {
            ResponseException exception = new ResponseException();
            exception.setReturnCode(-2);
            exception.setReturnMessage("Password error");
            throw exception;
        }
        return memberDaoEntity;
    }

    @Override
    protected ResponseDataEntity<MemberDtoEntity> generateResultData(MemberDaoEntity memberDaoEntity) {
        ResponseDataEntity<MemberDtoEntity> responseDataEntity = new ResponseDataEntity<>();
        MemberDtoEntity memberDtoEntity = new MemberDtoEntity();
        memberDtoEntity.setId(memberDaoEntity.getId());
        memberDtoEntity.setAccount(memberDaoEntity.getAccount());
        memberDtoEntity.setName(memberDaoEntity.getName());
        responseDataEntity.setData(memberDtoEntity);
        responseDataEntity.setReturnCode(1);
        return responseDataEntity;
    }
}
