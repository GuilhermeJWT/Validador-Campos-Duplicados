package br.com.systemsgs.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.systemsgs.dto.AutoresDTO;
import br.com.systemsgs.exception.UniqueNomeException;
import br.com.systemsgs.model.Autores;
import br.com.systemsgs.repository.AutorRepository;

@RestController
@RequestMapping(value = "/api/autores")
public class AutorController {

	@Autowired
	private AutorRepository autorRepository;

	private UniqueNomeException uniqueNomeException;

	/*
	 * Vai Validar antes de persistir no Banco, Verificar se o campo ja possui
	 * registro
	 */
	@InitBinder
	public void before(WebDataBinder before) {
		before.addValidators(uniqueNomeException);
	}

	@PostMapping(value = "/salvar")
	public ResponseEntity<AutoresDTO> salvarAutor(@RequestBody @Valid AutoresDTO autoresDTO) {

		Autores autores = autoresDTO.converteObjetoEntidade();
		autorRepository.save(autores);

		return new ResponseEntity<AutoresDTO>(HttpStatus.OK);

	}

}
