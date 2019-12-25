package com.stone.auto.test.interfaces.poolCase.demo;

import com.alibaba.fastjson.JSONObject;
import com.stone.auto.test.interfaces.dao.OrderInfoDao;
import com.stone.auto.test.interfaces.entity.OrderInfo;
import com.stone.auto.test.interfaces.util.httpRequest.QHttpGet;
import com.stone.auto.test.interfaces.util.httpRequest.QHttpPost;
import com.stone.auto.test.interfaces.util.httpRequest.QResponesData;
import com.stone.auto.test.interfaces.util.imp.ihttpRequest;
import com.stone.auto.test.interfaces.util.loadData.loadJsonFile;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})*/
public class Case07 {

    //
    @BeforeGroups(groups = "A")
    public void TestBeforeGroup_A()
    {
        System.out.println("====================================================START_GROUP_A=======================================================");
    }

    @AfterGroups(groups = "A")
    public void TestAfterGroup_A()
    {
        System.out.println("====================================================FINISH_GROUP_A======================================================");
    }


    @DataProvider(name = "JsonTestData")
    public Object[] getTestData() throws Exception
    {
        loadJsonFile data = new loadJsonFile();
        List<Map<String,String>> list = new ArrayList<>();
        list = data.getFileListMap("\\demo\\login");
        return list.toArray();
    }

    @BeforeMethod()
    public void TestBeforeMethod()
    {
        System.out.println("---------------------------------------------------------------------------------------------");
    }
    @Test(dataProvider = "JsonTestData",groups = "A")
    public void TestCase_DataProvider(Map<String,String> map) throws Exception
    {
        String url = "http://carinnerbeta.qunar.com/qb/qborderinfo/driver/queryorderdetail";

        ihttpRequest qGet = new QHttpGet();
        QResponesData qResponesData = new QResponesData();
        qResponesData = qGet.httpRequest(url,map);
        Assert.assertEquals(String.valueOf(qResponesData.getqStatus()),"200");
        JSONObject jsonObject = JSONObject.parseObject(qResponesData.getqBody());
        String stat = JSONObject.parseObject(jsonObject.getString("bstatus")).getString("code");
        Assert.assertEquals(stat,"0");
        System.out.printf("状态码：    %s\n",qResponesData.getqStatus());
        System.out.printf("响应长度：  %s\n",qResponesData.getqSize());
        System.out.printf("请求时长：  %s\n",qResponesData.getqTime());
        System.out.printf("响应数据：  %s\n",qResponesData.getqBody());
    }
    @AfterMethod()
    public void TestAfterMethod()
    {
        System.out.println("---------------------------------------------------------------------------------------------");
    }

