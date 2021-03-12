package com.example.demo.util;

import com.example.demo.BaseTest;
import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordTest extends BaseTest {
    @Test
    public void desTest() {
        String password = "sdhfaljkhfla";
        System.out.println(password);
        String s = DesUtil.encryptBasedDes(password);
        System.out.println(s);
        String res = DesUtil.decryptBasedDes(s);
        System.out.println(res);
    }
}