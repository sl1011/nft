package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.Prompts;
import com.shitouren.core.autogenerate.bean.PromptsExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PromptsDao {
    long countByExample(PromptsExample example);

    int deleteByExample(PromptsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Prompts record);

    int insertSelective(Prompts record);

    List<Prompts> selectByExample(PromptsExample example);

    Prompts selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Prompts record, @Param("example") PromptsExample example);

    int updateByExample(@Param("record") Prompts record, @Param("example") PromptsExample example);

    int updateByPrimaryKeySelective(Prompts record);

    int updateByPrimaryKey(Prompts record);

    int insertBatchSelective(List<Prompts> records);

    int updateBatchByPrimaryKeySelective(List<Prompts> records);
}