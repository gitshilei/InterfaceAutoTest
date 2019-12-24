package com.stone.auto.test.interfaces.poolCase.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Case04 {
    @Test
    public void Test11()
    {
        Map<String,Integer> map01 = new HashMap<>();
        map01.put("aa",11);
        map01.put("bb",22);
        map01.put("cc",33);
        map01.put("dd",44);

        JSONObject jsonObject01 = new JSONObject();
        jsonObject01.put("name",new Integer(2));
        jsonObject01.put("age",new Integer(22));
        jsonObject01.put("sex",new StringBuilder("男"));
        String jsonstr = new String("{\"sex\":\"男\",\"other\":{\"name\":2,\"age\":22}}");
        System.out.println(jsonObject01.toJSONString());

        JSONObject jsonObject02 = new JSONObject();
        jsonObject02 = JSONObject.parseObject(jsonstr);
        System.out.println(jsonObject02);

        JSONObject jsonObject03 = new JSONObject();
        jsonObject03 = JSON.parseObject(JSONObject.toJSONString(map01));



        System.out.println(jsonObject03);

    }
    @Test
    public void Test00()
    {

        Map<String,Integer> map01 = new HashMap<String, Integer>();
        Map<String,Integer> map02 = new Hashtable<String, Integer>();
        Map<String,Integer> map03 = new TreeMap<String, Integer>();
        Map<String,Integer> map04 = new LinkedHashMap<String, Integer>();
        Map<String,Integer> map05 = new ConcurrentHashMap<>();
        //HashMap是无序非线程安全的
        //Hashtable是无序线程安全的
        //TreeMap是按照key进行排序的非线程安全的
        //LinkedHashMap是有序且非线程安全的
/*        map01.put(null,8);
        System.out.println(map01);*/
/*        map05.put("w",1);
        map05.put("b",2);
        map05.put("d",3);
        map05.put("g",4);
        map05.put("f",5);
        map05.put("j",6);
        map05.put("h",7);
        map05.put("i",8);
        System.out.println(map05);*/

/*        map02.putAll(map01);
        map03.putAll(map01);
        map04.putAll(map01);
        System.out.println(map01);
        System.out.println(map02);
        System.out.println(map03);
        System.out.println(map04);
        System.out.println(map01.keySet());
        System.out.println(map01.entrySet());
        System.out.println(map01.get("b"));
        System.out.println(map01.hashCode());
        System.out.println(map01.size());
        System.out.println(map01.containsValue(3));
        System.out.println(map01.remove("b"));
        System.out.println(map01);
        System.out.println(map01.replace("e",24));
        System.out.println(map01);
        System.out.println(map01.replace("f",6,25));
        System.out.println(map01);*/



        List<Integer> list01 = new LinkedList<Integer>();
        List<Integer> list02 = new ArrayList<Integer>();
        List<Integer> list03 = new Vector<Integer>();
        list01.add(8);
        list01.add(9);
        list01.add(4);
        list01.add(5);
        list01.add(7);
        System.out.println(list01);
        list01.remove(2);
        System.out.println(list01);
        list01.remove(new Integer(8));
        System.out.println(list01);
        System.out.println(list01.size());

        Set<Integer> set01 = new TreeSet<Integer>();
        Set<Integer> set02 = new HashSet<Integer>();
        Set<Integer> set03 = new LinkedHashSet<Integer>();

        Queue<Integer> queue01 = new LinkedList<Integer>();
        Queue<Integer> queue02 = new PriorityQueue<Integer>();



    }
    @Test
    public void Test01()
    {
        // httpclient客户端，类似于一个浏览器，可以由这个客户端执行http请求
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 请求
        HttpGet httpGet = new HttpGet("https://www.jianshu.com/");
        RequestConfig config = RequestConfig.custom().setConnectTimeout(50000).build();
        httpGet.setConfig(config);
        // 响应
        CloseableHttpResponse response = null;
        try {
            // execute()执行成功会返回HttpResponse响应
            response = httpClient.execute(httpGet);
            // 响应体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态：" + response.getStatusLine());
            // gzip,deflate,compress
            System.out.println("响应体编码方式：" + responseEntity.getContentEncoding());
            // 响应类型如text/html charset也有可能在ContentType中
            System.out.println("响应体类型：" + responseEntity.getContentType());
            /**
             *  EntityUtils.toString()方法会将响应体的输入流关闭，相当于消耗了响应体，
             *  此时连接会回到httpclient中的连接管理器的连接池中，如果下次访问的路由
             *  是一样的（如第一次访问https://www.jianshu.com/,第二次访问
             *  https://www.jianshu.com/search?q=java&page=1&type=note)，
             *  则此连接可以被复用。
             */

            System.out.println("响应体内容：" + EntityUtils.toString(responseEntity));
            // 如果关闭了httpEntity的inputStream，httpEntity长度应该为0，而且再次请求相同路由的连接可以共用一个连接。
            // 可以通过设置连接管理器最大连接为1来验证。
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    // 关闭连接，则此次连接被丢弃
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
