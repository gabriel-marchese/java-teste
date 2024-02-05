package com.tgid.testejava.repositories;

import com.tgid.testejava.domain.empresa.Empresa;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
  Optional<Empresa> findEmpresaByCnpj(String cnpj);
}
