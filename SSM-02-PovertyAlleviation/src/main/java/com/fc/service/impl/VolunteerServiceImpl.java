package com.fc.service.impl;

import com.fc.dao.VolunteerRecruitmentMapper;
import com.fc.entity.VolunteerRecruitment;
import com.fc.service.VolunteerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VolunteerServiceImpl implements VolunteerService {
    static final Map<String, Object> map = new HashMap<>();
    static final Map<String, Object> errMap = new HashMap<>();
    @Autowired
    private VolunteerRecruitmentMapper volunteerMapper;

    @Override
    public Map<String, Object> add(VolunteerRecruitment volunteer) {
        errMap.put("errMsg", "请求参数为null");
        map.put("code", 404);
        map.put("success", false);
        map.put("message", "添加失败！");
        map.put("data", errMap);
        if(volunteer == null)return  map;

        int row = volunteerMapper.insert(volunteer);

        if(row < 1){
            errMap.put("errMsg","数据库异常，添加失败");
            map.put("data",errMap);
            return  map;
        }
        map.put("code", 200);
        map.put("success", true);
        map.put("message", "OK");
        map.put("data", volunteer);
        return map;
    }

    @Override
    public Map<String, Object> delete(Long id) {
        errMap.put("errMsg", "请求参数为null");
        map.put("code", 500);
        map.put("success", false);
        map.put("message", "删除失败！");
        map.put("data", errMap);
        if(id == null)return  map;

        int row = volunteerMapper.deleteByPrimaryKey(id);

        if(row < 1){
            errMap.put("errMsg","数据库中无此ID，或者已经被删除了");
            map.put("data",errMap);
            return  map;
        }
        map.put("code", 200);
        map.put("success", true);
        map.put("message", "OK");
        map.put("data", null);
        return map;
    }

    @Override
    public Map<String, Object> update(VolunteerRecruitment volunteer) {
        errMap.put("errMsg", "请求参数为null");
        map.put("code", 404);
        map.put("success", false);
        map.put("message", "修改失败！");
        map.put("data", errMap);
        if(volunteer == null)return  map;

        int row = volunteerMapper.updateByPrimaryKeySelective(volunteer);

        if(row < 1){
            errMap.put("errMsg","数据库中无此ID");
            map.put("data",errMap);
            return  map;
        }
        long id = volunteer.getId();
        VolunteerRecruitment data = volunteerMapper.selectByPrimaryKey(id);
        map.put("code", 200);
        map.put("success", true);
        map.put("message", "OK");
        map.put("data", data);
        return map;
    }

    @Override
    public Map<String, Object> getList(Integer pageNo, Integer pageSize) {
        pageNo = Math.max(1, pageNo);
        pageSize = Math.max(1, pageSize);

        map.put("code", 500);
        map.put("success", false);
        map.put("message", "数据库中无数据");
        errMap.put("errMsg", "数据库中无数据");
        map.put("data", errMap);

        PageHelper.startPage(pageNo, pageSize);
        List<VolunteerRecruitment> list = volunteerMapper.selectByExample(null);
        if (list == null) return map;
        PageInfo<VolunteerRecruitment> pageInfo = new PageInfo<>(list);

        map.put("code", 200);
        map.put("success", true);
        map.put("message", "OK");
        map.put("data", pageInfo);
        return map;
    }


}
