package com.project.invoiceHelper.repositories;

import com.project.invoiceHelper.entities.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {

	Optional<InvoiceItem> findById(Long id);
//	Optional<InvoiceItem> findById(List<Long id);

	List<InvoiceItem> findAll();
}
