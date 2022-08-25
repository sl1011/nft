package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.Transforlist;
import com.shitouren.core.autogenerate.bean.TransforlistExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TransforlistDao {
    long countByExample(TransforlistExample example);

    int deleteByExample(TransforlistExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Transforlist record);

    int insertSelective(Transforlist record);

    List<Transforlist> selectByExample(TransforlistExample example);

    Transforlist selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Transforlist record, @Param("example") TransforlistExample example);

    int updateByExample(@Param("record") Transforlist record, @Param("example") TransforlistExample example);

    int updateByPrimaryKeySelective(Transforlist record);

    int updateByPrimaryKey(Transforlist record);

    int insertBatchSelective(List<Transforlist> records);

    int updateBatchByPrimaryKeySelective(List<Transforlist> records);
}