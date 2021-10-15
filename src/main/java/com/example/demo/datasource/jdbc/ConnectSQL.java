package com.example.demo.datasource.jdbc;

import org.springframework.context.annotation.Configuration;

import java.sql.*;

import static com.sun.activation.registries.LogSupport.log;

/**
 * 使用sql的数据库连接
 *
 * @author czy
 * @date 2021/3/11
 */
@Configuration
public class ConnectSQL {
    public void dataConnect() {
        ResultSet rs = null;
        PreparedStatement dynamic = null;
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            String url = "jdbc:oracle:thin:@192.168.2.165:1521:SCJG";
            String username = "SCJG_WRITE";
            String password = "SCJG_WRITE";
            String sql =          "select u.unitjc,sum(case when t.infotype='1' then 1 else 0 end) ts "+
                    "from(select cljg,infotype,slbz from ywsl_djnrb a where to_char(a.regtime,'yyyy-MM-dd hh24:mi:ss')>=? "+
                                       " and to_char(a.regtime,'yyyy-MM-dd hh24:mi:ss')<='2020-08-25 23:59:59' "+
            " and a.blqkzt IN('4','9','5') "+
            "    and a.cljg in (select ','||unitcode||',' from t_xt_unit where unitcodelink like '100000000152000000%') "+
            " union all "+
            "  select cljg,infotype,slbz from ywsl_djnrb_hi a  where to_char(a.regtime,'yyyy-MM-dd hh24:mi:ss')>=? "+
            "           and to_char(a.regtime,'yyyy-MM-dd hh24:mi:ss')<='2020-08-25 23:59:59' "+
            "    and a.blqkzt IN('4','9','5') "+
            "    and a.cljg in (select ','||unitcode||',' from t_xt_unit where unitcodelink like '100000000152000000%' ) "+
            " ) t right join (select unitcode,unitjc from t_xt_unit where unitcode like '152__0000') u "+
            "   on  u.unitcode=substr(t.cljg,2,5)||'0000' "+
            "    group by u.unitjc,u.unitcode";
            conn = DriverManager.getConnection(url, username, password);

            dynamic = conn.prepareStatement(sql);
            dynamic.setString(1, "2019-01-01");
            dynamic.setString(2, "2019-01-01");
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
