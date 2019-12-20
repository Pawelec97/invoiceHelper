package com.project.invoiceHelper.repositories;

import com.project.invoiceHelper.Invoice;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository <Invoice, Long> {
    List<Invoice> findByInvoiceNo(Long InvoiceNo);
    List<Invoice> findAll();
    Page<Invoice> findAll(Pageable pageable);
}
