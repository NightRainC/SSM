package com.fc.controller;

import com.fc.entity.PoorWithBLOBs;
import com.fc.service.PoorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("poor")
public class PoorController {
    @Autowired
    private PoorService poorService;
    //增
    @RequestMapping("add")
    public Map<String,Object> add(PoorWithBLOBs poor){
        return poorService.add(poor);
    }
    //删
    @RequestMapping("delete")
    public Map<String ,Object> delete(Long id){
       return poorService.delete(id);
    }
    //改
    @RequestMapping("update")
    public Map<String,Object> update(@RequestBody PoorWithBLOBs poor){
        return poorService.update(poor);
    }
    //查
    @RequestMapping("getList")
    public Map<String, Object> getList(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        return  poorService.getList(pageNo,pageSize);
    }
}
