package com.fc.service.impl;

import com.fc.dao.UserMapper;
import com.fc.entity.User;
import com.fc.entity.UserExample;
import com.fc.service.UserService;
import com.fc.vo.DataVo;
import com.fc.vo.RestVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public RestVo add(User user) {
        if (user.getCreateTime() == null) {
            user.setCreateTime(new Date());
        }
        if (userMapper.insertSelective(user) != 1) {
            return new RestVo(-100, false, "fail", null);
        }

        return new RestVo(200, true, "OK", user);
    }

    @Override
    public RestVo delete(Long id) {
        if (userMapper.deleteByPrimaryKey(id) != 1) {
            return new RestVo(-10, false, "fail", null);
        }
        return new RestVo(200, true, "OK", null);
    }

    @Override
    public RestVo update(User user) {
        if (userMapper.updateByPrimaryKeySelective(user) != 1) {
            return new RestVo(-1000, false, "fail", null);
        }
        User data = userMapper.selectByPrimaryKey(user.getId());
        return new RestVo(200, true, "OK", data);
    }


    @Override
    public RestVo getList(Integer pageNum, Integer pageSize, User user) {
        try {
            UserExample example = null;
            if (user != null) {
                example = new UserExample();
                UserExample.Criteria criteria = example.createCriteria();
                if (user.getId() != null) {
                    criteria.andIdEqualTo(user.getId());

                }

                if (user.getUsername() != null)
                    criteria.andUsernameLike("%" + user.getUsername() + "%");

                if (user.getName() != null)
                    criteria.andNameLike("%" + user.getName() + "%");

                if (user.getGender() != null)
                    criteria.andGenderEqualTo(user.getGender());

            }
            pageNum = Math.max(pageNum, 1);
            pageSize = Math.max(pageSize, 1);
            PageHelper.startPage(pageNum, pageSize);
            List<User> list = userMapper.selectByExample(example);
            PageInfo<User> pageInfo = new PageInfo<>(list);
            DataVo<User> dataVo = new DataVo<>(pageInfo.getTotal(), list, pageNum, pageSize);
            return new RestVo(200, true, "OK", dataVo);
        } catch (Exception e) {
            return new RestVo(-400, false, "fail", null);
        }
    }

    @Override
    public RestVo search(String keyword) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andNameLike("%" + keyword + "%");
        List<User> users = userMapper.selectByExample(example);
        if (users == null)
            return new RestVo(-600, false, "查无此人", null);
        return new RestVo(200, true, "OK", users);
    }

}
