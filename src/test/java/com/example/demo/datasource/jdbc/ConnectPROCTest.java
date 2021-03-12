package com.example.demo.datasource.jdbc;

import com.example.demo.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

public class ConnectPROCTest extends BaseTest {
    @Resource
    private ConnectPROC proc;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void dataConnect() {
        proc.dataConnect();
    }
}