package com.stone.auto.test.interfaces.util.loadData;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class loadJsonFile {

    private String sourcePath;

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public List<Map<String, String>> getFileListMap(String sourcePath) throws IOException
    {
        List<Map<String, String>> list = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();
        jsonArray = getFileJsonArray(sourcePath);

        for(Object mapList : jsonArray){
            Map map = new HashMap();
            for (Object entry : ((Map)mapList).entrySet()){
                map.put(((Map.Entry)entry).getKey(),((Map.Entry)entry).getValue());
            }
            list.add(map);
        }
        return list;
    }
    /**/
    public JSONArray getFileJsonArray(String sourcePath) throws IOException
    {
        File directory = new File(".");
        setSourcePath(directory.getCanonicalPath() + "\\src\\main\\resources\\data" + sourcePath);
        //用来存储读出来的Json数组
        JSONArray jsonArray = new JSONArray();
        // 使用ArrayList来存储每行读取到的字符串
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            File file = new File(this.getSourcePath());
            InputStreamReader inputReader = new InputStreamReader(new FileInputStream(file));
            BufferedReader bf = new BufferedReader(inputReader);
            // 按行读取字符串
            String str;
            while ((str = bf.readLine()) != null) {
                JSONObject jsonObject = new JSONObject();
                jsonObject = JSONObject.parseObject(str);
                jsonArray.add(jsonObject);
                //arrayList.add(str);
            }
            bf.close();
            inputReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }



}
