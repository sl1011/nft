package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.UserGrant;
import com.shitouren.core.autogenerate.bean.UserGrantExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserGrantDao {
    long countByExample(UserGrantExample example);

    int deleteByExample(UserGrantExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserGrant record);

    int insertSelective(UserGrant record);

    List<UserGrant> selectByExample(UserGrantExample example);

    UserGrant selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserGrant record, @Param("example") UserGrantExample example);

    int updateByExample(@Param("record") UserGrant record, @Param("example") UserGrantExample example);

    int updateByPrimaryKeySelective(UserGrant record);

    int updateByPrimaryKey(UserGrant record);

    int insertBatchSelective(List<UserGrant> records);

    int updateBatchByPrimaryKeySelective(List<UserGrant> records);
}