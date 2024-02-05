package com.tgid.testejava.core;

import java.math.BigDecimal;

public record TransactionInformation(BigDecimal value, String type ,String cpf, String cnpj) {}