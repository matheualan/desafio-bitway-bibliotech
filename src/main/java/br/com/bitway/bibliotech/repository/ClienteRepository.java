package br.com.bitway.bibliotech.repository;

import br.com.bitway.bibliotech.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Optional<Cliente> findByCpf(String cpf);
//    Page<Cliente> findAllPage(Pageable pageable);

}
