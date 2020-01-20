package com.project.invoiceHelper.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceItemDtoAddInvoice {

	@ApiModelProperty(example = "1", value = "InvoiceItem id")
	private long idItem;
	@ApiModelProperty(example = "5")
	private long quantity;
	@ApiModelProperty(example = "20")
	private BigDecimal price;

}


