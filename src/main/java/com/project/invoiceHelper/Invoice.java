package com.project.invoiceHelper;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @Column(name = "invoiceNo")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long invoiceNo;

    @Column(name = "creationDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-DD")
    private LocalDate creationDate;

    @ManyToOne
    @JoinColumn(name="supplierId")
    private Supplier supplier;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    @Column(name = "order")
    private List<Order> orders;



    public Invoice(){
    }
    public Invoice(long invoiceNo, LocalDate creationDate, Supplier supplier){
        this.invoiceNo = invoiceNo;
        this.creationDate = creationDate;
        this.supplier = supplier;
    }

    public Invoice(long invoiceNo, LocalDate creationDate, Supplier supplier, List<Order> orders) {
        this.invoiceNo = invoiceNo;
        this.creationDate = creationDate;
        this.supplier = supplier;
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public long getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(long invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

}
