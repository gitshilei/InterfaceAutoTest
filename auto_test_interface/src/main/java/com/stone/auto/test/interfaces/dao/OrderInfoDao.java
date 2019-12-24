package com.stone.auto.test.interfaces.dao;

import com.stone.auto.test.interfaces.entity.OrderInfo;

import java.util.List;

public interface OrderInfoDao {
    List<OrderInfo> selectOrderByDriverId(Integer driverId);
}
