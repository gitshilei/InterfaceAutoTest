package com.stone.auto.test.interfaces.poolCase.demo;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.Charset;

public class Case06 {


    @Test(/*invocationCount = 1000,threadPoolSize = 10*/)
    public void Test01() throws Exception {
        //1.创建httpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2.创建post请求方式实例
        HttpPost httpPost = new HttpPost("http://carinner.qunar.com/cbase/city/list");

        //2.1设置请求头 发送的是json数据格式
        httpPost.setHeader("Content-type", "application/json;charset=utf-8");
        httpPost.setHeader("Connection", "Close");

        //3.设置参数---设置消息实体 也就是携带的数据
        /*
        * 比如传递：
        * {
                "username": "aries",
                "password": "666666"
            }
         */
        String jsonStr = " {\"file\":\"aries\"}";
        StringEntity entity = new StringEntity(jsonStr.toString(), Charset.forName("UTF-8"));
        entity.setContentEncoding("UTF-8");  //设置编码格式
        // 发送Json格式的数据请求
        entity.setContentType("application/json");
        //把请求消息实体塞进去
        httpPost.setEntity(entity);

        //4.执行http的post请求
        CloseableHttpResponse httpResponse = null;
        InputStream inputStream = null;
        try {
            httpResponse = httpClient.execute(httpPost);
            //5.对返回的数据进行处理
            //5.1判断是否成功
            System.out.println(httpResponse.getStatusLine().getStatusCode());

            //5.2对数据进行处理
            HttpEntity httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent(); //获取content实体内容
            //封装成字符流来输出
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //6.关闭inputStream和httpResponse
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
