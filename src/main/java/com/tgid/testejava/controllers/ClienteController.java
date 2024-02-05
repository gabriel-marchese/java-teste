package com.tgid.testejava.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tgid.testejava.application.ClienteService;
import com.tgid.testejava.application.EmailSenderService;
import com.tgid.testejava.core.TransactionInformation;
import com.tgid.testejava.core.exceptions.EmailServiceException;


@RestController
@RequestMapping("/cliente")
public class ClienteController {
    
    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService, EmailSenderService emailSenderService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> doWithdraw(@RequestBody TransactionInformation transactionInformation) {
        try {
            clienteService.doWithdraw(transactionInformation);
            return ResponseEntity.ok("Withdraw successfully!");
        } catch (EmailServiceException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Withdraw failed.");
        }
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> doDeposit(@RequestBody TransactionInformation transactionInformation) {
        try {
            clienteService.doDeposit(transactionInformation);
            return ResponseEntity.ok("Deposit successfully!");
        } catch (EmailServiceException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Deposit failed.");
        }
    }
}

