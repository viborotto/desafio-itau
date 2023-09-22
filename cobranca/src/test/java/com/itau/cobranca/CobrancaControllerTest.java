package com.itau.cobranca;

import com.itau.cobranca.controller.CobrancaController;
import com.itau.cobranca.exception.ClienteNotFoundException;
import com.itau.cobranca.model.Cobranca;
import com.itau.cobranca.service.CobrancaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class CobrancaControllerTest {

    @Mock
    private CobrancaService cobrancaService;

    @InjectMocks
    private CobrancaController cobrancaController;

    @Test
    public void testDeleteCobrancaByClienteId() {
        ResponseEntity<String> response = cobrancaController.deleteCobrancaByClienteId(1L);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cobranca deleted successfully", response.getBody());

        Mockito.verify(cobrancaService).deleteCobrancaByClienteId(1L);
    }
}

