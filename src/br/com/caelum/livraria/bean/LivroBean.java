package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;

@ManagedBean
@ViewScoped
public class LivroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Livro livro = new Livro();

	private Integer autorId;
	
	private Integer livroId;

	private List<Livro> livros;
	
	private List<String> generos = Arrays.asList("Romance", 
			                                     "Drama",
			                                     "Ação", 
			                                     "Terror", 
			                                     "Ficção Científica", 
			                                     "Comédia",
			                                     "Animação",
			                                     "Musical");

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public Integer getAutorId() {
		return autorId;
	}
	
	public Integer getLivroId() {
		return livroId;
	}
	
	public void setLivroId(Integer livroId) {
		this.livroId = livroId;
	}

	public Livro getLivro() {
		return livro;
	}
	
	public List<String> getGeneros() {
		return generos;
	}

	public List<Livro> getLivros() {
		DAO<Livro> dao = new DAO<Livro>(Livro.class);
		if(this.livros == null) {
			this.livros = dao.listaTodos();
		}
		return livros;
	}

	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}

	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}

	public void gravarAutor() {
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
		this.livro.adicionaAutor(autor);
		System.out.println("Escrito por: " + autor.getNome());
	}

	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor",
					new FacesMessage("Livro deve ter pelo menos um Autor."));
			return;
		}
		
		DAO<Livro> dao = new DAO<Livro>(Livro.class);
		if(this.livro.getId() == null) {
			dao.adiciona(this.livro);
			this.livros = dao.listaTodos();
		} else {
			dao.atualiza(this.livro);			
		}

		this.livro = new Livro();
	}
	
	public void carregaLivroPeloId() {
		this.livro = new DAO<Livro>(Livro.class).buscaPorId(this.livroId);
	}
	
	public void remover(Livro livro) {
		System.out.println("removendo livro...");
		new DAO<Livro>(Livro.class).remove(livro);
	}
	
	public void carrega(Livro livro) {
		System.out.println("alterando dados...");
		this.livro = livro;
	}
	
	public void removeAutorDoLivro(Autor autor) {
		this.livro.removeAutor(autor);
	}

	public String formAutor() {
		System.out.println("Chamanda do formulario do Autor.");
		return "autor?faces-redirect=true";
	}

	public void comecaComDigitoUm(FacesContext fc, UIComponent component,
			Object value) throws ValidatorException {

		String valor = value.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage(
					"ISBN deveria começar com 1"));
		}
	}
}