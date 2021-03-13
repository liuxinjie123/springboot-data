package com.springboot.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class User implements Serializable {
    private Long id;

    private LocalDateTime createTime;

    private String username;

    private String phone;

    private String password;

    private int status;
}
