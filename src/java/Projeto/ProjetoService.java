/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto;

import ClausulaSQL.ClausulaWhere;
import ClausulaSQL.GeneroCondicaoWhere;
import ClausulaSQL.OperacaoCondicaoWhere;
import ClausulaSQL.TipoCondicaoWhere;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author was
 */
public class ProjetoService {

    public List<Projeto> listarProjetos() {
        ProjetoDAO projetoDAO = new ProjetoDAO();
        try {
            List<Projeto> projetos = projetoDAO.listarProjetos();

            return projetos;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar projetos" + ex.getMessage());
        }
    }

    public List<ProjetoRelatorio> listarQuantidadeProjetoPorUF(String pEstado) {
        ProjetoDAO dao = new ProjetoDAO();
        ClausulaWhere condicao = new ClausulaWhere();
        condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "projeto.estado", GeneroCondicaoWhere.contem, pEstado, TipoCondicaoWhere.Texto);
        List<ProjetoRelatorio> listaProjetoRelatorio = dao.listarQuantidadeProjetoPorUF(condicao);
        return listaProjetoRelatorio;
    }
    
    public List<ProjetoRelatorio> listarQuantidadePessoaPorProjeto(long pIdProjeto) {
        ProjetoDAO dao = new ProjetoDAO();
        ClausulaWhere condicao = new ClausulaWhere();
        
        if(pIdProjeto != 0) {
            condicao.AdicionarCondicaoManual("where projeto.id_projeto = " + String.valueOf(pIdProjeto));
        }else {
            condicao.AdicionarCondicaoManual("where projeto.id_projeto <> 0");
        }
        List<ProjetoRelatorio> listaProjetoRelatorio = dao.listarQuantidadePessoaPorProjeto(condicao);
        return listaProjetoRelatorio;
    }
    
     public List<ProjetoRelatorio> listarQuantidadePGMPorProjeto(long pIdProjeto) {
        ProjetoDAO dao = new ProjetoDAO();
        ClausulaWhere condicao = new ClausulaWhere();       
        if(pIdProjeto != 0) {
            condicao.AdicionarCondicaoManual("where projeto.id_projeto = " + String.valueOf(pIdProjeto));
        }else {
            condicao.AdicionarCondicaoManual("where projeto.id_projeto <> 0");
        }
        List<ProjetoRelatorio> listaProjetoRelatorio = dao.listarQuantidadePGMPorProjeto(condicao);
        return listaProjetoRelatorio;
    }
}
