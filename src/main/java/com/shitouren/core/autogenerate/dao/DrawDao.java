package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.Draw;
import com.shitouren.core.autogenerate.bean.DrawExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DrawDao {
    long countByExample(DrawExample example);

    int deleteByExample(DrawExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Draw record);

    int insertSelective(Draw record);

    List<Draw> selectByExample(DrawExample example);

    Draw selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Draw record, @Param("example") DrawExample example);

    int updateByExample(@Param("record") Draw record, @Param("example") DrawExample example);

    int updateByPrimaryKeySelective(Draw record);

    int updateByPrimaryKey(Draw record);

    int insertBatchSelective(List<Draw> records);

    int updateBatchByPrimaryKeySelective(List<Draw> records);
}