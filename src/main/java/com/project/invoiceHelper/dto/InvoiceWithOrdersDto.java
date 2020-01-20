package com.project.invoiceHelper.dto;

import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceWithOrdersDto {

	@ApiModelProperty(example = "1", value = "unique invoice number")
	private long invoiceNo;
	@ApiModelProperty(example = "2011-10-05")
	private LocalDate creationDate;
	private SupplierDto supplier;
	private List<InvoiceItemDto> items;


}

