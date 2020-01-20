package com.project.invoiceHelper.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceItemDto {

	private long id;
	private String model;
	private long quantity;
	private BigDecimal price;

}
