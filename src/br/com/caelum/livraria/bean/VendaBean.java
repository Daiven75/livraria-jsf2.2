package br.com.caelum.livraria.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Livro;

@ManagedBean
@ViewScoped
public class VendaBean {
	
	
	public List<Venda> getVenda() {

		List<Livro> livros = new DAO<Livro>(Livro.class).listaTodos();
		List<Venda> vendas = new ArrayList<Venda>();
		
		for(Livro livro : livros) {
			
			Random random = new Random(1234);
			Integer quantidade = random.nextInt(500);
			vendas.add(new Venda(livro, quantidade));
		}
		
		
		return vendas;
	}
}
