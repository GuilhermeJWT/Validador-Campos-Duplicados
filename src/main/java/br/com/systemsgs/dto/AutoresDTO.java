package br.com.systemsgs.dto;

import javax.validation.constraints.NotEmpty;

import br.com.systemsgs.model.Autores;

public class AutoresDTO {
	

	@NotEmpty(message = "O Nome deve ser informado!")
	private String nome;
	
	public Autores converteObjetoEntidade() {
		return new Autores(this.nome);
	}
	
	public String getNome() {
		return nome;
	}

}
