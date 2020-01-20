package com.project.invoiceHelper.dto;

import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceAddDto {

	@ApiModelProperty(example = "1", value = "unique invoice model")
	private long invoiceNo;
	@ApiModelProperty(example = "2014-10-28")
	private LocalDate creationDate;
	@ApiModelProperty(example = "2", value = "supplier id")
	private long supplier;



}
