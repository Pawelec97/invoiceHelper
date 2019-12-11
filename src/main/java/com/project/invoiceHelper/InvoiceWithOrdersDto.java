package com.project.invoiceHelper;

import java.time.LocalDate;
import java.util.List;

public class InvoiceWithOrdersDto {

    private long invoiceNo;
    private LocalDate creationDate;
    private SupplierDto supplier;
    private List<InvoiceItemDto> items;

    public InvoiceWithOrdersDto(long invoiceNo, LocalDate creationDate, SupplierDto supplier, List<InvoiceItemDto> orders) {
        this.invoiceNo = invoiceNo;
        this.creationDate = creationDate;
        this.supplier = supplier;
        this.items = orders;
    }

    public List<InvoiceItemDto> getItems() {
        return items;
    }

    public void setItems(List<InvoiceItemDto> items) {
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

    public SupplierDto getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierDto supplierDto) {
        this.supplier = supplierDto;
    }


}

