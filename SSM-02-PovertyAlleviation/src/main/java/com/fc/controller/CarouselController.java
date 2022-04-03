package com.fc.controller;

import com.fc.entity.Carousel;
import com.fc.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("carousel")
public class CarouselController {
    @Autowired
    private CarouselService carouselService;

    //增
    @RequestMapping("add")
    public Map<String, Object> add(@RequestBody Carousel carousel) {
        return carouselService.add(carousel);
    }

    //删
    @RequestMapping("del")
    public Map<String, Object> del(Integer id) {
        return carouselService.del(id);
    }

    //改
    @RequestMapping("update")
    public Map<String, Object> update(@RequestBody Carousel carousel) {
        return carouselService.update(carousel);
    }

    //查
    @RequestMapping("list")
    public Map<String, Object> list(@RequestParam(value = "carousel", defaultValue = "") String carousel) {
        return carouselService.getList(carousel);
    }
    //状态取反
    @RequestMapping("state")
    public  Map<String,Object> state(Integer id){
        return  carouselService.setState(id);
    }


}
