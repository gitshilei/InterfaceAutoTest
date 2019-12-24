package com.stone.auto.test.interfaces.poolCase.demo;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Case02 {
    @Test(dataProvider = "data1",dataProviderClass = Case01.class)
    public void Test01(String name,Integer old,boolean sex)
    {
        System.out.println("name:--"+name+"  old:--"+old+"  sex:--"+sex);

    }

    @Test
    @Parameters({"name","age"})
    public void Test02(String name,Integer age)
    {
        System.out.println("name:--"+name+"  age:--"+age);
    }
}
