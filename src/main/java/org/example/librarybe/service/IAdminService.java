package org.example.librarybe.service;

import org.example.librarybe.controller.dto.LoginDTO;
import org.example.librarybe.controller.request.AdminPageRequest;
import org.example.librarybe.controller.request.LoginRequest;
import org.example.librarybe.controller.request.PasswordRequest;
import org.example.librarybe.entity.Admin;

import java.util.List;

public interface IAdminService {

    List<Admin> list();

    List<Admin> page(AdminPageRequest adminPageRequest);

    void save(Admin admin);

    void update(Admin admin);

    void delete(Integer id);

    Admin getById(Integer id);

    LoginDTO login(String username);

    void changePassword(PasswordRequest passwordRequest);

    Admin loginByusername(String username);
}
