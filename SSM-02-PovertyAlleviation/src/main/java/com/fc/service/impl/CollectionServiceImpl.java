package com.fc.service.impl;

import com.fc.dao.CollectionMapper;
import com.fc.entity.Collection;
import com.fc.service.CollectionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CollectionServiceImpl implements CollectionService {
    static private final Map<String, Object> map = new HashMap<>();
    static private final Map<String, Object> errMap = new HashMap<>();
    @Autowired
    private CollectionMapper collectionMapper;

    @Override
    public Map<String, Object> add(Collection collection) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "收藏失败！");
        map.put("success", false);
        map.put("code", 500);
        errMap.put("errMsg", "添加失败！参数为null或主键重复");
        map.put("data", errMap);

        if (collection == null) return map;

        int affectedRows = collectionMapper.insert(collection);

        if (affectedRows != 1) return map;

        map.put("message", "收藏成功！");
        map.put("success", true);
        map.put("code", 200);
        map.put("data", null);
        return map;
    }

    @Override
    public Map<String, Object> del(Long id) {
        map.put("message", "删除失败！");
        map.put("code", 404);
        map.put("success", false);
        errMap.put("errMsg", "删除失败，请求参数异常");
        map.put("data",errMap);

        if (id == null) return map;

        int affectedRows = collectionMapper.deleteByPrimaryKey(id);

        if (affectedRows != 1) return map;

        map.put("message", "删除成功！");
        map.put("code", 200);
        map.put("success", true);
        map.put("data", null);

        return map;
    }

    @Override
    public Map<String, Object> getList(Integer pageNo, Integer pageSize) {
        map.put("code",404);
        map.put("success",false);
        map.put("message","获取失败！");
        errMap.put("errMsg", "请求参数为null");
        map.put("data", errMap);
        if(pageNo == null || pageSize == null) return  map;

        pageNo = pageNo < 1 ? 1 : pageNo;

        pageSize = pageSize < 1 ? 5 : pageSize;

        PageHelper.startPage(pageNo,pageSize);

        List<Collection> list = collectionMapper.selectByExample(null);

        PageInfo<Collection> pageInfo = new PageInfo<>(list);

        map.put("code",200);
        map.put("success",true);
        map.put("message","获取成功！");
        map.put("data",pageInfo);

        return map;
    }
}
