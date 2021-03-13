package com.springboot.data.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.springboot.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/jdbc")
@RestController
public class JDBCController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询数据库的所有信息
     * 没有实体类，数据库中的数据如何获取
     */
    @GetMapping("/user")
    public List<User> userList() {
        String sql = "select id, create_time createTime, username, phone, password, status from user";
        List<Map<String, Object>> userObjList = jdbcTemplate.queryForList(sql);
        List<User> userList = JSONObject.parseArray(JSON.toJSONString(userObjList), User.class);
        return userList;
    }

    @PostMapping("/user")
    public String addUser() {
        String sql = "insert into user (create_time, username, phone, password, status) " +
                " values (now(), 'jack', '13150333833', '111111', '1')";
        jdbcTemplate.update(sql);
        return "ADD SUCCESS";
    }

    @PutMapping("/user")
    public String updateUserById(@RequestBody User user) {
        String sql = "update user set username=?, phone=?, status=? where id=?";
        Object[] objects = new Object[4];
        objects[0] = user.getUsername();
        objects[1] = user.getPhone();
        objects[2] = user.getStatus();
        objects[3] = user.getId();
        jdbcTemplate.update(sql, objects);
        return "UPDATE SUCCESS";
    }

    @DeleteMapping("/user/{id}")
    public String deleteUserById(@PathVariable("id") Long id) {
        String sql = "delete from user where id=?";
        jdbcTemplate.update(sql, id);
        return "DELETE SUCCESS";
    }

}
