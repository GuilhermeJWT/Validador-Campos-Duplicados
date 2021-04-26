package br.com.systemsgs.exception;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.systemsgs.dto.AutoresDTO;
import br.com.systemsgs.model.Autores;
import br.com.systemsgs.repository.AutorRepository;

@Component
public class UniqueNomeException implements Validator{
	
	@Autowired
	private AutorRepository autorRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return AutoresDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		if (errors.hasErrors()) {
			return;
		}
		
		AutoresDTO dto = (AutoresDTO) target;
		Optional<Autores> autores = autorRepository.findByNome(dto.getNome());
		
		if (autores.isPresent()) {
			errors.rejectValue("nome", null, "Nome já cadastrado!, Cadastre outro!");
		}
		
	}

}
