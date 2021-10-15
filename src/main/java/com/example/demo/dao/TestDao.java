package com.example.demo.dao;

import com.example.demo.bo.UnitBO;

/**
 * @author czy
 * @date 2021/3/11
 */
public interface TestDao {
    /**
     * 添加机构信息
     *
     * @param bo
     * @return
     */
    int saveUnit(UnitBO bo);

    /**
     *
     * @param unitCode
     */
    Integer get(String unitCode);
}
