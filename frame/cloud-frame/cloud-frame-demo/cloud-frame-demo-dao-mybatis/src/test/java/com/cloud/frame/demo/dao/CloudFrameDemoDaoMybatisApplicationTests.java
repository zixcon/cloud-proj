package com.cloud.frame.demo.dao;

import com.cloud.frame.demo.dao.service.AccountInfoService;
import com.cloud.frame.demo.dao.xml.entity.AccountInfoEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CloudFrameDemoDaoMybatisApplicationTests {

    @Autowired
    private AccountInfoService accountInfoService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void test() {
        List<AccountInfoEntity> list = accountInfoService.getPage(1, 10);
        System.out.println(list.toString());
    }

}
