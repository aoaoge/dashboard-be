package org.example.librarybe.controller;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import org.example.librarybe.common.Result;
import org.example.librarybe.controller.request.UserPageRequest;
import org.example.librarybe.entity.User;
import org.example.librarybe.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping("/save")
    public Result save(@RequestBody User user) {
        Date date = new Date();
//        当做卡号
        user.setUsername(DateUtil.format(date, "yyyyMMdd") + IdUtil.fastSimpleUUID());
        userService.save(user);
        return Result.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        userService.update(user);
        user.setUpdateTime(new Date());
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        User user = userService.getById(id);
        return Result.success(user);
    }

    @GetMapping("list")
    public Result list() {
        List<User> user = userService.list();
        return Result.success(user);
    }

    @GetMapping("delete")
    public Result delete(Integer id) {
        userService.delete(id);
        return Result.success();
    }

    @GetMapping("page")
    public Result page(UserPageRequest userPageRequest) {
        return Result.success(userService.page(userPageRequest));
    }

}
