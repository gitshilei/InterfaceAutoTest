package com.stone.auto.test.interfaces.util.httpRequest;


import com.alibaba.fastjson.JSONObject;
import com.stone.auto.test.interfaces.util.imp.ihttpRequest;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QHttpPost implements ihttpRequest {

    @Override
    public <Params> QResponesData httpRequest(String url, Params... params) throws Exception
    {
        QResponesData qResponesData = new QResponesData();
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        CloseableHttpResponse httpResponse=null;
        InputStream inputStream=null;
        StringBuilder qBodyStr = new StringBuilder();
        UrlEncodedFormEntity formEntity;


        //1.创建httpClient
        CloseableHttpClient httpClient= HttpClients.createDefault();
        //2.创建post请求方式实例
        HttpPost httpPost=new HttpPost(url);

        if (params.length > 0)
        {
            if ((params[0] instanceof JSONObject))
            {
                if (((JSONObject) params[0]).size() > 0) {
                    Map<String, Object> map = (Map) params[0];
                    for (Map.Entry entry : map.entrySet()) {
                        pairs.add(new BasicNameValuePair((String) entry.getKey(), entry.getValue().toString()));
                    }
                }

            }
            else if (params[0] instanceof Map)
            {
                if (((Map) params[0]).size() > 0)
                {
                    Map<String, Object> map = (Map) params[0];
                    for (Map.Entry entry : map.entrySet()) {
                        pairs.add(new BasicNameValuePair((String) entry.getKey(), entry.getValue().toString()));
                    }
                }
            }
            else
            {
                throw new IOException();
            }

            formEntity = new UrlEncodedFormEntity(pairs, "utf-8");
            //把请求消息实体塞进去
            httpPost.setEntity(formEntity);
        }
        try {
            Long beg = System.currentTimeMillis();
            httpResponse = httpClient.execute(httpPost);
            Long end =System.currentTimeMillis();
            qResponesData.setqTime(end - beg);
            //5.对返回的数据进行处理
            //5.1判断是否成功
            qResponesData.setqStatus(httpResponse.getStatusLine().getStatusCode());
            //5.2对数据进行处理
            HttpEntity httpEntity=httpResponse.getEntity();
            inputStream=httpEntity.getContent(); //获取content实体内容
            qResponesData.setqSize(httpEntity.getContentLength());
            //封装成字符流来输出
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String line="";
            while ((line=bufferedReader.readLine())!=null){
                qBodyStr.append(line);
            }
            qResponesData.setqBody(qBodyStr.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                //6.关闭inputStream和httpResponse
                if (inputStream!=null){
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (httpResponse!=null){
                    try {
                        httpResponse.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        return qResponesData;
    }
}
