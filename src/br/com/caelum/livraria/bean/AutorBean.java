package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.RollbackException;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.tx.Transacional;

@Named
@ViewScoped
public class AutorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Autor autor = new Autor();
	
	private Integer autorId;

	@Inject
	private AutorDao dao;
	
	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	
	public Integer getAutorId() {
		return autorId;
	}
	
	public void carregaAutorPeloId() {
		this.autor = this.dao.buscaPorId(autorId);
	}

	public Autor getAutor() {
		return autor;
	}
	
	public List<Autor> getAutores() {
		return this.dao.listaTodos();
	}

	@Transacional
	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());
		
		if(this.autor.getId() == null) {
			this.dao.adiciona(this.autor);
		} else {
			this.dao.atualiza(this.autor);
		}


		this.autor = new Autor();

		return "livro?faces-redirect=true";
	}
	
	public void carrega(Autor autor) {
		this.autor = autor;
	}
	
	@Transacional
	public void remove(Autor autor) {
		try {
			this.dao.remove(autor);
		}  catch (RollbackException e) {
			FacesContext.getCurrentInstance().addMessage("autor", 
					new FacesMessage("Autor não pode ser excluído pois está associado à um livro"));
		}
	}
	
	public String retornarPaginaCadastrarLivro() {
		return "livro?faces-redirect=true";
	}
}
