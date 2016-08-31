/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LegendPlacement;

/**
 *
 * @author was
 */
@ManagedBean
@ViewScoped
public class ProjetoController {

    List<ProjetoRelatorio> quantidadeProjetosPorUf = new ArrayList<ProjetoRelatorio>();
    List<ProjetoRelatorio> quantidadePessoaPorProjeto = new ArrayList<ProjetoRelatorio>();
    List<ProjetoRelatorio> quantidadePGMPorProjeto = new ArrayList<ProjetoRelatorio>();
    long idProjeto = 0;
    List<Projeto> listaProjeto = new ArrayList<Projeto>();

    //Models chart      
    public BarChartModel graficoBarraQuantidadeProjetoPorUF;
    public BarChartModel graficoBarraQuantidadePessoaPorProjeto;
    public BarChartModel graficoBarraQuantidadePgmPorProjeto;

    String estado = "";

    public void carregar() {
        criarGraficoBarraQuantidadeProjetoPorUF();
        criarGraficoBarraQuantidadePessoaPorProjeto();
        criarGraficoBarraQuantidadePGMPorProjeto();
        carregarProjetos();
    }

    public void carregarProjetos() {
        listaProjeto = new ArrayList<Projeto>();
        ProjetoService projetoService = new ProjetoService();
        listaProjeto = projetoService.listarProjetos();
    }

  
    public void criarGraficoBarraQuantidadeProjetoPorUF() {
        graficoBarraQuantidadeProjetoPorUF = new BarChartModel();
        ProjetoService projetoService = new ProjetoService();
        quantidadeProjetosPorUf = projetoService.listarQuantidadeProjetoPorUF(estado);
        if(quantidadeProjetosPorUf.isEmpty()) {
            quantidadeProjetosPorUf = projetoService.listarQuantidadeProjetoPorUF("");
        } 
        for (ProjetoRelatorio p : quantidadeProjetosPorUf) {
            ChartSeries estado = new ChartSeries();
            estado.setLabel(p.getDescricao());
            estado.set(" ", Integer.valueOf(p.getValor()));
            graficoBarraQuantidadeProjetoPorUF.addSeries(estado);
        }
        graficoBarraQuantidadeProjetoPorUF.setLegendPosition("e");
        graficoBarraQuantidadeProjetoPorUF.setAnimate(true);
        graficoBarraQuantidadeProjetoPorUF.setBarMargin(10);
        graficoBarraQuantidadeProjetoPorUF.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
    }

    public void criarGraficoBarraQuantidadePessoaPorProjeto() {
        graficoBarraQuantidadePessoaPorProjeto = new BarChartModel();
        ProjetoService projetoService = new ProjetoService();
        
        quantidadePessoaPorProjeto = projetoService.listarQuantidadePessoaPorProjeto(idProjeto);
        if (quantidadePessoaPorProjeto.isEmpty()) {
            quantidadePessoaPorProjeto = projetoService.listarQuantidadePessoaPorProjeto(0);
        }
        for (ProjetoRelatorio p : quantidadePessoaPorProjeto) {
            ChartSeries estado = new ChartSeries();
            estado.setLabel(p.getDescricao());
            estado.set(" ", Integer.valueOf(p.getValor()));
            graficoBarraQuantidadePessoaPorProjeto.addSeries(estado);
        }
        graficoBarraQuantidadePessoaPorProjeto.setLegendPosition("e");
        graficoBarraQuantidadePessoaPorProjeto.setAnimate(true);
        graficoBarraQuantidadePessoaPorProjeto.setBarMargin(10);
        graficoBarraQuantidadePessoaPorProjeto.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
    }
    
    public void criarGraficoBarraQuantidadePGMPorProjeto() {
        graficoBarraQuantidadePgmPorProjeto = new BarChartModel();
        ProjetoService projetoService = new ProjetoService();
        quantidadePGMPorProjeto = projetoService.listarQuantidadePGMPorProjeto(idProjeto);
        if (quantidadePGMPorProjeto.isEmpty()) {
            quantidadePGMPorProjeto = projetoService.listarQuantidadePGMPorProjeto(0);
        }
        for (ProjetoRelatorio p : quantidadePGMPorProjeto) {
            ChartSeries estado = new ChartSeries();
            estado.setLabel(p.getDescricao());
            estado.set(" ", Integer.valueOf(p.getValor()));
            graficoBarraQuantidadePgmPorProjeto.addSeries(estado);
        }
        graficoBarraQuantidadePgmPorProjeto.setLegendPosition("e");
        graficoBarraQuantidadePgmPorProjeto.setAnimate(true);
        graficoBarraQuantidadePgmPorProjeto.setBarMargin(10);
        graficoBarraQuantidadePgmPorProjeto.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
    }
    
    // GET E SET
    public List<ProjetoRelatorio> getQuantidadeProjetosPorUf() {
        return quantidadeProjetosPorUf;
    }

    public void setQuantidadeProjetosPorUf(List<ProjetoRelatorio> quantidadeProjetosPorUf) {
        this.quantidadeProjetosPorUf = quantidadeProjetosPorUf;
    }

    public List<ProjetoRelatorio> getQuantidadePessoaPorProjeto() {
        return quantidadePessoaPorProjeto;
    }

    public void setQuantidadePessoaPorProjeto(List<ProjetoRelatorio> quantidadePessoaPorProjeto) {
        this.quantidadePessoaPorProjeto = quantidadePessoaPorProjeto;
    }

    public List<ProjetoRelatorio> getQuantidadePGMPorProjeto() {
        return quantidadePGMPorProjeto;
    }

    public void setQuantidadePGMPorProjeto(List<ProjetoRelatorio> quantidadePGMPorProjeto) {
        this.quantidadePGMPorProjeto = quantidadePGMPorProjeto;
    }

    public long getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(long idProjeto) {
        this.idProjeto = idProjeto;
    }

    public List<Projeto> getListaProjeto() {
        return listaProjeto;
    }

    public void setListaProjeto(List<Projeto> listaProjeto) {
        this.listaProjeto = listaProjeto;
    }

    public BarChartModel getGraficoBarraQuantidadeProjetoPorUF() {
        return graficoBarraQuantidadeProjetoPorUF;
    }

    public void setGraficoBarraQuantidadeProjetoPorUF(BarChartModel graficoBarraQuantidadeProjetoPorUF) {
        this.graficoBarraQuantidadeProjetoPorUF = graficoBarraQuantidadeProjetoPorUF;
    }

    public BarChartModel getGraficoBarraQuantidadePessoaPorProjeto() {
        return graficoBarraQuantidadePessoaPorProjeto;
    }

    public void setGraficoBarraQuantidadePessoaPorProjeto(BarChartModel graficoBarraQuantidadePessoaPorProjeto) {
        this.graficoBarraQuantidadePessoaPorProjeto = graficoBarraQuantidadePessoaPorProjeto;
    }

    public BarChartModel getGraficoBarraQuantidadePgmPorProjeto() {
        return graficoBarraQuantidadePgmPorProjeto;
    }

    public void setGraficoBarraQuantidadePgmPorProjeto(BarChartModel graficoBarraQuantidadePgmPorProjeto) {
        this.graficoBarraQuantidadePgmPorProjeto = graficoBarraQuantidadePgmPorProjeto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
