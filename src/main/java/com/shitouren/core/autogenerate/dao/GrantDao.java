package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.Grant;
import com.shitouren.core.autogenerate.bean.GrantExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GrantDao {
    long countByExample(GrantExample example);

    int deleteByExample(GrantExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Grant record);

    int insertSelective(Grant record);

    List<Grant> selectByExample(GrantExample example);

    Grant selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Grant record, @Param("example") GrantExample example);

    int updateByExample(@Param("record") Grant record, @Param("example") GrantExample example);

    int updateByPrimaryKeySelective(Grant record);

    int updateByPrimaryKey(Grant record);

    int insertBatchSelective(List<Grant> records);

    int updateBatchByPrimaryKeySelective(List<Grant> records);
}