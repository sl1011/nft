package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.Blindbox;
import com.shitouren.core.autogenerate.bean.BlindboxExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BlindboxDao {
    long countByExample(BlindboxExample example);

    int deleteByExample(BlindboxExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Blindbox record);

    int insertSelective(Blindbox record);

    List<Blindbox> selectByExample(BlindboxExample example);

    Blindbox selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Blindbox record, @Param("example") BlindboxExample example);

    int updateByExample(@Param("record") Blindbox record, @Param("example") BlindboxExample example);

    int updateByPrimaryKeySelective(Blindbox record);

    int updateByPrimaryKey(Blindbox record);

    int insertBatchSelective(List<Blindbox> records);

    int updateBatchByPrimaryKeySelective(List<Blindbox> records);
}