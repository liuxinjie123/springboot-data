package com.springboot.data.service.impl;

import com.springboot.data.mapper.UserMapper;
import com.springboot.data.pojo.User;
import com.springboot.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> list(Pageable pageable) {
        return userMapper.list(pageable);
    }

    @Override
    public User findById(Long id) {
        return userMapper.findById(id);
    }

    @Override
    public void save(User user) {
        if (null == user.getId()) {
            userMapper.save(user);
        } else {
            userMapper.update(user);
        }
    }
}
