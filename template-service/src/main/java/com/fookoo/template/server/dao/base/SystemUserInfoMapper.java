package com.template.server.dao.base;

import com.template.server.entity.SystemUserInfo;
import com.template.server.entity.SystemUserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SystemUserInfoMapper {
    long countByExample(SystemUserInfoExample example);

    int deleteByExample(SystemUserInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SystemUserInfo record);

    int insertSelective(SystemUserInfo record);

    List<SystemUserInfo> selectByExample(SystemUserInfoExample example);

    SystemUserInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SystemUserInfo record, @Param("example") SystemUserInfoExample example);

    int updateByExample(@Param("record") SystemUserInfo record, @Param("example") SystemUserInfoExample example);

    int updateByPrimaryKeySelective(SystemUserInfo record);

    int updateByPrimaryKey(SystemUserInfo record);
}