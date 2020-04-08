package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.RollbackException;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;

@ManagedBean
@ViewScoped
public class AutorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Autor autor = new Autor();
	
	private Integer autorId;
	
	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	
	public Integer getAutorId() {
		return autorId;
	}
	
	public void carregaAutorPeloId() {
		this.autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);
	}

	public Autor getAutor() {
		return autor;
	}
	
	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}

	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());
		
		if(this.autor.getId() == null) {
			new DAO<Autor>(Autor.class).adiciona(this.autor);
		} else {
			new DAO<Autor>(Autor.class).atualiza(this.autor);
		}


		this.autor = new Autor();

		return "livro?faces-redirect=true";
	}
	
	public void carrega(Autor autor) {
		this.autor = autor;
	}
	
	public void remove(Autor autor) {
		try {
		new DAO<Autor>(Autor.class).remove(autor);
		}  catch (RollbackException e) {
			FacesContext.getCurrentInstance().addMessage("autor", 
					new FacesMessage("Autor não pode ser excluído pois está associado à um livro"));
		}
	}
	
	public String retornarPaginaCadastrarLivro() {
		return "livro?faces-redirect=true";
	}
}
