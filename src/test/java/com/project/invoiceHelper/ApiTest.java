package com.project.invoiceHelper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest(InvoiceController.class)
public class ApiTest {

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
	public void test_getInvoicesDetailsFull() throws Exception {
		InvoiceItem item = new InvoiceItem(1L, "myszka");
		Order order = new Order(4L, new BigDecimal("20"), item);
		Invoice invoice = new Invoice(1L, LocalDate.of(2010, 10, 12),
				new Supplier(2, "Maciej Nowak", "Zlota 35 Gdansk"),
				List.of(order));

		ArrayList<Invoice> lista = new ArrayList<>();

		lista.add(invoice);

		given(invoiceRepository.findAll(any(Pageable.class))).willReturn(new PageImpl<Invoice>(lista));
		given(invoiceItemRepository.findById(eq(order.getInvoiceItem().getId())))
				.willReturn(Optional.of(item));
		mockMvc.perform(MockMvcRequestBuilders
				.get("/invoicesDetails?page=0&size=1")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].invoiceNo", Is.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].creationDate", Is.is("2010-10-12")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].items[0].id", Is.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].items[0].model", Is.is("myszka")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].items[0].price", Is.is(20)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].items[0].quantity", Is.is(4)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].supplier.id", Is.is(2)))
				.andExpect(
						MockMvcResultMatchers.jsonPath("$[0].supplier.address", Is.is("Zlota 35 Gdansk")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].supplier.name", Is.is("Maciej Nowak"))
				);
	}




	@Test
	public void test_getInvoicesFull() throws Exception {
		InvoiceItem item = new InvoiceItem(1L, "myszka");
		Order order = new Order(4L, new BigDecimal("20"), item);
		Invoice invoice = new Invoice(1L, LocalDate.of(2010, 10, 12),
				new Supplier(2, "Maciej Nowak", "Zlota 35 Gdansk"),
				List.of(order));

		ArrayList<Invoice> lista = new ArrayList<>();

		lista.add(invoice);

		given(invoiceRepository.findAll(any(Pageable.class))).willReturn(new PageImpl<Invoice>(lista));

		mockMvc.perform(MockMvcRequestBuilders
				.get("/invoices?page=0&size=1")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].invoiceNo", Is.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].creationDate", Is.is("2010-10-12")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].supplierId", Is.is(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].orderCount", Is.is(1)));
	}

	@Test
	public void test_getInvoiceFull() throws Exception {
		InvoiceItem item = new InvoiceItem(1L, "myszka");
		Order order = new Order(4L, new BigDecimal("20"), item);
		Invoice invoice = new Invoice(1L, LocalDate.of(2010, 10, 12),
				new Supplier(2, "Maciej Nowak", "Zlota 35 Gdansk"),
				List.of(order));

		given(invoiceRepository.findById(any())).willReturn(Optional.of(invoice));

		mockMvc.perform(MockMvcRequestBuilders
				.get("/invoices/{invoiceNo}", 1).accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.invoiceNo", Is.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.creationDate", Is.is("2010-10-12")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].id", Is.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].model", Is.is("myszka")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].price", Is.is(20)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.items[0].quantity", Is.is(4)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.supplier.id", Is.is(2)))
				.andExpect(
						MockMvcResultMatchers.jsonPath("$.supplier.address", Is.is("Zlota 35 Gdansk")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.supplier.name", Is.is("Maciej Nowak"))
				);
	}

	@Test
	public void test_getInvoiceFull_badInvoiceNo() throws Exception {
		InvoiceItem item = new InvoiceItem(1L, "myszka");
		Order order = new Order(4L, new BigDecimal("20"), item);
		Invoice invoice = new Invoice(1L, LocalDate.of(2010, 10, 12),
				new Supplier(2, "Maciej Nowak", "Zlota 35 Gdansk"),
				List.of(order));

		given(invoiceRepository.findById(eq(2L))).willReturn(Optional.ofNullable(invoice));

		mockMvc.perform(MockMvcRequestBuilders
				.get("/invoices/{invoiceNo}", 1).accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andDo(print())
				.andExpect(status().isPreconditionFailed());
	}

	@Test
	public void test_createInvoice() throws Exception {

		given(invoiceRepository.findById(eq(1L))).willReturn(Optional.ofNullable(new Invoice()));
		given(supplierRepository.findById(eq(2L))).willReturn(Optional.of(new Supplier()));

		mockMvc.perform(MockMvcRequestBuilders.post("/invoices")
				.content("{\n"
						+ "  \"creationDate\": \"2014-10-28\",\n"
						+ "  \"invoiceNo\": 2,\n"
						+ "  \"supplier\": 2\n"
						+ "}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isCreated());
	}

	@Test
	public void test_createInvoice_duplicateInvoiceNo() throws Exception {

		given(invoiceRepository.findById(eq(2L))).willReturn(Optional.ofNullable(new Invoice()));
		given(supplierRepository.findById(eq(2L))).willReturn(Optional.of(new Supplier()));

		mockMvc.perform(MockMvcRequestBuilders.post("/invoices")
				.content("{\n"
						+ "  \"creationDate\": \"2014-10-28\",\n"
						+ "  \"invoiceNo\": 2,\n"
						+ "  \"supplier\": 2\n"
						+ "}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isPreconditionFailed());
	}

	@Test
	public void test_createInvoice_noSupplier() throws Exception {

		given(invoiceRepository.findById(eq(1L))).willReturn(Optional.ofNullable(new Invoice()));
		given(supplierRepository.findById(eq(1L))).willReturn(Optional.of(new Supplier()));

		mockMvc.perform(MockMvcRequestBuilders.post("/invoices")
				.content("{\n"
						+ "  \"creationDate\": \"2014-10-28\",\n"
						+ "  \"invoiceNo\": 2,\n"
						+ "  \"supplier\": 2\n"
						+ "}")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isPreconditionFailed());
	}
}
