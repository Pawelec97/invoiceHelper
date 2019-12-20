package com.project.invoiceHelper.repositories;

import com.project.invoiceHelper.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {
  //  List<InvoiceItem> findById(long id);
    Optional<InvoiceItem>  findById(long id);
    List<InvoiceItem> findAll();
}