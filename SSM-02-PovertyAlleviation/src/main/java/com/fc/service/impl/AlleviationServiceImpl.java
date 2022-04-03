package com.fc.service.impl;

import com.fc.dao.AlleviationMapper;
import com.fc.entity.Alleviation;
import com.fc.service.AlleviationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class AlleviationServiceImpl implements AlleviationService {
    static private final Map<String, Object> map = new HashMap<>();
    static private final Map<String, Object> errMap = new HashMap<>();
    @Autowired
    private AlleviationMapper alleviationMapper;
    @Override
    public Map<String, Object> add(Alleviation alleviation) {
        errMap.put("errMsg","请求参数为null！");

        map.put("code",500);
        map.put("success",false);
        map.put("message","添加失败！");
        map.put("data",errMap);

        if(alleviation == null) return  map;

        int row = alleviationMapper.insert(alleviation);

        if(row != 1){
            errMap.put("errMsg","主键重复或数据库异常");
            map.put("data",errMap);
            return  map;
        }
        map.put("code",200);
        map.put("success",true);
        map.put("message","添加成功！");
        map.put("data",null);

        return map;
    }

    @Override
    public Map<String, Object> delete(Long id) {
        map.put("code",500);
        map.put("success",false);
        map.put("message","删除失败！");


        int row = alleviationMapper.deleteByPrimaryKey(id);
        if(row < 1){
            errMap.put("errMsg","数据库中无此ID");
            map.put("data",errMap);
            return map;
        }
        map.put("code",200);
        map.put("success",true);
        map.put("message","OK");
        map.put("data",null);
        return map;
    }

    @Override
    public Map<String, Object> update(Alleviation alleviation) {
        map.put("code", 500);
        map.put("success",false);
        map.put("message","NO");
        long id = alleviation.getId();
        int row = alleviationMapper.updateByPrimaryKeySelective(alleviation);
        if(row < 1){
            errMap.put("errMst","数据库中无此ID");
            map.put("data",errMap);
            return map;
        }
        Alleviation resBean = alleviationMapper.selectByPrimaryKey(id);
        map.put("code",200);
        map.put("success",true);
        map.put("message","OK");
        map.put("data",resBean);
        return map;
    }

    @Override
    public Map<String, Object> getList(Integer pageNo, Integer pageSize) {
        pageNo = Math.max(1,pageNo);
        pageSize = Math.max(1,pageSize);
        errMap.put("errMsg","数据库中无数据！");
        map.put("code", 500);
        map.put("success",false);
        map.put("message","数据库中无数据！");
        map.put("data",errMap);

        PageHelper.startPage(pageNo,pageSize);
        List<Alleviation> list = alleviationMapper.selectByExample(null);
        if(list == null)return map;
        PageInfo<Alleviation> pageInfo = new PageInfo<>(list);
        map.put("code",200);
        map.put("success",true);
        map.put("message","OK");
        map.put("data",pageInfo);
        return map;
    }
}
