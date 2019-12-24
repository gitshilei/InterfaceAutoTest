package com.stone.auto.test.interfaces.poolCase.demo;

import org.testng.annotations.Test;

public class Case03 {
    @Test
    public void Test01()
    {
        System.out.println(Thread.currentThread().getId());
    }
    @Test
    public void Test02()
    {
        System.out.println(Thread.currentThread().getId());
    }
    @Test
    public void Test03()
    {
        System.out.println(Thread.currentThread().getId());
    }
    @Test
    public void Test04()
    {
        System.out.println(Thread.currentThread().getId());
    }
}
