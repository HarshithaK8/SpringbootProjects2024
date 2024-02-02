package com.example.orderservice.Service;

import com.example.orderservice.DAO.OrderDao;
import com.example.orderservice.Model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    public Order saveOrder(Order order);
}
