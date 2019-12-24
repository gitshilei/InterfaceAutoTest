package com.stone.auto.test.interfaces.util.loadData;

import com.alibaba.fastjson.JSONArray;
import org.apache.commons.collections4.list.TreeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class loadLogFile {

    //获取日志中抓取的参数以List<Map<String,String>>结构返回
    public List<Map<String,String>> getLogListMap()
    {
        List<Map<String,String>> list = new ArrayList<>();
        return list;
    }

    //获取日志中抓取的参数以JSONArray结构返回
    public JSONArray getLogJsonArray()
    {
        JSONArray jsonArray = new JSONArray();
        return jsonArray;
    }

    //解析日志文件内容
    public Map<String,String> analysisLogFile()
    {
        Map<String,String> map = new HashMap<>();
        return map;
    }

    //遍历日志文件夹
    public List<String> ergodicLogFolder()
    {
        List<String> list = new TreeList<>();
        return list;
    }

}
