package com.example.demo.domain;

import lombok.Data;

@Data
public class Member {
    private String id;
    private String name;
    private String email;
    private String password;
    private String tel;
}
