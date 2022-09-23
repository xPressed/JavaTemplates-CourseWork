package ru.xpressed.javatemplatescoursework.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.xpressed.javatemplatescoursework.entity.Customer;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    Customer customer = new Customer();

    @Test
    @DisplayName("CustomerRepository test Save")
    public void findByUsername_1() {
        customer.setUsername("test");

        customer.setPassword("123");
        customer.setRepeated("123");
        customerRepository.save(customer);

        assertEquals("test", customerRepository.findByUsername("test").getUsername());
    }

    @Test
    @DisplayName("CustomerRepository test Update")
    public void findByUsername_2() {
        customer.setUsername("test");

        customer.setPassword("1234");
        customer.setRepeated("1234");
        customerRepository.save(customer);

        assertEquals("1234", customerRepository.findByUsername("test").getPassword());

        customerRepository.delete(customer);
    }
}