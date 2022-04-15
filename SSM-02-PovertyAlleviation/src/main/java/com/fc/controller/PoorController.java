package com.fc.controller;
import com.fc.entity.Poor;
import com.fc.entity.PoorWithBLOBs;
import com.fc.service.PoorService;
import com.fc.vo.RestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("poor")
public class PoorController {
    @Autowired
    private PoorService poorService;
    //增
    @PostMapping("add")
    public RestVo add(@RequestBody PoorWithBLOBs poor){
        return poorService.add(poor);
    }
    //删
    @GetMapping("delete")
    public RestVo delete(Long id){
       return poorService.delete(id);
    }
    //改
    @PostMapping("update")
    public RestVo update(@RequestBody PoorWithBLOBs poor){
        return poorService.update(poor);
    }
    //查
    @GetMapping("getlist")
    public RestVo getList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,PoorWithBLOBs poor) {
        return  poorService.getList(pageNum,pageSize,poor);
    }

    //点击
    @PostMapping("click")
    public RestVo click(@RequestBody Poor poor) {
        return  poorService.click(poor.getId(),poor.getLastClickTime());
    }

}
