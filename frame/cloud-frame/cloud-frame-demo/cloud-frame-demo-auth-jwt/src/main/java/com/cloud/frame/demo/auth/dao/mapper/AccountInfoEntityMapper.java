package com.cloud.frame.demo.auth.dao.mapper;

import com.cloud.frame.demo.auth.dao.entity.AccountInfoEntity;
import com.cloud.frame.demo.auth.dao.entity.AccountInfoEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountInfoEntityMapper {
    long countByExample(AccountInfoEntityExample example);

    int deleteByExample(AccountInfoEntityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AccountInfoEntity record);

    int insertSelective(AccountInfoEntity record);

    int deleteLogicByIds(@Param("ids") List<Integer> ids, @Param("isDeleted") int isDeleted);

    List<AccountInfoEntity> selectByExample(AccountInfoEntityExample example);

    AccountInfoEntity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AccountInfoEntity record, @Param("example") AccountInfoEntityExample example);

    int updateByExample(@Param("record") AccountInfoEntity record, @Param("example") AccountInfoEntityExample example);

    int updateByPrimaryKeySelective(AccountInfoEntity record);

    int updateByPrimaryKey(AccountInfoEntity record);
}