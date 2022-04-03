package com.fc.service.impl;

import com.fc.dao.PoorMapper;
import com.fc.entity.Poor;
import com.fc.entity.PoorWithBLOBs;
import com.fc.service.PoorService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PoorServiceImpl implements PoorService {
    static private final Map<String, Object> map = new HashMap<>();
    static private final Map<String, Object> errMap = new HashMap<>();
    @Autowired
    private PoorMapper poorMapper;


    @Override
    public Map<String, Object> add(PoorWithBLOBs poor) {
        if (poorMapper.insertSelective(poor) != 1) {
            map.put("code", 500);
            map.put("success", false);
            map.put("message", "请求参数异常");
            errMap.put("errMsg", "主键重复");
            map.put("data", errMap);
            return map;
        }
        map.put("code", 200);
        map.put("success", true);
        map.put("message", "OK");
        map.put("data", poor);
        return map;
    }

    @Override
    public Map<String, Object> delete(Long id) {
    if(poorMapper.deleteByPrimaryKey(id)){
        map.put("code", 200);
        map.put("success", true);
        map.put("message", "OK");
        map.put("data", null);
        return map;
    }
        map.put("code", 500);
        map.put("success", false);
        map.put("message", "NO");
        errMap.put("errMsg", "数据库无此ID");
        map.put("data", errMap);
        return map;
    }

    @Override
    public Map<String, Object> update(PoorWithBLOBs poor) {
        map.put("code", 500);
        map.put("success", false);
        map.put("message", "NO");
        errMap.put("errMsg", "请求参数异常");
        map.put("data", errMap);
        if (poor == null) return map;
        if (poorMapper.updateByPrimaryKeySelective(poor) != 1) {
            errMap.put("errMsg", "数据库中无此ID");
            map.put("data", errMap);
            return map;
        }
        Long id = poor.getId();
        PoorWithBLOBs data = poorMapper.selectByPrimaryKey(id);
        map.put("code", 200);
        map.put("success", true);
        map.put("message", "OK");
        map.put("data", data);
        return map;
    }




    @Override
    public Map<String, Object> getList(Integer pageNo, Integer pageSize) {
        pageNo = Math.max(pageNo, 1);
        pageSize = Math.max(pageSize, 5);

        PageHelper.startPage(pageNo, pageSize);
        List<Poor> list = poorMapper.selectByExample(null);
        if (list == null) {
            map.put("code", 500);
            map.put("success", false);
            map.put("message", "NO");
            errMap.put("errMsg", "数据库中无数据");
            map.put("data", errMap);
            return map;
        }

        PageInfo<Poor> pageInfo = new PageInfo<>(list);
        map.put("code", 200);
        map.put("success", true);
        map.put("message", "OK");
        map.put("data", pageInfo);
        return map;
    }


}
