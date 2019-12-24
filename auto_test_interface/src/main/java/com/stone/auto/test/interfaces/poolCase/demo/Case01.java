package com.stone.auto.test.interfaces.poolCase.demo;

import com.alibaba.fastjson.JSONArray;
import com.stone.auto.test.interfaces.util.loadData.loadExcelFile;
import com.stone.auto.test.interfaces.util.loadData.loadJsonFile;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Case01 {


    @Test
    public void Case16() throws Exception
    {
        String aaa="\\demo\\login";
        loadJsonFile loadjsonfile = new loadJsonFile();
        List<Map<String,String>> lisArray = new ArrayList<>();
        lisArray = loadjsonfile.getFileListMap(aaa);
        System.out.println(lisArray);
    }
    @Test
    public void Case15() throws Exception
    {
        String aaa="\\demo\\login";
        loadJsonFile loadjsonfile = new loadJsonFile();
        JSONArray jsonArray = new JSONArray();
        jsonArray = loadjsonfile.getFileJsonArray(aaa);
        System.out.println(jsonArray);
    }

    @Test
    public void Case14() throws Exception
    {
        String aaa="\\demo\\login.xlsx";
        loadExcelFile bbb = new loadExcelFile();
        JSONArray ccc = new JSONArray();
        ccc= loadExcelFile.getExcuteJsonArray(aaa,"lei");
        System.out.println(ccc);
    }
    @DataProvider(name = "excelData")
    public Object[] exceldata() throws Exception
    {
        String filepath="\\demo\\login.xlsx";
        loadExcelFile lefc = new loadExcelFile();
        List<Map<String, String>> xlsxData = new ArrayList<Map<String, String>>();
        xlsxData= loadExcelFile.getExcuteListMap(filepath,"lei");
        return  xlsxData.toArray();
    }
    @Test(dataProvider = "excelData")
    public void Case13(Map<String,String> map) throws Exception
    {

        System.out.println(map);
        //System.out.println(map);
/*        Integer a = Integer.valueOf("455");
        String b = String.valueOf(123.5);
        Double c = Double.valueOf("123");
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);*/
/*        String columns[] = {"username", "password", "result"};
        String filepath="\\demo\\login.xlsx";
        loadExcelFromComputer lefc = new loadExcelFromComputer();
        List<Map<String, String>> xlsxData= loadExcelFromComputer.getExcuteList(filepath,"lei");
        System.out.println(xlsxData);*/
    }
/*    @Test(dataProvider = "data01",dataProviderClass =  com.stone.auto.test.interfaces.poolData.demo.Data01.class)
    public void Case12(String name,Integer age)
    {
        System.out.println(name+"-------"+age);
    }*/
    @BeforeSuite
    public void BeforeSuite()
    {
        System.out.println("BeforeSuite------------");
    }
    @AfterSuite
    public void AfterSuite()
    {
        System.out.println("AfterSuite------------");
    }

    @BeforeTest
    public void BeforeTest()
    {
        System.out.println("BeforeTest---------");
    }
    @AfterTest
    public void AfterTest()
    {
        System.out.println("AfterTest---------");
    }

    @BeforeClass
    public void BeforeClass()
    {
        System.out.println("BeforeClass----");
    }
    @AfterClass
    public void AfterClass()
    {
        System.out.println("AfterClass----");
    }

    @BeforeGroups(groups = "A_group")
    public void BeforeGroups()
    {
        System.out.println("BeforeGroups------A");
    }
    @AfterGroups(groups = "A_group")
    public void AfterGroups()
    {
        System.out.println("AfterGroups------A");
    }

    @BeforeGroups(groups = "B_group")
    public void BeforeGroupsb()
    {
        System.out.println("BeforeGroupsb------B");
    }
    @AfterGroups(groups = "B_group")
    public void AfterGroupsb()
    {
        System.out.println("AfterGroupsb------B");
    }

    @BeforeMethod
    public void BeforeMethod()
    {
        System.out.println("BeforeMethod---");
    }
    @AfterMethod
    public void AfterMethod()
    {
        System.out.println("AfterMethod---");
    }

    @Test(groups = "A_group")
    public void Test01()
    {
        System.out.println("test01");
    }

    @Test
    public void Test02()
    {
        System.out.println("Test02");
    }

    @Test(enabled = false)
    public void Test03()
    {
        System.out.println("Test03");
    }

    @Test(dataProvider = "data1",groups = "B_group")
    public void Test04(String name,Integer old,boolean sex)
    {
        System.out.println("name:--"+name+"  old:--"+old+"  sex:--"+sex);
    }
    //方法传参
    @DataProvider(name = "data1")
    public Object[][] DataProvider()
    {
        return new Object[][]{
                {"test1",1,false},{"test2",2,true},{"test3",3,true}
        };
    }
    @DataProvider(name = "MethodDataProvider")
    public Object[][] Test05(Method method)
    {
        Object[][] object = null;
        if (method.getName().equals("Test05"))
        {
            object = new Object[][]{{"aaa",11,true}};
        }
        else
        {
            object = new Object[][]{{"bbb",22,false}};
        }
        return object;
    }
    @Test(dataProvider = "MethodDataProvider")
    public void Test05(String name,Integer old,boolean sex)
    {
        System.out.println("name:--"+name+"  old:--"+old+"  sex:--"+sex);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void Test06()
    {
        System.out.println("2222");
        throw new RuntimeException();
    }
    @Test(dependsOnMethods = {"Test06"})
    public void Test07()
    {
        System.out.println("33333");
    }

    @Test(timeOut = 1)
    public void  Test08() throws InterruptedException
    {
        Thread.sleep(20);
        System.out.println("444444");
    }

    @Test(invocationCount = 4,threadPoolSize = 2)
    public void Test09()
    {
        for (int i = 0;i<5;i++){
            System.out.println(i);
        }
        System.out.println(Thread.currentThread().getId());
    }

    @Test
    public void Test10()
    {
        System.out.println("---"+Thread.currentThread().getId());
        System.out.println("---"+Thread.currentThread().getId());
        System.out.println("---"+Thread.currentThread().getId());
        System.out.println("---"+Thread.currentThread().getId());
        System.out.println("---"+Thread.currentThread().getId());
        System.out.println("---"+Thread.currentThread().getId());
        System.out.println("---"+Thread.currentThread().getId());

    }
}

