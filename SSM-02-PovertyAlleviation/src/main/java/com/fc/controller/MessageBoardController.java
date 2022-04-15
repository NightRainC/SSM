package com.fc.controller;
import com.fc.entity.MessageBoardWithBLOBs;
import com.fc.service.MessageBoardService;
import com.fc.vo.RestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("msgboard")
public class MessageBoardController {
    @Autowired
    private MessageBoardService messageBoardService;
    //增
    @PostMapping("add")
    public RestVo add(@RequestBody MessageBoardWithBLOBs messageBoardWithBLOBs) {
    return messageBoardService.add(messageBoardWithBLOBs);
    }
    //删
    @GetMapping("delete")
    public RestVo delete(Long id){
        return  messageBoardService.delete(id);
    }
    //回复
    @PostMapping("reply")
    public  RestVo reply(Long id,String reply,String replyPicture,boolean isDel){
    return  messageBoardService.reply(id,reply,replyPicture,isDel);
    }
    @PostMapping("update")
    public RestVo update(@RequestBody MessageBoardWithBLOBs message){
        return  messageBoardService.update(message);
    }
    //查
    @GetMapping("getlist")
    public RestVo getList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize, MessageBoardWithBLOBs message) {
        return messageBoardService.getList(pageNum,pageSize,message);
    }

}
