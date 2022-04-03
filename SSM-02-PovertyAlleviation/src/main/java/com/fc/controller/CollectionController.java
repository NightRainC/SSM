package com.fc.controller;

import com.fc.entity.Collection;
import com.fc.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("collect")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;

    //添加
    @RequestMapping(value = "add", consumes = "application/json;charset=UTF-8")
    public Map<String, Object> add(@RequestBody Collection collection) {
        return collectionService.add(collection);
    }

    //删除
    @RequestMapping("del")
    public Map<String, Object> del(Long id) {
        return collectionService.del(id);
    }

    //获取分页内容
    @RequestMapping("list")
    public Map<String, Object> list(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        return collectionService.getList(pageNo, pageSize);
    }


}
