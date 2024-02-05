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

import com.tgid.testejava.application.EmpresaService;
import com.tgid.testejava.domain.empresa.Empresa;
import com.tgid.testejava.dtos.EmpresaDto;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
    
    private final EmpresaService empresaService;

    @PostMapping("/register")
    public ResponseEntity<Empresa> newCliente(@RequestBody EmpresaDto empresa) {
        Empresa newEmpresa = empresaService.createEmpresa(empresa);
       return ResponseEntity.status(HttpStatus.CREATED).body(newEmpresa);
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> getAllEmpresas() {
       List<Empresa> empresas = this.empresaService.getAllEmpresas();
       return ResponseEntity.status(HttpStatus.CREATED).body(empresas);
    }

    @Autowired
    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

}
