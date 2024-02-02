package com.example.orderservice.Service;

import com.example.orderservice.DAO.OrderDao;
import com.example.orderservice.Model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderDao orderDao;
    @Override
    public Order saveOrder(Order order) {
        return orderDao.save(order);
    }
}
