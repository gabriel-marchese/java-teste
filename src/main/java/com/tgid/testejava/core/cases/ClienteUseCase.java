package com.tgid.testejava.core.cases;

import java.math.BigDecimal;

public interface ClienteUseCase {
    void realizarDeposito(String CNPJ, BigDecimal valor);
    void realizarSaque(String CNPJ, BigDecimal valor);
}
