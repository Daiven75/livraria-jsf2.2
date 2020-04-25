package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.caelum.livraria.dao.LivroDao;
import br.com.caelum.livraria.modelo.Livro;

@Named
@ViewScoped
public class VendasBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private LivroDao livroDao;

	public BarChartModel getVendasModel() {
		
		 BarChartModel model = new BarChartModel();
		 model.setAnimate(true);
	     ChartSeries vendaSerie2020 = new ChartSeries();
	     vendaSerie2020.setLabel("Vendas 2020");
	     
	     List<Venda> vendas = getVendas(1234); 
	     
	     for(Venda venda : vendas) {
	       	vendaSerie2020.set(venda.getLivro().getTitulo(), venda.getQuantidade());
	     }
	     
	     ChartSeries vendaSerie2019 = new ChartSeries();
	     vendaSerie2019.setLabel("Vendas 2019");
	     
	     vendas = getVendas(4321);
	     for(Venda venda : vendas) {
	    	vendaSerie2019.set(venda.getLivro().getTitulo(), venda.getQuantidade());
	     }
	 
	     model.addSeries(vendaSerie2019);
	     model.addSeries(vendaSerie2020);
	     
	     model.setTitle("Vendas");
	     model.setLegendPosition("ne");
	     
	     Axis xAxis = model.getAxis(AxisType.X);
	     xAxis.setLabel("Título");
	     
	     Axis yAxis = model.getAxis(AxisType.Y);
	     yAxis.setLabel("Quantidade");
	     
	     return model;
	}
	
	public List<Venda> getVendas(long seed) {

		List<Livro> livros = this.livroDao.listaTodos();
		List<Venda> vendas = new ArrayList<Venda>();
		
		Random random = new Random(seed);
		for(Livro livro : livros) {
			Integer quantidade = random.nextInt(500);
			vendas.add(new Venda(livro, quantidade));
		}
		
		return vendas;
	}
}
