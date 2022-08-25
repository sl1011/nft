package com.shitouren.core.autogenerate.dao;

import com.shitouren.core.autogenerate.bean.Agreements;
import com.shitouren.core.autogenerate.bean.AgreementsExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AgreementsDao {
    long countByExample(AgreementsExample example);

    int deleteByExample(AgreementsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Agreements record);

    int insertSelective(Agreements record);

    List<Agreements> selectByExampleWithBLOBs(AgreementsExample example);

    List<Agreements> selectByExample(AgreementsExample example);

    Agreements selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Agreements record, @Param("example") AgreementsExample example);

    int updateByExampleWithBLOBs(@Param("record") Agreements record, @Param("example") AgreementsExample example);

    int updateByExample(@Param("record") Agreements record, @Param("example") AgreementsExample example);

    int updateByPrimaryKeySelective(Agreements record);

    int updateByPrimaryKeyWithBLOBs(Agreements record);

    int updateByPrimaryKey(Agreements record);

    int insertBatchSelective(List<Agreements> records);

    int updateBatchByPrimaryKeySelective(List<Agreements> records);
}