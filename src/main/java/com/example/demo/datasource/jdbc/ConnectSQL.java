package com.example.demo.datasource.jdbc;

import java.sql.*;

import static com.sun.activation.registries.LogSupport.log;

/**
 * 使用sql的数据库连接
 *
 * @author czy
 * @date 2021/3/11
 */
public class ConnectSQL {
    public void dataConnect() {
        ResultSet rs = null;
        PreparedStatement dynamic = null;
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            String url = "jdbc:oracle:thin:@192.168.2.135:1521:sw12366";
            String username = "araskyf";
            String password = "araskyf";
            String sql = "select  * from QA_MIDDLE_DATA where STATISTICE_DATE > ? ";
            conn = DriverManager.getConnection(url, username, password);

            dynamic = conn.prepareStatement(sql);
            dynamic.setString(1, "2021-01-10");
            rs = dynamic.executeQuery();
            StringBuffer s;
            while (rs.next()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                System.out.print("{");
                for (int i = 1; i <= columnCount; i++) {
                    String name = rsmd.getColumnName(i);
                    String string = rs.getString(i);
                    System.out.print(" [" +name +":"+string+"] ");
                }
                System.out.println("}");
            }
            System.out.println("****************\n******************\n******************************\n********************************\n***************************");
            while (rs.next()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                System.out.print("{");
                for (int i = 1; i <= columnCount; i++) {
                    String name = rsmd.getColumnName(i);
                    String string = rs.getString(i);
                    System.out.print(" [" +name +":"+string+"] ");
                }
                System.out.println("}");
            }
        } catch (Exception e) {
            log("数据查询出错");
            e.printStackTrace();
        }
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
