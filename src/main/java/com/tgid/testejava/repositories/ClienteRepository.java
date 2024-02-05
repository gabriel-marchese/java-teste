package com.tgid.testejava.repositories;

import com.tgid.testejava.domain.cliente.Cliente;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
  Optional<Cliente> findUserByCpf(String cpf);
}
