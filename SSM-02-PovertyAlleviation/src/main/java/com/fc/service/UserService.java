package com.fc.service;

import com.fc.entity.User;
import com.fc.vo.RestVo;

public interface UserService {

    //添加一个
    RestVo add(User user);

    RestVo delete(Long id);

    RestVo update(User user);

    RestVo getList(Integer pageNum, Integer pageSize, User user);

    RestVo search(String keyword);
}
