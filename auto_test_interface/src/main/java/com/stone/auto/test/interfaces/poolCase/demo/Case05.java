package com.stone.auto.test.interfaces.poolCase.demo;

import com.alibaba.fastjson.JSONArray;
import org.testng.annotations.Test;
import com.stone.auto.test.interfaces.util.loadData.loadDataBase;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Case05 {
    @Test
    public void TestCase02() throws Exception
    {

        loadDataBase ldb = new loadDataBase();
        ldb.getDataBaseParams().setTable("ab_test");
        List<Map<String,String>> list =  ldb.getDBListMap();
        JSONArray jsonArray =  ldb.getDBJsonArray();
        System.out.println(list);
        System.out.println(jsonArray);
    }
    @Test
    public void TestCase01() throws Exception
    {
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet rs = null;

        try {
            // 加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 获取连接
            String url = "jdbc:mysql://10.86.33.29:3344/car_qbest";
            String user = "car_beta";
            String password = "carqatest";
            connection = DriverManager.getConnection(url, user, password);
            DatabaseMetaData metaData = connection.getMetaData();
            Map<String, List<String>> map = new HashMap<String, List<String>>();
            // User表
            String tableName = "order_undone" ;
            ResultSet columns = metaData.getColumns(null, "%", tableName , "%");

            // 列
            List<String> colslist = new ArrayList<String>();
            while (columns.next()) {
                String COLUMN_NAME = columns.getString("COLUMN_NAME");
                //String TYPE_NAME = columns.getString("TYPE_NAME");
                colslist.add(COLUMN_NAME /*+ "|" + TYPE_NAME*/);
            }
            System.out.println(colslist);


                    // 获取statement，preparedStatement
            //String sql = "select * from order_undone where driver_id=?";
            String sql1 = "desc ?";
            prepareStatement = connection.prepareStatement(sql1);
            // 设置参数
            prepareStatement.setString(1, "order_undone");
            // 执行查询
            rs = prepareStatement.executeQuery(sql1);

            // 处理结果集
            while (rs.next()) {

                System.out.println();
                System.out.println(rs.getString("id"));
                System.out.println(rs.getString("driver_id"));
                System.out.println(rs.getString("order_id"));
                System.out.println(rs.getString("city_code"));
            }
        } finally {
            // 关闭连接，释放资源
            if (rs != null) {
                rs.close();
            }
            if (prepareStatement != null) {
                prepareStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

}
