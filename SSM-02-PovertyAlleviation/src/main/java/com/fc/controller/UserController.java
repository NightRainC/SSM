package com.fc.controller;
import com.fc.entity.User;
import com.fc.service.UserService;
import com.fc.vo.RestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    //增
    @PostMapping("add")
    public RestVo add(@RequestBody User user ) {
        return userService.add(user);
    }

    //删
    @GetMapping("delete")
    public RestVo delete(@RequestParam("id") Long id) {
        return userService.delete(id);
    }

    //改
    @PostMapping("update")
    public RestVo update(@RequestBody User user) {
        return userService.update(user);
    }

    //查
    @GetMapping("getlist")
    public RestVo getList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize,
                          User user) {
        return userService.getList(pageNum, pageSize, user);
    }

    @GetMapping("search")
    public  RestVo search(String name){
        return  userService.search(name);
    }

}
