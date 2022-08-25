package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.Apliaymh;
import com.shitouren.core.autogenerate.bean.ApliaymhExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ApliaymhDao {
    long countByExample(ApliaymhExample example);

    int deleteByExample(ApliaymhExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Apliaymh record);

    int insertSelective(Apliaymh record);

    List<Apliaymh> selectByExample(ApliaymhExample example);

    Apliaymh selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Apliaymh record, @Param("example") ApliaymhExample example);

    int updateByExample(@Param("record") Apliaymh record, @Param("example") ApliaymhExample example);

    int updateByPrimaryKeySelective(Apliaymh record);

    int updateByPrimaryKey(Apliaymh record);

    int insertBatchSelective(List<Apliaymh> records);

    int updateBatchByPrimaryKeySelective(List<Apliaymh> records);
}