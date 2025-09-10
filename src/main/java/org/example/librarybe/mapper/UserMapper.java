package org.example.librarybe.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.example.librarybe.controller.request.UserPageRequest;
import org.example.librarybe.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {

//    @Select("select * from user")
    List<User> list();

    List<User> listByCondition(UserPageRequest userPageRequest);

    void save(User user);
}
