package br.com.systemsgs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.systemsgs.model.Autores;

public interface AutorRepository extends JpaRepository<Autores, Long>{
	
	Optional<Autores> findByNome(String nome);

}
