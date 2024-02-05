package com.tgid.testejava.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tgid.testejava.domain.empresa.Empresa;
import com.tgid.testejava.dtos.EmpresaDto;
import com.tgid.testejava.repositories.EmpresaRepository;


@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository repository;

    public Empresa findEmpresaByCnpj(String cnpj) throws Exception {
       return this.repository.findEmpresaByCnpj(cnpj).orElseThrow(
            () -> new Exception("Empresa não encontrado"));
    }

    public void saveEmpresa(Empresa empresa) {
        this.repository.save(empresa);
    }

    public Empresa createEmpresa(EmpresaDto data) {
    Empresa newEmpresa = new Empresa(data);
    this.saveEmpresa(newEmpresa);
    return newEmpresa;
    }

    public List<Empresa> getAllEmpresas() {
      return this.repository.findAll();
    }
}

