package com.springboot.data.mapper;

import com.springboot.data.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 这个注解表示这是一个mybatis mapper
 */
@Mapper
@Repository
public interface UserMapper {

    List<User> list(Pageable pageable);

    User findById(Long id);

    void save(User user);


}
