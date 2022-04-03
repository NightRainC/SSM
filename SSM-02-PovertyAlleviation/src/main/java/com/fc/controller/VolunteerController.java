package com.fc.controller;

import com.fc.entity.VolunteerRecruitment;
import com.fc.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("volunteer")
public class VolunteerController {
    @Autowired
    private VolunteerService volunteerService;
    //增
    @RequestMapping("add")
    public Map<String , Object> add(@RequestBody VolunteerRecruitment volunteer){
        return volunteerService.add(volunteer);
    }
    //删
    @RequestMapping("delete")
    public  Map<String , Object> delete(Long id){
        return  volunteerService.delete(id);
    }
    //改
    @RequestMapping("update")
    public Map<String,Object> update(@RequestBody VolunteerRecruitment volunteer){
        return  volunteerService.update(volunteer);
    }
    //查
    @RequestMapping("getList")
    public Map<String, Object> getList(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        return volunteerService.getList(pageNo,pageSize);
    }

}
