package com.project.invoiceHelper;

import java.time.LocalDate;
import java.util.List;

public class InvoiceAddDto {
    private long invoiceNo;
    private LocalDate creationDate;
    private long supplier;
    private List<InvoiceItemDtoAddInvoice> items;

    public InvoiceAddDto(long invoiceNo, LocalDate creationDate, long supplier, List<InvoiceItemDtoAddInvoice> items) {
        this.invoiceNo = invoiceNo;
        this.creationDate = creationDate;
        this.supplier = supplier;
        this.items = items;
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

    public long getSupplier() {
        return supplier;
    }

    public void setSupplier(Long supplier) {
        this.supplier = supplier;
    }

    public List<InvoiceItemDtoAddInvoice> getItems() {
        return items;
    }

    public void setItems(List<InvoiceItemDtoAddInvoice> items) {
        this.items = items;
    }
}
