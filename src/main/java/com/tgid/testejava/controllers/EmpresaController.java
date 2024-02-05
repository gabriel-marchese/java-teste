package com.tgid.testejava.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgid.testejava.application.EmpresaService;
import com.tgid.testejava.core.EmpresaCreate;
import com.tgid.testejava.core.exceptions.EmailServiceException;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
    
    private final EmpresaService empresaService;

    @Autowired
    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

}
