package com.fc.controller;

import com.fc.entity.Collection;
import com.fc.service.CollectionService;
import com.fc.vo.RestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("collection")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;

    //添加
    @PostMapping("add")
    public RestVo add(@RequestBody Collection collection) {
        return collectionService.add(collection);
    }

    //删除
    @GetMapping("delete")
    public RestVo del(Long id) {
        return collectionService.delete(id);
    }

    //
    @PostMapping("update")
    public RestVo update(@RequestBody Collection collection) {
        return collectionService.update(collection);
    }

    //获取分页内容
    @GetMapping("getlist")
    public RestVo list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNo, @RequestParam(value = "pageSize", defaultValue = "3")Integer  pageSize, Collection collection) {
        return collectionService.getList(pageNo, pageSize, collection);
    }


}
