package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.Drawrecord;
import com.shitouren.core.autogenerate.bean.DrawrecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DrawrecordDao {
    long countByExample(DrawrecordExample example);

    int deleteByExample(DrawrecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Drawrecord record);

    int insertSelective(Drawrecord record);

    List<Drawrecord> selectByExample(DrawrecordExample example);

    Drawrecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Drawrecord record, @Param("example") DrawrecordExample example);

    int updateByExample(@Param("record") Drawrecord record, @Param("example") DrawrecordExample example);

    int updateByPrimaryKeySelective(Drawrecord record);

    int updateByPrimaryKey(Drawrecord record);

    int insertBatchSelective(List<Drawrecord> records);

    int updateBatchByPrimaryKeySelective(List<Drawrecord> records);
}