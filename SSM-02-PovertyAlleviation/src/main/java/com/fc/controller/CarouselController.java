package com.fc.controller;

import com.fc.entity.Carousel;
import com.fc.service.CarouselService;
import com.fc.vo.RestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("carousel")
public class CarouselController {
    @Autowired
    private CarouselService carouselService;

    //增
    @PostMapping("add")
    public RestVo add(@RequestBody Carousel carousel) {
        return carouselService.add(carousel);
    }

    //删
    @GetMapping("delete")
    public RestVo delete(Integer id) {
        return carouselService.delete(id);
    }

    //改
    @PostMapping("update")
    public RestVo update(@RequestBody Carousel carousel) {
        return carouselService.update(carousel);
    }

    //查
    @GetMapping("getlist")

    public RestVo getList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize,
                          Carousel carousel) {
        return carouselService.getList(pageNum, pageSize, carousel);
    }

    //状态取反
    @GetMapping("state")
    public RestVo state(Integer id) {
        return carouselService.setState(id);
    }


}
