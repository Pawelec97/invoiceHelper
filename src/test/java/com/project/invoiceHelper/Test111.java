package com.project.invoiceHelper;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.invoiceHelper.dto.InvoiceAddDto;
import com.project.invoiceHelper.dto.InvoiceItemDtoAddInvoice;
import com.project.invoiceHelper.entities.Invoice;
import com.project.invoiceHelper.entities.InvoiceItem;
import com.project.invoiceHelper.entities.Order;
import com.project.invoiceHelper.entities.Supplier;
import com.project.invoiceHelper.repositories.InvoiceItemRepository;
import com.project.invoiceHelper.repositories.InvoiceRepository;
import com.project.invoiceHelper.repositories.OrderRepository;
import com.project.invoiceHelper.repositories.SupplierRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(InvoiceController.class)
public class Test111 {

	@MockBean
	InvoiceRepository invoiceRepository;
	@MockBean
	SupplierRepository supplierRepository;
	@MockBean
	InvoiceItemRepository invoiceItemRepository;
	@MockBean
	OrderRepository orderRepository;
	@Autowired
	MockMvc mockMvc;


	@Test
	public void test_getInvoices() throws Exception {
		InvoiceItem item = new InvoiceItem(1, "myszka");
		Order order = new Order(1, new BigDecimal("20"), item);
		Invoice invoice = new Invoice(1L, LocalDate.now(), new Supplier(1, "pasda", "asdasd"),
				List.of(order));

		ArrayList<Invoice> lista = new ArrayList<>();

		lista.add(invoice);
		given(invoiceRepository.findAll()).willReturn(lista);
		given(invoiceItemRepository.findById(eq(order.getInvoiceItem().getId())))
				.willReturn(Optional.of(item)); // given w testach określa zachowanie zamockowanego repo
		mockMvc.perform(MockMvcRequestBuilders
				.get("/invoices")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].invoiceNo").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].invoiceNo").isNotEmpty());

	}

	@Test
	public void test_getInvoice() throws Exception {
		InvoiceItem item = new InvoiceItem(1, "myszka");
		Order order = new Order(1, new BigDecimal("20"), item);
		Invoice invoice = new Invoice(1L, LocalDate.now(), new Supplier(1, "pasda", "asdasd"),
				List.of(order));

		ArrayList<Invoice> lista = new ArrayList<>();
		given(invoiceRepository.findAll()).willReturn(lista);

		lista.add(invoice);

		mockMvc.perform(MockMvcRequestBuilders
				.get("/invoices/{invoiceNo}", 1).accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.invoiceNo").value(1));
	}

	@Test
	public void test_createInvoice() throws Exception {
		InvoiceItemDtoAddInvoice itemDto = new InvoiceItemDtoAddInvoice(1, 2, new BigDecimal("20"));
		InvoiceItem item = new InvoiceItem(1, "test");
		Supplier supplier = new Supplier(1, "pawel", "sadowa");
		InvoiceAddDto invoiceDto = new InvoiceAddDto(1, LocalDate.now(), 1, List.of(itemDto));

		given(invoiceItemRepository.findById(eq(itemDto.getIdItem()))).willReturn(Optional.of(item));
		given(supplierRepository.findById(eq(invoiceDto.getSupplier())))
				.willReturn(Optional.of(supplier));
		mockMvc.perform(MockMvcRequestBuilders.post("/invoices")
				.content("{\n" +
						"    \"invoiceNo\": 1,\n" +
						"    \"creationDate\": \"1997-12-12\",\n" +
						"    \"supplier\": 1,\n" +
						"    \"items\": [\n" +
						"        {\n" +
						"            \"id\": 1,\n" +
						"            \"quantity\": 69,\n" +
						"            \"price\": 69.00\n" +
						"        },\n" +
						"        {\n" +
						"            \"id\": 1,\n" +
						"            \"quantity\": 96,\n" +
						"            \"price\": 96.00\n" +
						"        }    ]\n" +
						"}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}