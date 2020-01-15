package com.project.invoiceHelper.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;


public class InvoiceItemDtoAddInvoice {

	@ApiModelProperty(example = "1", value = "InvoiceItem id")
	private long idItem;
	@ApiModelProperty(example = "5")
	private long quantity;
	@ApiModelProperty(example = "20")
	private BigDecimal price;


	public InvoiceItemDtoAddInvoice() {
	}

	public InvoiceItemDtoAddInvoice(long idItem, long quantity, BigDecimal price) {
		this.idItem = idItem;
		this.quantity = quantity;
		this.price = price;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public long getIdItem() {
		return idItem;
	}

	public void setIdItem(long idItem) {
		this.idItem = idItem;
	}


}


