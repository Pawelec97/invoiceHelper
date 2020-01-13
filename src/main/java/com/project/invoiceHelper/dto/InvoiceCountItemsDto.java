package com.project.invoiceHelper.dto;

import java.time.LocalDate;

public class InvoiceCountItemsDto {
	private Long invoiceNo;
	private LocalDate creationDate;
	private Long supplierId;
	private int orderCount;

	public InvoiceCountItemsDto() {
	}

	public InvoiceCountItemsDto(Long invoiceNo, LocalDate creationDate,
			Long supplierId, int orderCount) {
		this.invoiceNo = invoiceNo;
		this.creationDate = creationDate;
		this.supplierId = supplierId;
		this.orderCount = orderCount;
	}

	public Long getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(Long invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}
}
