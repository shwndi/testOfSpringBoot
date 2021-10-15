package com.example.demo.datasource.jdbc;

import com.example.demo.bo.UnitBO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 换数据库导表
 *
 * @author czy
 * @date 2021/5/17
 */
public class DataBase2Other {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        List<UnitBO> list = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement dynamic = null;
        Connection conn = null;

        Class.forName("oracle.jdbc.driver.OracleDriver");
        String url = "jdbc:oracle:thin:@192.168.2.135:1521:sw12366";
        String username = "araskyf";
        String password = "araskyf";
        String sql = "select UNITCODE,UNITJC,UNITNAME,UNITREGION," +
                "UNITPARENTCODE,UNITORDER,GXSJ,UNITJGLX,UNITCODELINK,JHZT,UNITLEVELCODE from t_xt_unit";
        conn = DriverManager.getConnection(url, username, password);
        dynamic = conn.prepareStatement(sql);
        rs = dynamic.executeQuery();
        while (rs.next()) {
            UnitBO bo = new UnitBO();
            List<Object> obList = new ArrayList<>(11);

            bo.setUnitCode(rs.getString(1));
            bo.setUnitName(rs.getString(2));
            bo.setUnitFullName(rs.getString(3));
            bo.setRegionCode(rs.getString(4));
            bo.setUnitParentCode(rs.getString(5));
            bo.setUnitOrder(rs.getInt(6));
            bo.setUpdateTime(rs.getTimestamp(7));
            bo.setUnitType(rs.getString(8));
            bo.setUnitCodeLink(rs.getString(9));
            bo.setDataSyncState(rs.getString(10));
            bo.setUnitLevel(rs.getString(11));
            bo.setUnitState("Y");

            list.add(bo);
        }
        System.out.println(list.size());
        extracted(rs, dynamic, conn);


    }

    private static void extracted(ResultSet rs, PreparedStatement dynamic, Connection conn) {
        // 关闭记录集
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // 关闭声明
        if (dynamic != null) {
            try {
                dynamic.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // 关闭连接对象
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
