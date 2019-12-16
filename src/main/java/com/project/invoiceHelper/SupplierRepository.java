package com.project.invoiceHelper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long>{

    Optional<Supplier> findById(long id);
    List<Supplier> findAll();
}


