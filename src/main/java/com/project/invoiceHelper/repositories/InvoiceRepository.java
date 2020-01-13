package com.project.invoiceHelper.repositories;

import com.project.invoiceHelper.entities.Invoice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>,
		QueryByExampleExecutor<Invoice>, JpaSpecificationExecutor<Invoice> {

	List<Invoice> findByInvoiceNo(Long InvoiceNo);

	List<Invoice> findAll();

	Page<Invoice> findAll(Pageable pageable);

	Page<Invoice> findByCreationDateBetween(LocalDate start, LocalDate end, Pageable pageable);
}
