package com.fc.service.impl;

import com.fc.dao.MessageBoardMapper;
import com.fc.entity.MessageBoardExample;
import com.fc.entity.MessageBoardWithBLOBs;
import com.fc.service.MessageBoardService;
import com.fc.vo.DataVo;
import com.fc.vo.RestVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MessageBoardServiceImpl implements MessageBoardService {
    @Autowired
    private MessageBoardMapper mapper;


    @Override
    public RestVo add(MessageBoardWithBLOBs messageBoard) {
        if (messageBoard != null && messageBoard.getCreateTime() == null) {
            messageBoard.setCreateTime(new Date());
        }
        if (mapper.insertSelective(messageBoard) != 1) {
            return new RestVo(-100, false, "添加失败", null);
        }
        return new RestVo(200, true, "添加成功", messageBoard);
    }

    @Override
    public RestVo delete(Long id) {
        try {
            mapper.deleteByPrimaryKey(id);
            return new RestVo(200, true, "删除成功", null);
        } catch (Exception e) {
            return new RestVo(-100, false, "发生了未知的错误", null);
        }
    }

    @Override
    public RestVo reply(Long id, String reply, String replyPicture, boolean isDel) {
        if (isDel) {
            mapper.deleteByPrimaryKey(id);
            return new RestVo(200, true, "删除成功", null);
        }
        MessageBoardWithBLOBs data = mapper.selectByPrimaryKey(id);
        if (reply != null) data.setReply(reply);
        if (replyPicture != null) data.setReplyPicture(replyPicture);
        if (mapper.insertSelective(data) < 1) {
            return new RestVo(-100, false, "回复失败", null);
        }

        return new RestVo(200, true, "回复成功", data);
    }

    @Override
    public RestVo update(MessageBoardWithBLOBs message) {
        if (mapper.updateByPrimaryKeySelective(message) < 1){
            return  new RestVo(-400,false,"修改失败",null);
        }
        MessageBoardWithBLOBs data = mapper.selectByPrimaryKey(message.getId());
        return new RestVo(200,true,"修改成功",data);
    }

    @Override
    public RestVo getList(Integer pageNum, Integer pageSize, MessageBoardWithBLOBs message) {
        try {
            MessageBoardExample example = null;
            if (message != null) {
                example = new MessageBoardExample();
                MessageBoardExample.Criteria criteria = example.createCriteria();
                if (message.getId() != null) {
                    criteria.andIdEqualTo(message.getId());
                }
                if (message.getUserId() != null) {
                    criteria.andUserIdEqualTo(message.getUserId());
                }
                if (message.getUsername() != null) {
                    criteria.andUsernameLike("%" + message.getUsername() + "%");
                }
                if (message.getReply() != null) {
                    criteria.andReplyPictureLike("%" + message.getReply() + "%");
                }
            }
            pageNum = Math.max(pageNum, 1);
            pageSize = Math.max(pageSize, 1);
            PageHelper.startPage(pageNum, pageSize);
            List<MessageBoardWithBLOBs> list = mapper.selectByExampleWithBLOBs(example);
            PageInfo<MessageBoardWithBLOBs> pageInfo = new PageInfo<>(list);
            DataVo<MessageBoardWithBLOBs> data = new DataVo<>(pageInfo.getTotal(), list, pageNum, pageSize);
            return new RestVo(200, true, "OK", data);
        } catch (Exception e) {
            e.printStackTrace();
            return new RestVo(-400, false, "你的操作有误,不会让管理员教你", null);
        }

    }
}
