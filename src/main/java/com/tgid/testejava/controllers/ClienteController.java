package com.tgid.testejava.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tgid.testejava.application.ClienteService;
import com.tgid.testejava.core.TransactionInformation;
import com.tgid.testejava.core.exceptions.EmailServiceException;
import com.tgid.testejava.domain.cliente.Cliente;
import com.tgid.testejava.dtos.ClienteDto;


@RestController
@RequestMapping("/cliente")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/register")
    public ResponseEntity<Cliente> newCliente(@RequestBody ClienteDto cliente) {
       Cliente newCliente = clienteService.createCliente(cliente);
       return ResponseEntity.status(HttpStatus.CREATED).body(newCliente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
       List<Cliente> clientes = this.clienteService.getAllClientes();
       return ResponseEntity.status(HttpStatus.CREATED).body(clientes);
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> doWithdraw(@RequestBody TransactionInformation transactionInformation) throws Exception {
        try {
            clienteService.newDeposit(transactionInformation);
            return ResponseEntity.ok("Deposit successfully!");
        } catch (EmailServiceException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Deposit failed.");
        }
    }
}

