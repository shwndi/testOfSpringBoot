package com.example.demo.datasource.jdbc;

import com.example.demo.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

public class ConnectSQLTest extends BaseTest {
    @Resource
    private ConnectSQL sql;

    @Test
    public void dataConnect() {
        sql.dataConnect();
    }
}