package com.project.invoiceHelper.repositories;

import com.project.invoiceHelper.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findById(long id);

	List<Order> findByInvoice(long id);

	List<Order> findAll();


}
