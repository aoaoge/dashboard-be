package org.example.librarybe.controller;

import org.example.librarybe.common.Result;
import org.example.librarybe.controller.request.UserPageRequest;
import org.example.librarybe.entity.User;
import org.example.librarybe.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping
    public Result save(@RequestBody User user) {
        userService.save(user);
        return Result.success();
    }


    @GetMapping("list")
    public Result list() {
        List<User> user = userService.list();
        return Result.success(user);
    }

    @GetMapping("page")
    public Result page(UserPageRequest userPageRequest) {
        return Result.success(userService.page(userPageRequest));
    }

}
