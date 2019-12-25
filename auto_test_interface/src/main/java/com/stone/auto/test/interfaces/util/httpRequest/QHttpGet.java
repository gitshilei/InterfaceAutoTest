package com.stone.auto.test.interfaces.util.httpRequest;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.stone.auto.test.interfaces.util.imp.ihttpRequest;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class QHttpGet implements ihttpRequest {

    @Override
    public <Params> QResponesData httpRequest(String url, Params... params) throws Exception
    {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse response = null;
        Map<String,String> map = new HashMap<>();
        StringBuilder result = new StringBuilder();
        boolean len0 = false;
        QResponesData qResponesData = new QResponesData();
        Long beg;
        Long end;

        try {
            if (params.length == 0)
            {
                result.append(url);
            }
            else
            {
                StringBuilder rparams = new StringBuilder();

                if (params[0] instanceof JSONObject)
                {
                    if (((JSONObject) params[0]).size() <= 0) {
                        len0 = true;
                    }
                    map = JSONObject.parseObject(((JSONObject) params[0]).toJSONString(), new TypeReference<Map<String, String>>() {});
                }
                else if (params[0] instanceof Map)
                {
                    if (((Map) params[0]).size() <= 0)
                    {
                        len0 = true;
                    }
                    map = (Map<String, String>) params[0];
                }
                else
                {
                    throw new IOException();
                }

                if (!len0)
                {
                    for (Map.Entry<String,String> entry:map.entrySet()) {
                        rparams.append(entry);
                        rparams.append("&");
                    }
                    rparams.setLength(rparams.length() - 1);
                    result.append(url);
                    result.append("?");
                    result.append(rparams);
                }
                else
                {
                    result.append(url);
                }
            }

            HttpGet httpGet = new HttpGet(result.toString());

            // 配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    // 设置连接超时时间(单位毫秒)
                    .setConnectTimeout(5000)
                    // 设置请求超时时间(单位毫秒)
                    .setConnectionRequestTimeout(5000)
                    // socket读写超时时间(单位毫秒)
                    .setSocketTimeout(5000)
                    // 设置是否允许重定向(默认为true)
                    .setRedirectsEnabled(true).build();

            // 将上面的配置信息 运用到这个Get请求里
            httpGet.setConfig(requestConfig);

            // 由客户端执行(发送)Get请求
            beg = System.currentTimeMillis();
            response = httpClient.execute(httpGet);
            end = System.currentTimeMillis();
            qResponesData.setqTime(end - beg);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            qResponesData.setqStatus(response.getStatusLine().getStatusCode());
            //System.out.println(response.getAllHeaders().length);

            if (responseEntity != null) {
                qResponesData.setqSize(responseEntity.getContentLength());
                qResponesData.setqBody(EntityUtils.toString(responseEntity));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return qResponesData;
    }

}
