package com.fc.service.impl;

import com.fc.dao.UserMapper;
import com.fc.entity.User;
import com.fc.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    static private final Map<String, Object> map = new HashMap<>();
    static private final Map<String, Object> errMap = new HashMap<>();
    @Autowired
    private UserMapper userMapper;

    //添加service
    @Override
    public Map<String, Object> add(User user) {
        int affectedRows = userMapper.insert(user);
        if (affectedRows != 1) {
            map.put("message", "用户添加失败！");
            map.put("code", 400);
            map.put("success", false);
            errMap.put("errMsg", "添加失败");
            map.put("data", errMap);
            return map;
        }
        map.put("message", "用户添加成功！");
        map.put("code", 200);
        map.put("success", true);
        map.put("data", null);
        return map;
    }

    //删除一个
    @Override
    public Map<String, Object> del(Long id) {
        map.put("code", 404);
        map.put("message", "用户删除失败");
        map.put("success", false);
        errMap.put("errMsg", "添加失败");
        map.put("data",errMap);
        if (id == null) {
            return map;
        }
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            return map;
        }
        map.put("code", 200);
        map.put("message", "用户删除成功！");
        map.put("success", true);
        map.put("data", null);
        return map;
    }

    //修改
    @Override
    public Map<String, Object> update(User user) {
        map.put("message", "用户修改失败！");
        map.put("code", 404);
        map.put("success", false);
        errMap.put("errMsg", "数据库无此数据或请求参数错误");
        map.put("data", errMap);
        if (user == null) return map;
        int affectedRows = userMapper.updateByPrimaryKeySelective(user);
        if (affectedRows == 0) return map;
        map.put("message", "用户修改成功！");
        map.put("code", 200);
        map.put("success", true);
        map.put("data", null);
        return map;
    }

    //分页service
    @Override
    public Map<String, Object> getList(String pageNoStr, String pageSizeStr) {
        map.put("message", "用户获取失败！");
        map.put("code", 400);
        map.put("success", false);

        errMap.put("errMsg", "请求参数为null,或者数据库中没有数据");

        map.put("data", errMap);

        if (pageNoStr == null || pageSizeStr == null) return map;

        int pageNo;
        int pageSize;

        //防止前端传的参数携带空格
        pageNoStr = pageNoStr.trim();
        pageSizeStr = pageSizeStr.trim();

        try {
            pageNo = Integer.parseInt(pageNoStr);
        } catch (Exception e) {
            pageNo = 1;
        }

        try{
            pageSize = Integer.parseInt(pageSizeStr);
        }catch (Exception e){
            pageSize = 5;
        }

        PageHelper.startPage(pageNo, pageSize);
        List<User> list = userMapper.selectByExample(null);

        if (list == null) return map;

        PageInfo<User> pageInfo = new PageInfo<>(list);
        map.put("message", "用户获取成功！");
        map.put("code", 200);
        map.put("success", true);
        map.put("data", pageInfo);
        return map;
    }


}
