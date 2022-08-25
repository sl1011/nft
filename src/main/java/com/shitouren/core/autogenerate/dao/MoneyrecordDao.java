package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.Moneyrecord;
import com.shitouren.core.autogenerate.bean.MoneyrecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MoneyrecordDao {
    long countByExample(MoneyrecordExample example);

    int deleteByExample(MoneyrecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Moneyrecord record);

    int insertSelective(Moneyrecord record);

    List<Moneyrecord> selectByExample(MoneyrecordExample example);

    Moneyrecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Moneyrecord record, @Param("example") MoneyrecordExample example);

    int updateByExample(@Param("record") Moneyrecord record, @Param("example") MoneyrecordExample example);

    int updateByPrimaryKeySelective(Moneyrecord record);

    int updateByPrimaryKey(Moneyrecord record);

    int insertBatchSelective(List<Moneyrecord> records);

    int updateBatchByPrimaryKeySelective(List<Moneyrecord> records);
}