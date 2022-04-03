package com.fc.controller;

import com.fc.entity.MessageBoard;
import com.fc.entity.MessageBoardWithBLOBs;
import com.fc.service.MessageBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("msgBoard")
public class MessageBoardController {
    @Autowired
    private MessageBoardService messageBoardService;
    //增
    @RequestMapping("add")
    public  Map<String,Object> add(@RequestBody MessageBoardWithBLOBs messageBoardWithBLOBs) {
    return messageBoardService.add(messageBoardWithBLOBs);
    }
    //删
    @RequestMapping("delete")
    public Map<String,Object> delete(Long id){
        return  messageBoardService.delete(id);
    }
    //回复
    @RequestMapping("reply")
    public  Map<String ,Object> reply(Long id,String reply,String replyPicture,boolean isDel){
    return  messageBoardService.reply(id,reply,replyPicture,isDel);
    }
    //查
    @RequestMapping("getList")
    public Map<String, Object> getList(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,String userId) {
        return messageBoardService.getList(pageNo,pageSize,userId);
    }

}
