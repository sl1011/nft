package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.Imglist;
import com.shitouren.core.autogenerate.bean.ImglistExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ImglistDao {
    long countByExample(ImglistExample example);

    int deleteByExample(ImglistExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Imglist record);

    int insertSelective(Imglist record);

    List<Imglist> selectByExample(ImglistExample example);

    Imglist selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Imglist record, @Param("example") ImglistExample example);

    int updateByExample(@Param("record") Imglist record, @Param("example") ImglistExample example);

    int updateByPrimaryKeySelective(Imglist record);

    int updateByPrimaryKey(Imglist record);

    int insertBatchSelective(List<Imglist> records);

    int updateBatchByPrimaryKeySelective(List<Imglist> records);
}