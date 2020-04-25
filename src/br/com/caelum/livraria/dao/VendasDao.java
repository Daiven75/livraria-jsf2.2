package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.livraria.modelo.Vendas;

public class VendasDao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	EntityManager manager;
	
	private DAO<Vendas> dao;
	
	@PostConstruct
	void init() {
		this.dao = new DAO<Vendas>(this.manager, Vendas.class);
	}
	
	public List<Vendas> listaTodos() {
		return this.dao.listaTodos();
	}
}
