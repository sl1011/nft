package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.Blindboxtrue;
import com.shitouren.core.autogenerate.bean.BlindboxtrueExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BlindboxtrueDao {
    long countByExample(BlindboxtrueExample example);

    int deleteByExample(BlindboxtrueExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Blindboxtrue record);

    int insertSelective(Blindboxtrue record);

    List<Blindboxtrue> selectByExample(BlindboxtrueExample example);

    Blindboxtrue selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Blindboxtrue record, @Param("example") BlindboxtrueExample example);

    int updateByExample(@Param("record") Blindboxtrue record, @Param("example") BlindboxtrueExample example);

    int updateByPrimaryKeySelective(Blindboxtrue record);

    int updateByPrimaryKey(Blindboxtrue record);

    int insertBatchSelective(List<Blindboxtrue> records);

    int updateBatchByPrimaryKeySelective(List<Blindboxtrue> records);
}