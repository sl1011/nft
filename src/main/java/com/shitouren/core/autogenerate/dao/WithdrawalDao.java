package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.Withdrawal;
import com.shitouren.core.autogenerate.bean.WithdrawalExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WithdrawalDao {
    long countByExample(WithdrawalExample example);

    int deleteByExample(WithdrawalExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Withdrawal record);

    int insertSelective(Withdrawal record);

    List<Withdrawal> selectByExample(WithdrawalExample example);

    Withdrawal selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Withdrawal record, @Param("example") WithdrawalExample example);

    int updateByExample(@Param("record") Withdrawal record, @Param("example") WithdrawalExample example);

    int updateByPrimaryKeySelective(Withdrawal record);

    int updateByPrimaryKey(Withdrawal record);

    int insertBatchSelective(List<Withdrawal> records);

    int updateBatchByPrimaryKeySelective(List<Withdrawal> records);
}