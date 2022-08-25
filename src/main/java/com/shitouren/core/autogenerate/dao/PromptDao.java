package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.Prompt;
import com.shitouren.core.autogenerate.bean.PromptExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PromptDao {
    long countByExample(PromptExample example);

    int deleteByExample(PromptExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Prompt record);

    int insertSelective(Prompt record);

    List<Prompt> selectByExample(PromptExample example);

    Prompt selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Prompt record, @Param("example") PromptExample example);

    int updateByExample(@Param("record") Prompt record, @Param("example") PromptExample example);

    int updateByPrimaryKeySelective(Prompt record);

    int updateByPrimaryKey(Prompt record);

    int insertBatchSelective(List<Prompt> records);

    int updateBatchByPrimaryKeySelective(List<Prompt> records);
}