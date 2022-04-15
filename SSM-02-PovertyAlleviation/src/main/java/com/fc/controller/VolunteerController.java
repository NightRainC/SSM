package com.fc.controller;
import com.fc.entity.VolunteerRecruitment;
import com.fc.service.VolunteerService;
import com.fc.vo.RestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("volunteer")
public class VolunteerController {
    @Autowired
    private VolunteerService volunteerService;

    //增
    @PostMapping("add")
    public RestVo add(@RequestBody VolunteerRecruitment volunteer) {
        return volunteerService.add(volunteer);
    }

    //删
    @GetMapping("delete")
    public RestVo delete(Long id) {
        return volunteerService.delete(id);
    }

    //改
    @PostMapping("update")
    public RestVo update(@RequestBody VolunteerRecruitment volunteer) {
        return volunteerService.update(volunteer);
    }

    //查
    @GetMapping("getlist")
    public RestVo getList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize, VolunteerRecruitment volunteer) {
        return volunteerService.getList(pageNum, pageSize, volunteer);
    }
    @PostMapping("click")
    public RestVo click(@RequestBody VolunteerRecruitment volunteer){
        return volunteerService.click(volunteer.getId(),volunteer.getLastClickTime());
    }

}
