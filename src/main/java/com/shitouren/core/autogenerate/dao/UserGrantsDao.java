package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.UserGrants;
import com.shitouren.core.autogenerate.bean.UserGrantsExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserGrantsDao {
    long countByExample(UserGrantsExample example);

    int deleteByExample(UserGrantsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserGrants record);

    int insertSelective(UserGrants record);

    List<UserGrants> selectByExample(UserGrantsExample example);

    UserGrants selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserGrants record, @Param("example") UserGrantsExample example);

    int updateByExample(@Param("record") UserGrants record, @Param("example") UserGrantsExample example);

    int updateByPrimaryKeySelective(UserGrants record);

    int updateByPrimaryKey(UserGrants record);

    int insertBatchSelective(List<UserGrants> records);

    int updateBatchByPrimaryKeySelective(List<UserGrants> records);
}