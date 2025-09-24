package org.example.librarybe.impl;


import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;
import org.example.librarybe.controller.dto.LoginDTO;
import org.example.librarybe.controller.request.AdminPageRequest;
import org.example.librarybe.controller.request.LoginRequest;
import org.example.librarybe.controller.request.PasswordRequest;
import org.example.librarybe.entity.Admin;
import org.example.librarybe.exception.ServiceException;
import org.example.librarybe.mapper.AdminMapper;
import org.example.librarybe.service.IAdminService;
import org.example.librarybe.utils.TokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AdminService implements IAdminService {

    private static final String PASS_SALT = "aoge";
    private static final String DEFAULT_PASS = "123";
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

        if (StrUtil.isBlank(admin.getPassword())) {
            admin.setPassword(DEFAULT_PASS);
        }
        admin.setPassword(SecureUtil.md5(admin.getPassword() + PASS_SALT)); // md5加密加盐
        try {
            adminMapper.save(admin);
        } catch (DuplicateKeyException e) {
            log.error("用户名{}已存在", admin.getUsername());
            throw new ServiceException("用户名已存在");
        }
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
    public LoginDTO login(String username) {
//        request.setPassword(securePass(request.getPassword()));
        Admin admin = adminMapper.loginByUsername(username);
        if (admin == null) {
            throw new ServiceException("用户名密码错误");
        }
        if (!admin.isStatus()) {
            throw new ServiceException("该用户已被禁用");
        }
        LoginDTO loginDTO = new LoginDTO();

        BeanUtils.copyProperties(admin, loginDTO);

        // 生成token
        String token = TokenUtils.genToken(String.valueOf(admin.getId()), admin.getPassword());

        loginDTO.setToken(token);
        return loginDTO;
    }

    @Override
    public void changePassword(PasswordRequest passwordRequest) {
        passwordRequest.setNewPass(securePass(passwordRequest.getNewPass()));
        passwordRequest.setPassword(securePass(passwordRequest.getPassword()));
        Integer count = adminMapper.changePassword(passwordRequest);

        if (count <= 0) {
            throw new ServiceException("修改密码失败");
        }
    }

    @Override
    public Admin loginByusername(String username) {
        return adminMapper.loginByUsername(username);
    }

    private String securePass(String pass) {
        return SecureUtil.md5(pass + PASS_SALT);
    }

}
