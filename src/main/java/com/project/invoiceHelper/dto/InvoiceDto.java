package com.project.invoiceHelper.dto;

import java.time.LocalDate;

public class InvoiceDto {

	private long invoiceNo;
	private LocalDate creationDate;
	private SupplierDto supplier;

	public InvoiceDto(long invoiceNo, LocalDate creationDate, SupplierDto supplier) {
		this.invoiceNo = invoiceNo;
		this.creationDate = creationDate;
		this.supplier = supplier;
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
