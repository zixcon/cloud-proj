package com.cloud.frame.demo.reactive.mapper;

import com.cloud.frame.demo.reactive.entity.WxAppInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by wd on 2018/2/2.
 */
public interface WxAppInfoMapper {

    @Select("SELECT * FROM wx_app_info WHERE enable=1 and type=#{type} limit 1")
    WxAppInfo findOne(@Param("type") Integer type);
}
