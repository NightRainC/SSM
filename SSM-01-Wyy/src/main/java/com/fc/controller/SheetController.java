package com.fc.controller;

import com.fc.entity.TbMusic;
import com.fc.entity.TbSheet;
import com.fc.service.TbSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("sheet")
public class SheetController {
    @Autowired
    TbSheetService tbSheetService;
    //查找全部
    @RequestMapping("findAll")
    public List<TbSheet> findAll(){
        return  tbSheetService.findAll();
    }
    //按歌单查找
    @RequestMapping("findSongsBySheet")
    public List<TbMusic> findSongsBySheet(String sheetName){
        return tbSheetService.findSongsBySheet(sheetName);
    }
    //创建歌单
    @RequestMapping("insertSheet")
    public Map<String,Object> insertSheet(String sheetName){

        return tbSheetService.insertSheet(sheetName);
    }


    @RequestMapping("test")
    public String test(){
        return "test";
    }

}
