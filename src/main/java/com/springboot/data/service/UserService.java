package com.springboot.data.service;

import com.springboot.data.pojo.User;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;

import java.util.List;

public interface UserService {
    List<User> list(SpringDataWebProperties.Pageable pageable);

    User findById(Long id);

    void save(User user);

}
