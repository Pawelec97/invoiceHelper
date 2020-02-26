package com.project.invoiceHelper;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;


@SpringBootTest(classes = InvoiceHelperApplication.class,
		webEnvironment = WebEnvironment.RANDOM_PORT)
public class IntegrationTests {
	@LocalServerPort
	private int port;

	


}
