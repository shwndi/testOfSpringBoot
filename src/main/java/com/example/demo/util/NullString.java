package com.example.demo.util;

/**
 * @author czy
 * @date 2021/3/19
 */
public class NullString {
    public static void main(String[] args) {
        String s = null;
        String[] split = s.split(",");
        System.out.println(split);
        System.out.println(split.length);
    }
}
