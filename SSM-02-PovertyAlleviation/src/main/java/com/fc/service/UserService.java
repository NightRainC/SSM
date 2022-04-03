package com.fc.service;

import com.fc.entity.User;

import java.util.Map;

public interface UserService {
     //添加一个
     Map<String, Object> add(User user);
     //分页
     Map<String, Object> getList(String pageNo, String pageSize);

    Map<String, Object> del(Long id);

    Map<String, Object> update(User user);
}
