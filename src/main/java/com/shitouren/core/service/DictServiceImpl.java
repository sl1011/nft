package com.shitouren.core.service;

import com.shitouren.core.autogenerate.bean.Dict;
import com.shitouren.core.autogenerate.bean.DictExample;
import com.shitouren.core.autogenerate.dao.DictDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictServiceImpl implements DictService {
    @Autowired(required = false)
    DictDao dictDao;

    public String getValue(String code) {
        DictExample dictExample = new DictExample();
        dictExample.createCriteria().andCodeEqualTo(code);
        List<Dict> dictList = dictDao.selectByExample(dictExample);
        System.out.println(dictList);
        Dict dict = dictList.get(0);
        return dict.getValue();
    }

    public void setValue(String code, String value) {
        DictExample dictExample = new DictExample();
        dictExample.createCriteria().andCodeEqualTo(code);
        Dict dict = dictDao.selectByExample(dictExample).get(0);
        dict.setValue(value);
        dictDao.updateByPrimaryKeySelective(dict);
    }
}
