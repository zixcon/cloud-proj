package com.cloud.frame.demo.auth.service;

import com.cloud.frame.demo.auth.dao.entity.AccountInfoEntity;
import com.cloud.frame.demo.auth.dao.entity.AccountInfoEntityExample;
import com.cloud.frame.demo.auth.dao.mapper.AccountInfoEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by wd on 2018/5/3.
 */
@Service
public class AccountInfoService {

    @Autowired
    private AccountInfoEntityMapper accountInfoEntityMapper;

    public AccountInfoEntity getEntity(String username) {
        AccountInfoEntityExample example = new AccountInfoEntityExample();
        AccountInfoEntityExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(username);
        List<AccountInfoEntity> list = accountInfoEntityMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }
}
