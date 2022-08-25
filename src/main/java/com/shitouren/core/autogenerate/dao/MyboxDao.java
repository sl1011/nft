package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.Mybox;
import com.shitouren.core.autogenerate.bean.MyboxExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MyboxDao {
    long countByExample(MyboxExample example);

    int deleteByExample(MyboxExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Mybox record);

    int insertSelective(Mybox record);

    List<Mybox> selectByExample(MyboxExample example);

    Mybox selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Mybox record, @Param("example") MyboxExample example);

    int updateByExample(@Param("record") Mybox record, @Param("example") MyboxExample example);

    int updateByPrimaryKeySelective(Mybox record);

    int updateByPrimaryKey(Mybox record);

    int insertBatchSelective(List<Mybox> records);

    int updateBatchByPrimaryKeySelective(List<Mybox> records);
}