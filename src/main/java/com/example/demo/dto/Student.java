package com.example.demo.dto;

import lombok.Data;

/**
 * @author czy
 * @date 2021/3/26
 */
@Data
public class Student {
    public String name;
    public Student(Object name){
        this.name = name.toString();
    }
}
