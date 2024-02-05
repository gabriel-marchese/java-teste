package com.tgid.testejava.adapters;

public interface ClienteGateway {
    void doDeposit(String CNPJ, Integer valor);
    void doWithdraw(String CNPJ, Integer valor);
}