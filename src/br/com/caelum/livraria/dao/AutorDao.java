package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.livraria.modelo.Autor;

public class AutorDao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager manager;
	
	private DAO<Autor> dao;
	
	@PostConstruct
	void init() {
		this.dao = new DAO<Autor>(this.manager, Autor.class);
	}

	public Autor buscaPorId(Integer autorId) {
		return this.dao.buscaPorId(autorId);
	}

	public List<Autor> listaTodos() {
		return this.dao.listaTodos();
	}

	public void adiciona(Autor autor) {
		this.dao.adiciona(autor);
	}

	public void atualiza(Autor autor) {
		this.dao.atualiza(autor);
	}

	public void remove(Autor autor) {
		this.dao.remove(autor);
	}
}