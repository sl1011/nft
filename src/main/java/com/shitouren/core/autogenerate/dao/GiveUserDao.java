package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.GiveUser;
import com.shitouren.core.autogenerate.bean.GiveUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GiveUserDao {
    long countByExample(GiveUserExample example);

    int deleteByExample(GiveUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GiveUser record);

    int insertSelective(GiveUser record);

    List<GiveUser> selectByExample(GiveUserExample example);

    GiveUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GiveUser record, @Param("example") GiveUserExample example);

    int updateByExample(@Param("record") GiveUser record, @Param("example") GiveUserExample example);

    int updateByPrimaryKeySelective(GiveUser record);

    int updateByPrimaryKey(GiveUser record);

    int insertBatchSelective(List<GiveUser> records);

    int updateBatchByPrimaryKeySelective(List<GiveUser> records);
}