package br.com.caelum.livraria.bean;

import br.com.caelum.livraria.modelo.Livro;

public class Venda {
	
	
	private Livro livro;
	private Integer quantidade;
	
	public Venda(Livro livro, Integer quantidade) {
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
