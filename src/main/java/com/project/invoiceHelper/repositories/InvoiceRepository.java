package com.project.invoiceHelper.repositories;

import com.project.invoiceHelper.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByInvoiceNo(Long InvoiceNo);
    List<Invoice> findAll();
}
