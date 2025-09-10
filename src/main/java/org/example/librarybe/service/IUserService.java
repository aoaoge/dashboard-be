package org.example.librarybe.service;

import org.example.librarybe.controller.request.UserPageRequest;
import org.example.librarybe.entity.User;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IUserService {

    List<User> list();

    Object page(UserPageRequest userPageRequest);

    void save(User user);

    User getById(Integer id);

    void delete(Integer id);

    void update(User user);
}
