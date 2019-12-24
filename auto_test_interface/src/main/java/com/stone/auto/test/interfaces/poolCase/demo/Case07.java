package com.stone.auto.test.interfaces.poolCase.demo;

import com.alibaba.fastjson.JSONObject;
import com.stone.auto.test.interfaces.dao.OrderInfoDao;
import com.stone.auto.test.interfaces.entity.OrderInfo;
import com.stone.auto.test.interfaces.util.httpRequest.QHttpGet;
import com.stone.auto.test.interfaces.util.httpRequest.QResponesData;
import com.stone.auto.test.interfaces.util.imp.ihttpRequest;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class Case07 {


    @Test
    public void TestCase02() throws Exception
    {
        ihttpRequest httpRequest = new QHttpGet();
        JSONObject jsonObject = new JSONObject();
        Map<String,String> map = new HashMap();
        map.put("field","drvId,cityCode,isZTC");
        map.put("idList","64,119");
        QResponesData response = httpRequest.httpRequest("http://carinnerbeta.qunar.com/qb/driv/drivinfo/querylistbycondition",map);
        System.out.println(response.getqStatus());
        System.out.println(response.getqSize());
        System.out.println(response.getqBody());

        //CloseableHttpResponse response = httpRequest.httpRequest("http://carinner.qunar.com/cbase/city/list",jsonObject);


    }

    @Autowired
    private OrderInfoDao OrderInfoDao;
    @Test
    public void TestCase01()
    {

        List<OrderInfo> orderInfo  = OrderInfoDao.selectOrderByDriverId(611945);

        System.out.println(orderInfo);
    }


}
