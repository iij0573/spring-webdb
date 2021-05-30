package com.example.demo.domain;

import lombok.Data;

@Data
public class Book {
    private int bookNum;
    private String title;
    private String author;
    private float grade;
    private int stock;
    private Boolean rental;
}