    @Test
    public void TestCase_Data() throws Exception
    {
        List<Map<String,String>> list = new ArrayList<>();
        loadJsonFile data = new loadJsonFile();
        list.addAll(data.getFileListMap("\\demo\\login"));
        String url = "http://carinnerbeta.qunar.com/qb/qborderinfo/driver/queryorderdetail";
        System.out.println("========================================================================START=========================================================================");
        for (Map<String,String> m : list) {
            ihttpRequest qGet = new QHttpGet();
            QResponesData qResponesData = new QResponesData();
            qResponesData = qGet.httpRequest(url,m);
            System.out.printf("状态码：    %s\n",qResponesData.getqStatus());
            System.out.printf("响应长度：  %s\n",qResponesData.getqSize());
            System.out.printf("请求时长：  %s\n",qResponesData.getqTime());
            System.out.printf("响应数据：  %s\n",qResponesData.getqBody());
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
        System.out.println("=========================================================================END==========================================================================");
    }


    //有参GET请求
    @Test
    public void TestCase_GetDemo_Params() throws Exception
    {
        //用主接口引用GET请求对象
        ihttpRequest qGet = new QHttpGet();

        //创建接受返回数据对象
        QResponesData qResponesData = new QResponesData();

        //创建请求参数 - JSONObject数据类型
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("field","drvId,cityCode,isZTC");
        jsonParams.put("idList","64,119");

        //创建请求参数 - Map数据类型
        //Map<String,String> map = new HashMap<>();
        //map.put("field","drvId,cityCode,isZTC");
        //map.put("idList","64,119");

        //创建请求URL - String数据类型
        String strUrl = new String();
        strUrl = "http://carinnerbeta.qunar.com/qb/driv/drivinfo/querylistbycondition";

        //请求接口用JSONObject + 接受返回数据
        qResponesData = qGet.httpRequest(strUrl,jsonParams);

        //请求接口Map + 接受返回数据
        //qResponesData = qGet.httpRequest(strUrl,map);

        //处理返回数据
        System.out.printf("状态码：    %s\n",qResponesData.getqStatus());
        System.out.printf("响应长度：  %s\n",qResponesData.getqSize());
        System.out.printf("请求时长：  %s\n",qResponesData.getqTime());
        System.out.printf("响应数据：  %s\n",qResponesData.getqBody());

    }

    //无参GET请求
    @Test
    public void TestCase_GetDemo_Null() throws Exception
    {
        //用主接口引用GET请求对象
        ihttpRequest qGet = new QHttpGet();

        //创建接受返回数据对象
        QResponesData qResponesData = new QResponesData();

        //创建请求URL - String数据类型
        String strUrl = new String();
        strUrl = "http://carinner.qunar.com/cbase/city/list";

        //请求接口 + 接受返回数据
        qResponesData = qGet.httpRequest(strUrl);

        //处理返回数据
        System.out.printf("状态码：    %s\n",qResponesData.getqStatus());
        System.out.printf("响应长度：  %s\n",qResponesData.getqSize());
        System.out.printf("请求时长：  %s\n",qResponesData.getqTime());
        System.out.printf("响应数据：  %s\n",qResponesData.getqBody());

    }

    //有参POST请求
    @Test
    public void TestCase_PostDemo_Params() throws Exception
    {
        //用主接口引用POST请求对象
        ihttpRequest qPost = new QHttpPost();

        //创建接受返回数据对象
        QResponesData qResponesData = new QResponesData();

        //创建请求参数 - JSONObject数据类型
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("orderId","S181767831948");
        jsonParams.put("driverId","64");

        //创建请求参数 - Map数据类型
        Map<String,String> map = new HashMap<>();
        map.put("orderId","S181767831948");
        map.put("driverId","64");

        //创建请求URL - String数据类型
        String strUrl = new String();
        strUrl = "http://carinnerbeta.qunar.com/qb/qborderinfo/driver/orderstatus";

        //请求接口用JSONObject + 接受返回数据
        //qResponesData = qPost.httpRequest(strUrl,jsonParams);

        //请求接口Map + 接受返回数据
        qResponesData = qPost.httpRequest(strUrl,map);

        //处理返回数据
        System.out.printf("状态码：    %s\n",qResponesData.getqStatus());
        System.out.printf("响应长度：  %s\n",qResponesData.getqSize());
        System.out.printf("请求时长：  %s\n",qResponesData.getqTime());
        System.out.printf("响应数据：  %s\n",qResponesData.getqBody());

    }

    //无参POST请求
    @Test
    public void TestCase_PostDemo_Null() throws Exception
    {
        //用主接口引用POST请求对象
        ihttpRequest qPost = new QHttpGet();

        //创建接受返回数据对象
        QResponesData qResponesData = new QResponesData();

        //创建请求URL - String数据类型
        String strUrl = new String();
        strUrl = "http://carinner.qunar.com/cbase/city/list";

        //请求接口 + 接受返回数据
        qResponesData = qPost.httpRequest(strUrl);

        //处理返回数据
        System.out.printf("状态码：    %s\n",qResponesData.getqStatus());
        System.out.printf("响应长度：  %s\n",qResponesData.getqSize());
        System.out.printf("请求时长：  %s\n",qResponesData.getqTime());
        System.out.printf("响应数据：  %s\n",qResponesData.getqBody());

    }

/*    @Autowired
    private OrderInfoDao OrderInfoDao;
    @Test
    public void TestCase_ReadDataBase()
    {

        List<OrderInfo> orderInfo  = OrderInfoDao.selectOrderByDriverId(611945);

        System.out.println(orderInfo);
    }*/

}
