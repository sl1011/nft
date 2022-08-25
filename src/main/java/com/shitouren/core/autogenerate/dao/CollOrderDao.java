package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.CollOrder;
import com.shitouren.core.autogenerate.bean.CollOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CollOrderDao {
    long countByExample(CollOrderExample example);

    int deleteByExample(CollOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CollOrder record);

    int insertSelective(CollOrder record);

    List<CollOrder> selectByExample(CollOrderExample example);

    CollOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CollOrder record, @Param("example") CollOrderExample example);

    int updateByExample(@Param("record") CollOrder record, @Param("example") CollOrderExample example);

    int updateByPrimaryKeySelective(CollOrder record);

    int updateByPrimaryKey(CollOrder record);

    int insertBatchSelective(List<CollOrder> records);

    int updateBatchByPrimaryKeySelective(List<CollOrder> records);
}