package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.Fragment;
import com.shitouren.core.autogenerate.bean.FragmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FragmentDao {
    long countByExample(FragmentExample example);

    int deleteByExample(FragmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Fragment record);

    int insertSelective(Fragment record);

    List<Fragment> selectByExample(FragmentExample example);

    Fragment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Fragment record, @Param("example") FragmentExample example);

    int updateByExample(@Param("record") Fragment record, @Param("example") FragmentExample example);

    int updateByPrimaryKeySelective(Fragment record);

    int updateByPrimaryKey(Fragment record);

    int insertBatchSelective(List<Fragment> records);

    int updateBatchByPrimaryKeySelective(List<Fragment> records);
}