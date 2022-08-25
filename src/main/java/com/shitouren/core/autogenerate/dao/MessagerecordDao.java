package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.Messagerecord;
import com.shitouren.core.autogenerate.bean.MessagerecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MessagerecordDao {
    long countByExample(MessagerecordExample example);

    int deleteByExample(MessagerecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Messagerecord record);

    int insertSelective(Messagerecord record);

    List<Messagerecord> selectByExample(MessagerecordExample example);

    Messagerecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Messagerecord record, @Param("example") MessagerecordExample example);

    int updateByExample(@Param("record") Messagerecord record, @Param("example") MessagerecordExample example);

    int updateByPrimaryKeySelective(Messagerecord record);

    int updateByPrimaryKey(Messagerecord record);

    int insertBatchSelective(List<Messagerecord> records);

    int updateBatchByPrimaryKeySelective(List<Messagerecord> records);
}