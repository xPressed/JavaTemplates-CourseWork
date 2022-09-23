package ru.xpressed.javatemplatescoursework.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.xpressed.javatemplatescoursework.entity.Order;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    Order order = new Order();

    @Test
    @DisplayName("OrderRepository test Save")
    public void findById_1() {
        order.setLoad("Here");
        order.setDestination("There");
        order.setContact("Number");

        orderRepository.save(order);

        assertEquals(order, orderRepository.getById(order.getId()));
    }

    @Test
    @DisplayName("OrderRepository test Update")
    public void findById_2() {
        order.setLoad("Here");
        order.setDestination("There Again");
        order.setContact("Number");

        orderRepository.save(order);

        assertEquals(order, orderRepository.getById(order.getId()));

        orderRepository.delete(order);
    }
}