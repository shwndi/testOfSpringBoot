package com.example.demo.datasource.jdbc;

import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

public class ConnectSQLTest {
    @Resource
    private ConnectSQL sql;

    @Test
    public void dataConnect() {
        sql.dataConnect();
    }
}