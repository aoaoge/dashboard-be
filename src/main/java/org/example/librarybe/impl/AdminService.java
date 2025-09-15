package org.example.librarybe.impl;


import lombok.extern.slf4j.Slf4j;
import org.example.librarybe.common.Result;
import org.example.librarybe.controller.dto.LoginDTO;
import org.example.librarybe.controller.request.AdminPageRequest;
import org.example.librarybe.controller.request.LoginRequest;
import org.example.librarybe.entity.Admin;
import org.example.librarybe.mapper.AdminMapper;
import org.example.librarybe.service.IAdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AdminService implements IAdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public List<Admin> list() {
        return adminMapper.list();
    }

    @Override
    public List<Admin> page(AdminPageRequest adminPageRequest) {
        return adminMapper.page(adminPageRequest);
    }

    @Override
    public void save(Admin admin) {
        adminMapper.save(admin);
    }

    @Override
    public void update(Admin admin) {
        adminMapper.update(admin);
    }

    @Override
    public void delete(Integer id) {
        adminMapper.delete(id);
    }

    @Override
    public Admin getById(Integer id) {
        return adminMapper.getById(id);
    }

    @Override
    public LoginDTO login(LoginRequest loginRequest) {
        LoginDTO loginDTO = null;
        try {
            Admin admin = adminMapper.login(loginRequest);
            loginDTO = new LoginDTO();
            BeanUtils.copyProperties(admin, loginDTO);
            return loginDTO;
        }
        catch(Exception e) {
            log.error("登录失败");
            return null;
        }
    }
}
