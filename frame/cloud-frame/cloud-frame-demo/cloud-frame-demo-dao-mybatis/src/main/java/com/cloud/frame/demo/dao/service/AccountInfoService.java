package com.cloud.frame.demo.dao.service;

import com.cloud.frame.demo.dao.xml.entity.AccountInfoEntity;
import com.cloud.frame.demo.dao.xml.entity.AccountInfoEntityExample;
import com.cloud.frame.demo.dao.xml.mapper.AccountInfoEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wd on 2018/4/27.
 */
@Service
public class AccountInfoService {

    @Autowired
    private AccountInfoEntityMapper accountInfoEntityMapper;

//    public void deleteLogic() {
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        accountInfoEntityMapper.deleteLogicByIds(list, 1);
//    }

    public List<AccountInfoEntity> getPage(int pageNo, int pageSize) {
        AccountInfoEntityExample example = new AccountInfoEntityExample();
        example.setPageNo(pageNo);
        example.setPageSize(pageSize);
        AccountInfoEntityExample.Criteria criteria = example.createCriteria();
        return accountInfoEntityMapper.selectByExample(example);
    }

}
