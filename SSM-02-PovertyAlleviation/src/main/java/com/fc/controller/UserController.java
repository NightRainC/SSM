package com.fc.controller;

import com.fc.entity.User;
import com.fc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    //用户添加
    @RequestMapping(value = "add",consumes = "application/json;charset=UTF-8")
    public Map<String, Object> add(@RequestBody User user){
        return userService.add(user);
    }
    //根据id删除
    @RequestMapping("del")
    public  Map<String , Object> del(Long id){
        return userService.del(id);
    }
    //用户修改
    @RequestMapping(value = "update",consumes = "application/json;charset=UTF-8")
    public Map<String,Object> update(@RequestBody User user){
        return userService.update(user);
    }
    //分页显示用户
    @RequestMapping("list")
    public Map<String, Object> list(@RequestParam(value = "pageNo", defaultValue = "1") String pageNo, @RequestParam(value = "pageSize", defaultValue = "5") String pageSize) {
        return userService.getList(pageNo, pageSize);
    }
}
