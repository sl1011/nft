package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.HideRecord;
import com.shitouren.core.autogenerate.bean.HideRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HideRecordDao {
    long countByExample(HideRecordExample example);

    int deleteByExample(HideRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HideRecord record);

    int insertSelective(HideRecord record);

    List<HideRecord> selectByExample(HideRecordExample example);

    HideRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HideRecord record, @Param("example") HideRecordExample example);

    int updateByExample(@Param("record") HideRecord record, @Param("example") HideRecordExample example);

    int updateByPrimaryKeySelective(HideRecord record);

    int updateByPrimaryKey(HideRecord record);

    int insertBatchSelective(List<HideRecord> records);

    int updateBatchByPrimaryKeySelective(List<HideRecord> records);
}