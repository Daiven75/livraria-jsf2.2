package br.com.caelum.livraria.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.caelum.livraria.modelo.Livro;

@Entity
public class Vendas {
	
	@Id @GeneratedValue
	private Integer id;
	
	@ManyToOne
	private Livro livro;
	private Integer quantidade;
	
	public Vendas() {
	}
	
	public Vendas(Livro livro, Integer quantidade) {
		this.livro= livro;
		this.quantidade = quantidade;
	}
	
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	public Livro getLivro() {
		return livro;
	}
	
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
}
