package com.project.invoiceHelper.repositories;

import com.project.invoiceHelper.Invoice;
import io.swagger.models.auth.In;
import jdk.internal.jline.internal.Nullable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository <Invoice, Long> , QueryByExampleExecutor<Invoice> {
    List<Invoice> findByInvoiceNo(Long InvoiceNo);
    List<Invoice> findAll();
    Page<Invoice> findAll(Pageable pageable);
    @Query(value = "select i from invoices i where (:supplierId is null or i.supplier_id = :supplierId) and ((:start is null or :end is null) or creation_date between :start and :end) " ,
            countQuery = "select count(i) from invoices i where (:supplierId is null or i.supplier_id = :supplierId) and ((:start is null and :end is null) or creation_date between :start and :end)",
            nativeQuery = true)
    Page<Invoice> findBySupplierIdAndCreationDateBetween(@Nullable @Param("supplierId") Long supplierId, @Param("start") LocalDate start, @Param("end") LocalDate end, Pageable pageable);

    Page<Invoice> findByCreationDateBetween(LocalDate start,LocalDate end, Pageable pageable);
}
