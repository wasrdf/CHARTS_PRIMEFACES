/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pessoa;

import Projeto.Projeto;
import Projeto.ProjetoService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LegendPlacement;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author was
 */
@ManagedBean
@ViewScoped
public class PessoaController {

    long idProjeto = 0;
    List<Projeto> listaProjeto = new ArrayList<Projeto>();
    Pessoa pessoaUF = new Pessoa();
    List<Pessoa> listaPessoaUF = new ArrayList<Pessoa>();
    List<Pessoa> listaPessoaCongregado = new ArrayList<Pessoa>();
    List<Pessoa> listaPessoaTipoEntrada = new ArrayList<Pessoa>();
    List<Pessoa> listaPessoaTipoSaida = new ArrayList<Pessoa>();
    List<Pessoa> listaPessoaPGM = new ArrayList<Pessoa>();
   
    //Models chart   
    public PieChartModel graficoPizzaUF;
    public BarChartModel graficoBarraUF;
    public BarChartModel graficoBarraCongregado;
    public BarChartModel graficoBarraTipoEntrada;
    public BarChartModel graficoBarraTipoSaida;
    public BarChartModel graficoBarraPgm;


    public void carregar() {
        //criarGraficoPizzaUF();
        criarGraficoBarraUF();
        criarGraficoBarraCongregado();
        criarGraficoBarraTipoEntrada();
        criarGraficoBarraTipoSaida();
        criarGraficoBarraPgm();
        carregarProjetos();
    }

    public void carregarProjetos() {
        listaProjeto = new ArrayList<Projeto>();
        ProjetoService projetoService = new ProjetoService();
        listaProjeto = projetoService.listarProjetos();        
    }
    /*
    public void criarGraficoPizzaUF() {
        graficoPizzaUF = new PieChartModel();
        PessoaService pessoaService = new PessoaService();
        listaPessoaUF = pessoaService.listarPessoaPorUF();

        for (Pessoa p : listaPessoaUF) {
            graficoPizzaUF.set(p.getDescricao(), Integer.valueOf(p.getValor()));
            graficoPizzaUF.setLegendPosition("e");
        }
*/

    public void criarGraficoBarraUF() {
        System.out.println("ID PROJETO >>>>>>>>>>>>>>>>>>>>>>>>>>>>" + idProjeto);
        System.out.println("tamanho da lista: " + listaProjeto.size());
        
        graficoBarraUF = new BarChartModel();
        PessoaService pessoaService = new PessoaService();
        
        listaPessoaUF = pessoaService.listarPessoaPorUF(idProjeto);
        if(listaPessoaUF.isEmpty()) {
            listaPessoaUF = pessoaService.listarPessoaPorUF(0);
        }
        
        for (Pessoa p : listaPessoaUF) {
            ChartSeries estado = new ChartSeries();
            estado.setLabel(p.getDescricao());
            estado.set("Pessoa", Integer.valueOf(p.getValor()));
            graficoBarraUF.addSeries(estado);

        }
        //graficoBarraUF.setTitle("Pessoa por UF");
        graficoBarraUF.setLegendPosition("e");
        graficoBarraUF.setAnimate(true);
        graficoBarraUF.setBarMargin(10);       
        graficoBarraUF.setLegendPlacement(LegendPlacement.INSIDE);
    }

    public void criarGraficoBarraCongregado() {
        graficoBarraCongregado = new BarChartModel();
        PessoaService pessoaService = new PessoaService();
        listaPessoaCongregado = pessoaService.listarPessoaCongregado(idProjeto);
        if(listaPessoaCongregado.isEmpty()) {
            listaPessoaCongregado = pessoaService.listarPessoaCongregado(0);
        }
        for (Pessoa p : listaPessoaCongregado) {
            ChartSeries estado = new ChartSeries();
            estado.setLabel(p.getDescricao());
            estado.set("Congregado", Integer.valueOf(p.getValor()));
            graficoBarraCongregado.addSeries(estado);
        }
        //graficoBarraCongregado.setTitle("Congregado");
        graficoBarraCongregado.setLegendPosition("e");
        graficoBarraCongregado.setAnimate(true);
        graficoBarraCongregado.setBarMargin(10);
        graficoBarraCongregado.setLegendPlacement(LegendPlacement.INSIDE);
    }

    public void criarGraficoBarraTipoEntrada() {
        graficoBarraTipoEntrada = new BarChartModel();
        PessoaService pessoaService = new PessoaService();
        
        listaPessoaTipoEntrada = pessoaService.listarPessoaTipoEntrada(idProjeto);
         if(listaPessoaTipoEntrada.isEmpty()) {
            listaPessoaTipoEntrada = pessoaService.listarPessoaTipoEntrada(0);
        }
        
        for (Pessoa p : listaPessoaTipoEntrada) {           
            ChartSeries estado = new ChartSeries();
            estado.setLabel(p.getDescricao());
            estado.set("Entrada", Integer.valueOf(p.getValor()));
            graficoBarraTipoEntrada.addSeries(estado);
        }
        //graficoBarraTipoEntrada.setTitle("Tipo Entrada");
        graficoBarraTipoEntrada.setLegendPosition("e");
        graficoBarraTipoEntrada.setAnimate(true);
        graficoBarraTipoEntrada.setBarMargin(10);
        graficoBarraTipoEntrada.setLegendPlacement(LegendPlacement.INSIDE);
    }

