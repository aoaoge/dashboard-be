package org.example.librarybe.controller;


import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.example.librarybe.common.Result;
import org.example.librarybe.controller.dto.LoginDTO;
import org.example.librarybe.controller.request.AdminPageRequest;
import org.example.librarybe.controller.request.LoginRequest;
import org.example.librarybe.controller.request.PasswordRequest;
import org.example.librarybe.entity.Admin;
import org.example.librarybe.exception.ServiceException;
import org.example.librarybe.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("admin")
public class AdminController {


    private static final String DEFAULT_PASS = "123";
    @Autowired
    IAdminService adminService;

    @PostMapping("login")
    public Result login(@RequestBody LoginRequest loginRequest) {
//        LoginDTO admin = adminService.login(loginRequest);
        LoginDTO admin = null;
        try {
            admin = adminService.login(loginRequest.getUsername());
        } catch (Exception e) {
            log.error("根据用户名{}查询出错", loginRequest.getUsername());
        }
        if (admin == null) {
            return Result.error("用户名或密码错误");
        }
        String securePass = securePass(loginRequest.getPassword());
        if (!admin.getPassword().equals(securePass)) {
            return Result.error("用户名或密码错误");
        }
        if (!admin.isStatus()) {
            return Result.error("当前用户已禁用，请联系管理员");
        }
        return Result.success(admin);
    }

    private static final String PASS_SALT = "aoge";
    private String securePass(String pass) {
        return SecureUtil.md5(pass + PASS_SALT);
    }

    @PostMapping("password")
    public Result changePassword(@RequestBody PasswordRequest passwordRequest) {
        adminService.changePassword(passwordRequest);
        return Result.success();
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
        try {
            adminService.save(admin);
        } catch (Exception e) {
            return Result.error("用户名已存在");
        }
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
