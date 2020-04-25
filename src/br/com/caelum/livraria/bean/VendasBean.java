package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.caelum.livraria.dao.VendasDao;
import br.com.caelum.livraria.modelo.Vendas;

@Named
@ViewScoped
public class VendasBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private VendasDao vendasDao;

	public BarChartModel getVendasModel() {
		
		 BarChartModel model = new BarChartModel();
		 model.setAnimate(true);
	     ChartSeries vendaSerie2020 = new ChartSeries();
	     vendaSerie2020.setLabel("Vendas 2020");
	     
	     List<Vendas> vendas = getVendas(); 
	     
	     for(Vendas venda : vendas) {
	       	vendaSerie2020.set(venda.getLivro().getTitulo(), venda.getQuantidade());
	     }
	     
	     model.addSeries(vendaSerie2020);
	     
	     model.setTitle("Vendas");
	     model.setLegendPosition("ne");
	     
	     Axis xAxis = model.getAxis(AxisType.X);
	     xAxis.setLabel("Título");
	     
	     Axis yAxis = model.getAxis(AxisType.Y);
	     yAxis.setLabel("Quantidade");
	     
	     return model;
	}
	
	public List<Vendas> getVendas() {
		List<Vendas> vendas = this.vendasDao.listaTodos();
		return vendas;
	}
}
