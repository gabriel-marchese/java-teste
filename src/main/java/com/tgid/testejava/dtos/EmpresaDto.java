package com.tgid.testejava.dtos;

import java.math.BigDecimal;

public record EmpresaDto(String cnpj, BigDecimal saldo, BigDecimal taxa ) {}
