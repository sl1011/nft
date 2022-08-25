package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.Collection;
import com.shitouren.core.autogenerate.bean.CollectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CollectionDao {
    long countByExample(CollectionExample example);

    int deleteByExample(CollectionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Collection record);

    int insertSelective(Collection record);

    List<Collection> selectByExampleWithBLOBs(CollectionExample example);

    List<Collection> selectByExample(CollectionExample example);

    Collection selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Collection record, @Param("example") CollectionExample example);

    int updateByExampleWithBLOBs(@Param("record") Collection record, @Param("example") CollectionExample example);

    int updateByExample(@Param("record") Collection record, @Param("example") CollectionExample example);

    int updateByPrimaryKeySelective(Collection record);

    int updateByPrimaryKeyWithBLOBs(Collection record);

    int updateByPrimaryKey(Collection record);

    int insertBatchSelective(List<Collection> records);

    int updateBatchByPrimaryKeySelective(List<Collection> records);
}