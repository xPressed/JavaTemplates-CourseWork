package ru.xpressed.javatemplatescoursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.xpressed.javatemplatescoursework.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
