package com.tgid.testejava.infra.Cliente;

import org.springframework.stereotype.Component;

import com.tgid.testejava.adapters.ClienteGateway;


@Component
public class ClienteGatewayImpl implements ClienteGateway {
    
    // Aqui você implementa as operações relacionadas a clientes
    @Override
    public void doDeposit(String CNPJ, Integer valor) {
        // Implementação da lógica para depósito
        System.out.println("Realizando depósito para o CNPJ: " + CNPJ + ", Valor: " + valor);
    }

    @Override
    public void doWithdraw(String CNPJ, Integer valor) {
        // Implementação da lógica para saque
        System.out.println("Realizando saque do CNPJ: " + CNPJ + ", Valor: " + valor);
    } 
}
