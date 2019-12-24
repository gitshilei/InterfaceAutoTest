package com.stone.auto.test.interfaces.util.loadData;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.stone.auto.test.interfaces.params.DataBaseParams;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class loadDataBase {

    private DataBaseParams dataBaseParams;

    public DataBaseParams getDataBaseParams() {
        return dataBaseParams;
    }

    public void setDataBaseParams(DataBaseParams dataBaseParams) {
        this.dataBaseParams = dataBaseParams;
    }

    public  loadDataBase()
    {
        DataBaseParams dataBaseParams = new DataBaseParams();
        this.setDataBaseParams(dataBaseParams);
        dataBaseParams.setIp("10.86.33.29");
        dataBaseParams.setPort("3344");
        dataBaseParams.setBatabase("car_qbest");
        dataBaseParams.setUser("car_beta");
        dataBaseParams.setPassword("carqatest");
        dataBaseParams.setTable("driver_nps_record");
    }

    public  List<Map<String,String>> getDBListMap() throws Exception
    {

        PreparedStatement prepareStatement = null;
        ResultSet rs = null;

        List<String> columnsName = new ArrayList<>();
        List<Map<String,String>> listMapDB = new ArrayList<>();


        Connection con = connectDB();

        columnsName = getColumnsName(con);
        //System.out.println(columnsName);
        listMapDB = getCaseRecord(con,prepareStatement,rs,columnsName);
        //System.out.println(listMapDB);


        return listMapDB;
    }

    public JSONArray getDBJsonArray() throws Exception
    {
        JSONArray jsonArray = new JSONArray();
        PreparedStatement prepareStatement = null;
        ResultSet rs = null;

        List<String> columnsName = new ArrayList<>();
        List<Map<String,String>> listMapDB = new ArrayList<>();


        Connection con = connectDB();

        columnsName = getColumnsName(con);

        listMapDB = getCaseRecord(con,prepareStatement,rs,columnsName);
        jsonArray = JSONArray.parseArray(JSON.toJSONString(listMapDB));
        return jsonArray;
    }

    public  Connection connectDB() throws Exception
    {
        Connection connection =null;
        StringBuilder url = new StringBuilder();
        url.append("jdbc:mysql://");
        url.append(this.dataBaseParams.getIp());
        url.append(":");
        url.append(this.dataBaseParams.getPort());
        url.append("/");
        url.append(this.dataBaseParams.getBatabase());
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url.toString(), this.dataBaseParams.getUser(), this.dataBaseParams.getPassword());
        return connection;

    }

    public List<String> getColumnsName(Connection connection) throws Exception
    {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet columns = metaData.getColumns(null, "%", this.dataBaseParams.getTable() , "%");
        // 列
        List<String> colslist = new ArrayList<String>();
        while (columns.next()) {
            if (columns == null) break;
            String COLUMN_NAME = columns.getString("COLUMN_NAME");
            //String TYPE_NAME = columns.getString("TYPE_NAME");
            colslist.add(COLUMN_NAME /*+ "|" + TYPE_NAME*/);
        }
        return colslist;
    }
    public List<Map<String,String>> getCaseRecord(Connection connection,PreparedStatement prepareStatement,ResultSet resultSet,List<String> columns) throws Exception
    {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from ");
        sql.append(this.dataBaseParams.getTable());
        PreparedStatement ps= prepareStatement;
        ps = connection.prepareStatement(sql.toString());
        ResultSet rs = resultSet;
        // 设置参数
        //prepareStatement.setString(1, "order_undone");
        // 执行查询
        rs = ps.executeQuery(sql.toString());
        List<Map<String,String>> resListMap = new ArrayList<>();
        // 处理结果集
        while (rs.next()) {
          //System.out.println(rs.getString("id"));
            List<String> columnsData = new ArrayList<>();
            Map<String,String> map = new HashMap<>();
            for (int i = 0;i<columns.size();i++)
            {
                String col = columns.get(i);
                map.put(col,rs.getString(col));
            }
            resListMap.add(map);
        }
        return resListMap;
    }


}
