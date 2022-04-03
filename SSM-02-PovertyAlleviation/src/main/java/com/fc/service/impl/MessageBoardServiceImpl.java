package com.fc.service.impl;

import com.fc.dao.MessageBoardMapper;
import com.fc.entity.MessageBoardExample;
import com.fc.entity.MessageBoardWithBLOBs;
import com.fc.service.MessageBoardService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageBoardServiceImpl implements MessageBoardService {
    static private final Map<String, Object> map = new HashMap<>();
    static private final Map<String, Object> errMap = new HashMap<>();
    @Autowired
    private MessageBoardMapper mapper;
    @Override
    public Map<String, Object> getList(Integer pageNo, Integer pageSize, String userId) {
        pageNo = Math.max(1, pageNo);
        pageSize = Math.max(1, pageSize);

        map.put("code", 500);
        map.put("success", false);
        map.put("message", "数据库中无数据");
        errMap.put("errMsg", "数据库中无数据");
        map.put("data", errMap);

        PageHelper.startPage(pageNo, pageSize);
        List<MessageBoardWithBLOBs> list = null;

        if(userId ==null){
          list = mapper.selectByExampleWithBLOBs(null);
        }else{
            MessageBoardExample example = new MessageBoardExample();
            MessageBoardExample.Criteria criteria = example.createCriteria();
            criteria.andUserIdEqualTo(Long.parseLong(userId));
            list= mapper.selectByExampleWithBLOBs(example);
        }


        if (list == null) return map;
        PageInfo<MessageBoardWithBLOBs> pageInfo = new PageInfo<>(list);

        map.put("code", 200);
        map.put("success", true);
        map.put("message", "OK");
        map.put("data", pageInfo);
        return map;
    }

    @Override
    public Map<String, Object> add(MessageBoardWithBLOBs messageBoardWithBLOBs) {
        map.put("code", 500);
        map.put("success", false);
        map.put("message", "添加失败！");
        errMap.put("errMsg", "请求参数为null");
        map.put("data", errMap);
        if(messageBoardWithBLOBs == null)return map;

        int row = mapper.insert(messageBoardWithBLOBs);
        if(row < 1){
            errMap.put("errMsg","主键重复或请求参数异常");
            map.put("data",errMap);
            return map;
        }

        map.put("code", 200);
        map.put("success", true);
        map.put("message", "OK");
        map.put("data", messageBoardWithBLOBs);
        return map;
    }

    @Override
    public Map<String, Object> delete(Long id) {
        map.put("code", 404);
        map.put("success", false);
        map.put("message", "删除失败！");
        errMap.put("errMsg", "请求参数为null");
        map.put("data", errMap);
        if(id == null)return  map;
        int i = mapper.deleteByPrimaryKey(id);
        if(i < 1){
            errMap.put("errMsg","数据库无此ID");
            map.put("data",errMap);
            return map;
        }
        map.put("code", 200);
        map.put("success", true);
        map.put("message", "OK");
        map.put("data", null);
        return map;
    }

    @Override
    public Map<String, Object> reply(Long id, String reply, String replyPicture, boolean isDel) {
        map.put("code", 404);
        map.put("success", false);
        map.put("message", "回复失败！");
        errMap.put("errMsg", "主键不能为null");
        map.put("data", errMap);
        if(id == null)return  map;
        MessageBoardExample example = new MessageBoardExample();
        MessageBoardExample.Criteria criteria = example.createCriteria();

        map.put("code", 200);
        map.put("success", true);
        map.put("message", "OK");
        if(isDel){
            mapper.deleteByPrimaryKey(id);
            map.put("data", null);
            return map;
        }
        MessageBoardWithBLOBs msg = mapper.selectByPrimaryKey(id);
        if(msg == null){
            map.put("code",500);
            map.put("success", false);
            map.put("message", "OK");
            errMap.put("errMsg","数据库中无此ID");
            map.put("data", errMap);
            return map;
        }
        msg.setReply(reply);
        msg.setReplyPicture(replyPicture);

        if (mapper.insert(msg) != 1) {
            map.put("code", 500);
            map.put("success", false);
            map.put("message", "插入失败！");
            errMap.put("errMsg", "数据库异常");
            map.put("data", errMap);
        }
        map.put("data", msg);
        return map;
    }

}
