package br.com.bitway.bibliotech.repository;

import br.com.bitway.bibliotech.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

    Endereco findByCep(String cep);

}
