package org.example.librarybe.service;

import org.example.librarybe.controller.request.UserPageRequest;
import org.example.librarybe.entity.User;

import java.util.List;

public interface IUserService {

    List<User> list();

    Object page(UserPageRequest userPageRequest);

    void save(User user);
}
