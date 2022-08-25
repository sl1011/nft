package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.Rechargelist;
import com.shitouren.core.autogenerate.bean.RechargelistExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RechargelistDao {
    long countByExample(RechargelistExample example);

    int deleteByExample(RechargelistExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Rechargelist record);

    int insertSelective(Rechargelist record);

    List<Rechargelist> selectByExample(RechargelistExample example);

    Rechargelist selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Rechargelist record, @Param("example") RechargelistExample example);

    int updateByExample(@Param("record") Rechargelist record, @Param("example") RechargelistExample example);

    int updateByPrimaryKeySelective(Rechargelist record);

    int updateByPrimaryKey(Rechargelist record);

    int insertBatchSelective(List<Rechargelist> records);

    int updateBatchByPrimaryKeySelective(List<Rechargelist> records);
}