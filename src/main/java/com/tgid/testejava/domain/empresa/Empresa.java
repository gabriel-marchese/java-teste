package com.tgid.testejava.domain.empresa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

import com.tgid.testejava.dtos.EmpresaDto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="empresas")
@Table(name="empresas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Empresa {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(unique = true)
  private String cnpj;

  private BigDecimal saldo;
  private BigDecimal taxa;

  public Empresa(EmpresaDto data) {
    this.cnpj = data.cnpj();
    this.saldo = data.saldo();
    this.taxa = data.taxa();
  }
}
