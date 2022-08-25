package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.Schedulmessage;
import com.shitouren.core.autogenerate.bean.SchedulmessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SchedulmessageDao {
    long countByExample(SchedulmessageExample example);

    int deleteByExample(SchedulmessageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Schedulmessage record);

    int insertSelective(Schedulmessage record);

    List<Schedulmessage> selectByExample(SchedulmessageExample example);

    Schedulmessage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Schedulmessage record, @Param("example") SchedulmessageExample example);

    int updateByExample(@Param("record") Schedulmessage record, @Param("example") SchedulmessageExample example);

    int updateByPrimaryKeySelective(Schedulmessage record);

    int updateByPrimaryKey(Schedulmessage record);

    int insertBatchSelective(List<Schedulmessage> records);

    int updateBatchByPrimaryKeySelective(List<Schedulmessage> records);
}