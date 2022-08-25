package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.CancelRecord;
import com.shitouren.core.autogenerate.bean.CancelRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CancelRecordDao {
    long countByExample(CancelRecordExample example);

    int deleteByExample(CancelRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CancelRecord record);

    int insertSelective(CancelRecord record);

    List<CancelRecord> selectByExample(CancelRecordExample example);

    CancelRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CancelRecord record, @Param("example") CancelRecordExample example);

    int updateByExample(@Param("record") CancelRecord record, @Param("example") CancelRecordExample example);

    int updateByPrimaryKeySelective(CancelRecord record);

    int updateByPrimaryKey(CancelRecord record);

    int insertBatchSelective(List<CancelRecord> records);

    int updateBatchByPrimaryKeySelective(List<CancelRecord> records);
}