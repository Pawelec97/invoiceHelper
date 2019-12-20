package com.project.invoiceHelper.repositories;

import com.project.invoiceHelper.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findById(long id);
    List<Order> findByInvoice(long id);
    List<Order> findAll();


}