package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.Realname;
import com.shitouren.core.autogenerate.bean.RealnameExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RealnameDao {
    long countByExample(RealnameExample example);

    int deleteByExample(RealnameExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Realname record);

    int insertSelective(Realname record);

    List<Realname> selectByExample(RealnameExample example);

    Realname selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Realname record, @Param("example") RealnameExample example);

    int updateByExample(@Param("record") Realname record, @Param("example") RealnameExample example);

    int updateByPrimaryKeySelective(Realname record);

    int updateByPrimaryKey(Realname record);

    int insertBatchSelective(List<Realname> records);

    int updateBatchByPrimaryKeySelective(List<Realname> records);
}