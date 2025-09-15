package org.example.librarybe.controller;


import com.github.pagehelper.PageHelper;
import org.example.librarybe.common.Result;
import org.example.librarybe.controller.dto.LoginDTO;
import org.example.librarybe.controller.request.AdminPageRequest;
import org.example.librarybe.controller.request.LoginRequest;
import org.example.librarybe.entity.Admin;
import org.example.librarybe.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("admin")
public class AdminController {


    @Autowired
    IAdminService adminService;

    @PostMapping("login")
    public Result login(@RequestBody LoginRequest LoginRequest) {
        LoginDTO admin = adminService.login(LoginRequest);
        if (admin == null) {
            return Result.error("用户名或密码错误");
        }
        return Result.success(admin);
    }

    @GetMapping("list")
    public Result list() {
        List<Admin> admin = adminService.list();
        return Result.success(admin);
    }

    @GetMapping("page")
    public Result page(AdminPageRequest adminPageRequest) {
        PageHelper.startPage(adminPageRequest.getPageNum(), adminPageRequest.getPageSize());
        List<Admin> admin = adminService.page(adminPageRequest);
        return Result.success(admin);
    }

    @PostMapping("save")
    public Result save(@RequestBody Admin admin) {
        adminService.save(admin);
        return Result.success();
    }

    @PostMapping("update")
    public Result update(@RequestBody Admin admin) {
        adminService.update(admin);
        return Result.success();
    }

    @GetMapping("delete/{id}")
    public Result delete(@PathVariable Integer id) {
        adminService.delete(id);
        return Result.success();
    }


    @GetMapping("getById")
    public Result getById(Integer id) {
        Admin admin = adminService.getById(id);
        return Result.success(admin);
    }

}
