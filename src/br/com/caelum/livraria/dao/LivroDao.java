package br.com.caelum.livraria.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.livraria.modelo.Livro;

public class LivroDao {

	@Inject
	EntityManager manager;
	
	private DAO<Livro> dao;
	
	void init() {
		this.dao = new DAO<Livro>(this.manager, Livro.class);
	}
	
	public List<Livro> listaTodos() {
		return this.dao.listaTodos();
	}

}
