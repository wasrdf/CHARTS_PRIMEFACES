/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto;

import ClausulaSQL.ClausulaWhere;
import Util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author was
 */
public class ProjetoDAO {

    public List<Projeto> listarProjetos() throws SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectarSGM();
        String sql = "select * from projeto ";
        System.out.println(sql);
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Projeto> projetos = new ArrayList<Projeto>();
        while (rs.next()) {
            Projeto p = new Projeto();
            p.setIdProjeto(rs.getInt("id_projeto"));
            p.setNome(rs.getString("nome"));
            projetos.add(p);
        }

        rs.close();
        ps.close();
        return projetos;
    }

    public List<ProjetoRelatorio> listarQuantidadeProjetoPorUF(ClausulaWhere sClausula) {
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.conectarSGM();
            List<ProjetoRelatorio> listaProjetoRelatorio = new ArrayList<ProjetoRelatorio>();
            String sql = " select \n"
                    + " CASE WHEN(projeto.estado) IS null then 'Não selecionado' else \n"
                    + " projeto.estado end as 'Descricao',\n"
                    + " COUNT(projeto.id_projeto) AS 'Valor'\n"
                    + " from \n"
                    + " projeto \n"
                    + sClausula.montarsClausula()
                    + "  GROUP BY projeto.estado";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            System.out.println(sql);
            while (rs.next()) {
                ProjetoRelatorio projetoRelatorio = new ProjetoRelatorio();
                projetoRelatorio.setDescricao(rs.getString("descricao"));
                projetoRelatorio.setValor(rs.getString("valor"));
                listaProjetoRelatorio.add(projetoRelatorio);
            }
            ps.close();
            rs.close();
            return listaProjetoRelatorio;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar projeto por UF." + ex.getMessage());
        }
    }

    public List<ProjetoRelatorio> listarQuantidadePessoaPorProjeto(ClausulaWhere sClausula) {
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.conectarSGM();
            List<ProjetoRelatorio> listaProjetoRelatorio = new ArrayList<ProjetoRelatorio>();
            String sql = "select \n"
                    + "projeto.nome as 'Descricao',\n"
                    + "COUNT(pessoa_projeto.id_projeto) AS 'Valor'\n"
                    + "from \n"
                    + "projeto \n"
                    + "inner join pessoa_projeto on pessoa_projeto.id_projeto = projeto.id_projeto \n"
                    + sClausula.montarsClausula()
                    + "GROUP BY projeto.nome";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ProjetoRelatorio projetoRelatorio = new ProjetoRelatorio();
                projetoRelatorio.setDescricao(rs.getString("descricao"));
                projetoRelatorio.setValor(rs.getString("valor"));
                listaProjetoRelatorio.add(projetoRelatorio);
            }
            ps.close();
            rs.close();
            return listaProjetoRelatorio;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar pessoa por projeto.," + ex.getMessage());
        }
    }

    public List<ProjetoRelatorio> listarQuantidadePGMPorProjeto(ClausulaWhere sClausula) {
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.conectarSGM();
            List<ProjetoRelatorio> listaProjetoRelatorio = new ArrayList<ProjetoRelatorio>();
            String sql = " select \n"
                    + " projeto.nome as 'Descricao',\n"
                    + " COUNT(pgm.id_projeto) AS 'Valor'\n"
                    + " from \n"
                    + " projeto \n"
                    + " inner join pgm on pgm.id_projeto = projeto.id_projeto \n"
                    + sClausula.montarsClausula()
                    + " GROUP BY projeto.nome ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ProjetoRelatorio projetoRelatorio = new ProjetoRelatorio();
                projetoRelatorio.setDescricao(rs.getString("descricao"));
                projetoRelatorio.setValor(rs.getString("valor"));
                listaProjetoRelatorio.add(projetoRelatorio);
            }
            ps.close();
            rs.close();
            return listaProjetoRelatorio;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar pgm por projeto.," + ex.getMessage());
        }
    }

}
