package com.project.invoiceHelper.repositories;

import com.project.invoiceHelper.Invoice;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository <Invoice, Long> {
    List<Invoice> findByInvoiceNo(Long InvoiceNo);
    List<Invoice> findAll();
    Page<Invoice> findAll(Pageable pageable);
    Page<Invoice> findBySupplierId(Long supplierId, Pageable pageable);
 //   @Query("select i from invoices where u.supplier_id = ?1 and i.creation_date between ?2 and ?3")
    Page<Invoice> findBySupplierIdAndCreationDateBetween(Long supplierId, LocalDate start, LocalDate end, Pageable pageable);
}
