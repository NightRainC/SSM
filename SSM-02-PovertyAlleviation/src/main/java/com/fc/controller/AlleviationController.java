package com.fc.controller;
import com.fc.entity.Alleviation;
import com.fc.service.AlleviationService;
import com.fc.vo.RestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("alleviation")
public class AlleviationController {
    @Autowired
    private AlleviationService alleviationService;
    //增
    @PostMapping("add")
    public RestVo add(@RequestBody Alleviation alleviation){
        return  alleviationService.add(alleviation);
    }
    //删
    @GetMapping ("delete")
    public RestVo delete(Long id){
        return alleviationService.delete(id);
    }
    //改
    @PostMapping("update")
    public RestVo update(@RequestBody Alleviation alleviation){
        return alleviationService.update(alleviation);
    }
    //查
    @GetMapping("getlist")
    public  RestVo getList(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,@RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,Alleviation alleviation){
        return  alleviationService.getList(pageNum,pageSize,alleviation);
    }


    //
    @PostMapping("click")
    public RestVo click(@RequestBody Alleviation alleviation){
        return  alleviationService.click(alleviation.getId(),alleviation.getLastClickTime());
    }

}
