package com.itau.cobranca;

import com.itau.cobranca.controller.ClienteController;
import com.itau.cobranca.exception.ClienteNotFoundException;
import com.itau.cobranca.exception.CobrancaNotFoundException;
import com.itau.cobranca.model.Cliente;
import com.itau.cobranca.model.Cobranca;
import com.itau.cobranca.service.ClienteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    @Test
    public void testListarClientes() {
        List<Cliente> expectedClientes = Arrays.asList(new Cliente(), new Cliente());
        Mockito.when(clienteService.listarClientes()).thenReturn(expectedClientes);

        List<Cliente> clientesAtuais = clienteController.listarCobrancas();

        assertEquals(expectedClientes, clientesAtuais);
    }

    @Test
    public void testObterCliente() {
        Long clientId = 1L;
        Cliente expectedCliente = new Cliente();
        Mockito.when(clienteService.obterCliente(clientId)).thenReturn(expectedCliente);

        Cliente actualCliente = clienteController.obterCobranca(clientId);
        assertEquals(expectedCliente, actualCliente);
    }

    @Test
    public void testObterCobrancaClienteCPF() throws ClienteNotFoundException, CobrancaNotFoundException {
        Cobranca cobranca = new Cobranca();
        cobranca.setId(1L);
        cobranca.setValor(100.0);

        Mockito.when(clienteService.obterCobrancaClienteCpf(Mockito.anyString(), Mockito.anyLong())).thenReturn(cobranca);

        Cobranca obtainedCobranca = clienteController.obterCobrancaClienteCPF("47409378578", 1L);

        assertNotNull(obtainedCobranca);
        assertEquals(1L, obtainedCobranca.getId().longValue());
        assertEquals(100.0, obtainedCobranca.getValor(), 0.001);

        Mockito.verify(clienteService).obterCobrancaClienteCpf("47409378578", 1L);
    }


    @Test
    public void testCriarCliente() throws ClienteNotFoundException {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("John Doe");
        cliente.setEmail("johndoe@example.com");
        Mockito.when(clienteService.criarCliente(Mockito.any(Cliente.class))).thenReturn(cliente);

        Cliente requestBody = new Cliente();
        requestBody.setNome("John Doe");
        requestBody.setEmail("johndoe@example.com");
        Cliente createdCliente = clienteController.criarCliente(requestBody);
        assertNotNull(createdCliente);
        assertEquals(1L, createdCliente.getId().longValue());
        assertEquals("John Doe", createdCliente.getNome());
        assertEquals("johndoe@example.com", createdCliente.getEmail());

        Mockito.verify(clienteService).criarCliente(Mockito.any(Cliente.class));
    }

}

