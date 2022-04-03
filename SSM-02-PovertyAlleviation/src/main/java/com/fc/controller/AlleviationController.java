package com.fc.controller;

import com.fc.entity.Alleviation;
import com.fc.service.AlleviationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("alleviation")
public class AlleviationController {
    @Autowired
    private AlleviationService alleviationService;
    //增
    @RequestMapping("add")
    public Map<String,Object> add(@RequestBody Alleviation alleviation){
        return  alleviationService.add(alleviation);
    }
    //删
    @RequestMapping("delete")
    public Map<String,Object> delete(Long id){
        return alleviationService.delete(id);
    }
    //改
    @RequestMapping("update")
    public Map<String,Object> update(@RequestBody Alleviation alleviation){
        return alleviationService.update(alleviation);
    }
    //查
    @RequestMapping("getList")
    public  Map<String,Object> getList(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){
        return  alleviationService.getList(pageNo,pageSize);
    }

}
