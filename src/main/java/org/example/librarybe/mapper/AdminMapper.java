package org.example.librarybe.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.example.librarybe.controller.request.AdminPageRequest;
import org.example.librarybe.controller.request.LoginRequest;
import org.example.librarybe.entity.Admin;

import java.util.List;

@Mapper
public interface AdminMapper {

    List<Admin> list();

    List<Admin> page(AdminPageRequest adminPageRequest);

    void save(Admin admin);

    void update(Admin admin);

    void delete(Integer id);

    Admin getById(Integer id);

    Admin login(LoginRequest loginRequest);
}