    public void criarGraficoBarraTipoSaida() {
        graficoBarraTipoSaida = new BarChartModel();
        PessoaService pessoaService = new PessoaService();
        listaPessoaTipoSaida = pessoaService.listarPessoaTipoSaida(idProjeto);        
        if(listaPessoaTipoSaida.isEmpty()) {
            listaPessoaTipoSaida = pessoaService.listarPessoaTipoSaida(0);
        }
        for (Pessoa p : listaPessoaTipoSaida) {            
            ChartSeries estado = new ChartSeries();
            estado.setLabel(p.getDescricao());
            estado.set("Saída", Integer.valueOf(p.getValor()));
            graficoBarraTipoSaida.addSeries(estado);

        }
        //graficoBarraTipoSaida.setTitle("Tipo de saída");
        graficoBarraTipoSaida.setLegendPosition("e");
        graficoBarraTipoSaida.setAnimate(true);
        graficoBarraTipoSaida.setBarMargin(10);
        graficoBarraTipoSaida.setLegendPlacement(LegendPlacement.INSIDE);
    }
    
      public void criarGraficoBarraPgm() {
        graficoBarraPgm = new BarChartModel();
        PessoaService pessoaService = new PessoaService();
        listaPessoaPGM = pessoaService.listarPessoaPGM(idProjeto);
         if(listaPessoaPGM.isEmpty()) {
            listaPessoaPGM = pessoaService.listarPessoaCongregado(0);
        }
        for (Pessoa p : listaPessoaPGM) {            
            ChartSeries estado = new ChartSeries();
            estado.setLabel(p.getDescricao());
            estado.set("PGM", Integer.valueOf(p.getValor()));
            graficoBarraPgm.addSeries(estado);
        }
        //graficoBarraPgm.setTitle("PGM");
        graficoBarraPgm.setLegendPosition("e");
        graficoBarraPgm.setAnimate(true);
        graficoBarraPgm.setBarMargin(10);
        graficoBarraPgm.setLegendPlacement(LegendPlacement.INSIDE);
    }
    

    public long getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(long idProjeto) {
        this.idProjeto = idProjeto;
    }

    public Pessoa getPessoaUF() {
        return pessoaUF;
    }

    public void setPessoaUF(Pessoa pessoaUF) {
        this.pessoaUF = pessoaUF;
    }

    public List<Pessoa> getListaPessoaUF() {
        return listaPessoaUF;
    }

    public void setListaPessoaUF(List<Pessoa> listaPessoaUF) {
        this.listaPessoaUF = listaPessoaUF;
    }

    public List<Pessoa> getListaPessoaCongregado() {
        return listaPessoaCongregado;
    }

    public void setListaPessoaCongregado(List<Pessoa> listaPessoaCongregado) {
        this.listaPessoaCongregado = listaPessoaCongregado;
    }

    public List<Pessoa> getListaPessoaTipoEntrada() {
        return listaPessoaTipoEntrada;
    }

    public void setListaPessoaTipoEntrada(List<Pessoa> listaPessoaTipoEntrada) {
        this.listaPessoaTipoEntrada = listaPessoaTipoEntrada;
    }

    public List<Pessoa> getListaPessoaTipoSaida() {
        return listaPessoaTipoSaida;
    }

    public void setListaPessoaTipoSaida(List<Pessoa> listaPessoaTipoSaida) {
        this.listaPessoaTipoSaida = listaPessoaTipoSaida;
    }

    public List<Pessoa> getListaPessoaPGM() {
        return listaPessoaPGM;
    }

    public void setListaPessoaPGM(List<Pessoa> listaPessoaPGM) {
        this.listaPessoaPGM = listaPessoaPGM;
    }

    public PieChartModel getGraficoPizzaUF() {
        return graficoPizzaUF;
    }

    public void setGraficoPizzaUF(PieChartModel graficoPizzaUF) {
        this.graficoPizzaUF = graficoPizzaUF;
    }

    public BarChartModel getGraficoBarraUF() {
        return graficoBarraUF;
    }

    public void setGraficoBarraUF(BarChartModel graficoBarraUF) {
        this.graficoBarraUF = graficoBarraUF;
    }

    public BarChartModel getGraficoBarraCongregado() {
        return graficoBarraCongregado;
    }

    public void setGraficoBarraCongregado(BarChartModel graficoBarraCongregado) {
        this.graficoBarraCongregado = graficoBarraCongregado;
    }

    public BarChartModel getGraficoBarraTipoEntrada() {
        return graficoBarraTipoEntrada;
    }

    public void setGraficoBarraTipoEntrada(BarChartModel graficoBarraTipoEntrada) {
        this.graficoBarraTipoEntrada = graficoBarraTipoEntrada;
    }

    public BarChartModel getGraficoBarraTipoSaida() {
        return graficoBarraTipoSaida;
    }

    public void setGraficoBarraTipoSaida(BarChartModel graficoBarraTipoSaida) {
        this.graficoBarraTipoSaida = graficoBarraTipoSaida;
    }

    public BarChartModel getGraficoBarraPgm() {
        return graficoBarraPgm;
    }

    public void setGraficoBarraPgm(BarChartModel graficoBarraPgm) {
        this.graficoBarraPgm = graficoBarraPgm;
    }

    public List<Projeto> getListaProjeto() {
        return listaProjeto;
    }

    public void setListaProjeto(List<Projeto> listaProjeto) {
        this.listaProjeto = listaProjeto;
    }

    
    
}
