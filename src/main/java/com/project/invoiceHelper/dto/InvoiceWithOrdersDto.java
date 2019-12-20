package com.project.invoiceHelper.dto;

import com.project.invoiceHelper.dto.InvoiceItemDto;
import com.project.invoiceHelper.dto.SupplierDto;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Reference;

import java.time.LocalDate;
import java.util.List;

public class InvoiceWithOrdersDto {

    @ApiModelProperty(example = "1" , value = "unique invoice number" )
    private long invoiceNo;
    @ApiModelProperty(example = "2011-10-05")
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

