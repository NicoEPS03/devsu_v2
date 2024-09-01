package com.devsu.prueba;

import com.devsu.prueba.entity.Cliente;
import com.devsu.prueba.service.imp.IClienteServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.Assert.*;

@AutoConfigureMockMvc
@SpringBootTest
class PruebaApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IClienteServiceImp service;

	ObjectMapper objectMapper = new ObjectMapper();

	@Test
	void crearCliente() throws Exception {
		Cliente cliente = new Cliente();
		cliente.setNombre("Nicolas Peralta");
		cliente.setGenero('M');
		cliente.setEdad(23);
		cliente.setIdentificacion("10010901231");
		cliente.setDireccion("Cll 1 #23b12");
		cliente.setTelefono("8971623412");
		cliente.setPassword("12312");
		doNothing().when(service).guardar(any());
		mockMvc.perform(post("/clientes/insertar")
						.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(cliente)))
				.andExpect(status().isCreated());
	}

	@Test
	void probarCliente() {
		Cliente cliente = new Cliente();
		cliente.setNombre("Nicolas Peralta");

		assertEquals("Nicolas Peralta", cliente.getNombre());
	}

}
