package com.project.invoiceHelper.dto;

import java.time.LocalDate;

public class InvoiceDtoSupplierId {

	private long invoiceNo;
	private LocalDate creationDate;
	private long supplierId;

	public InvoiceDtoSupplierId(long invoiceNo, LocalDate creationDate, long supplierId) {
		this.invoiceNo = invoiceNo;
		this.creationDate = creationDate;
		this.supplierId = supplierId;
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

	public long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(long supplierId) {
		this.supplierId = supplierId;
	}
}
