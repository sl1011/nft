package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.BalanceRecord;
import com.shitouren.core.autogenerate.bean.BalanceRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BalanceRecordDao {
    long countByExample(BalanceRecordExample example);

    int deleteByExample(BalanceRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BalanceRecord record);

    int insertSelective(BalanceRecord record);

    List<BalanceRecord> selectByExample(BalanceRecordExample example);

    BalanceRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BalanceRecord record, @Param("example") BalanceRecordExample example);

    int updateByExample(@Param("record") BalanceRecord record, @Param("example") BalanceRecordExample example);

    int updateByPrimaryKeySelective(BalanceRecord record);

    int updateByPrimaryKey(BalanceRecord record);

    int insertBatchSelective(List<BalanceRecord> records);

    int updateBatchByPrimaryKeySelective(List<BalanceRecord> records);
}