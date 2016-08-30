/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pessoa;

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
public class PessoaDAO {

    public List<Pessoa> listarPessoaPorEstado(ClausulaWhere sClausula) {
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.conectarSGM();
            List<Pessoa> listaPessoa = new ArrayList<Pessoa>();
            String sql = "select \n"
                    + " CASE WHEN(pessoa_projeto.estado) IS null then 'Não selecionado' else \n"
                    + " pessoa_projeto.estado end as 'Descricao',\n"
                    + " COUNT(pessoa_projeto.id_pessoa_projeto) AS 'Valor'\n"
                    + " from \n"
                    + " pessoa_projeto_caracteristica \n"
                    + " inner join pessoa_projeto on pessoa_projeto.id_pessoa_projeto = pessoa_projeto_caracteristica.id_pessoa_projeto\n"
                    + " inner join projeto on pessoa_projeto.id_projeto = projeto.id_projeto\n"
                    + " left join EBD on EBD.id_projeto = pessoa_projeto.id_projeto \n" + sClausula.montarsClausula()
                    + " GROUP BY pessoa_projeto.estado";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setDescricao(rs.getString("descricao"));
                pessoa.setValor(rs.getString("valor"));
                listaPessoa.add(pessoa);
            }
            ps.close();
            rs.close();
            return listaPessoa;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar pessoa.," + ex.getMessage());
        }
    }

    public List<Pessoa> listarPessoaCongregado(ClausulaWhere sClausula) {
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.conectarSGM();
            List<Pessoa> listaPessoa = new ArrayList<Pessoa>();
            String sql = " select  'Membro' Descricao,\n"
                    + " COUNT(*) AS 'Valor'\n"
                    + " from \n"
                    + " dual\n"
                    + " left join pessoa_projeto on 1 = 1  " + sClausula.montarsClausula()
                    + " left join pessoa_projeto_caracteristica on pessoa_projeto.id_pessoa_projeto = pessoa_projeto_caracteristica.id_pessoa_projeto\n"
                    + " where pessoa_projeto_caracteristica.membro_igreja = 'true'  \n"
                    + " UNION ALL\n"
                    + " select  'Visitante' Descricao,\n"
                    + " COUNT(*) AS 'Valor'\n"
                    + " from \n"
                    + " dual\n"
                    + " left join pessoa_projeto on 1 = 1  " + sClausula.montarsClausula()
                    + " left join pessoa_projeto_caracteristica on pessoa_projeto.id_pessoa_projeto = pessoa_projeto_caracteristica.id_pessoa_projeto\n"
                    + " where pessoa_projeto_caracteristica.visitante_igreja = 'true'  \n"
                    + " UNION ALL\n"
                    + " select  'Congregado' Descricao,\n"
                    + " COUNT(*) AS 'Valor'\n"
                    + " from \n"
                    + " dual\n"
                    + " left join pessoa_projeto on 1 = 1  " + sClausula.montarsClausula()
                    + " left join pessoa_projeto_caracteristica on pessoa_projeto.id_pessoa_projeto = pessoa_projeto_caracteristica.id_pessoa_projeto\n"
                    + " where pessoa_projeto_caracteristica.congregado = 'true'  ";

            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            System.out.println(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setDescricao(rs.getString("descricao"));
                pessoa.setValor(rs.getString("valor"));
                listaPessoa.add(pessoa);
            }
            ps.close();
            rs.close();
            return listaPessoa;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar congregado.," + ex.getMessage());
        }

    }

    public List<Pessoa> listaPessoaTipoEntrada(ClausulaWhere sClausula) {
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.conectarSGM();
            List<Pessoa> listaPessoa = new ArrayList<Pessoa>();
            String sql = " select \n"
                    + " CASE WHEN(pessoa_projeto_caracteristica.entrada_tipo) IS NULL THEN 'Não selecionado' else pessoa_projeto_caracteristica.entrada_tipo end as 'Descricao',\n"
                    + " COUNT(pessoa_projeto_caracteristica.id_pessoa_caracteristica) AS 'Valor'\n"
                    + " from \n"
                    + " pessoa_projeto_caracteristica  \n"
                    + " left join pessoa_projeto on pessoa_projeto.id_pessoa_projeto = pessoa_projeto_caracteristica.id_pessoa_projeto \n"
                    + sClausula.montarsClausula()
                    + " GROUP BY pessoa_projeto_caracteristica.entrada_tipo";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setDescricao(rs.getString("descricao"));
                pessoa.setValor(rs.getString("valor"));
                listaPessoa.add(pessoa);
            }
            ps.close();
            rs.close();
            return listaPessoa;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar tipo entrada.," + ex.getMessage());
        }

    }

    public List<Pessoa> listaPessoaTipoSaida(ClausulaWhere sClausula) {
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.conectarSGM();
            List<Pessoa> listaPessoa = new ArrayList<Pessoa>();
            String sql = "select \n"
                    + "CASE WHEN(pessoa_projeto_caracteristica.saida_tipo) IS NULL THEN 'Não selecionado' else pessoa_projeto_caracteristica.saida_tipo end as 'Descricao',\n"
                    + "COUNT(pessoa_projeto_caracteristica.id_pessoa_caracteristica) AS 'Valor'\n"
                    + "from \n"
                    + "pessoa_projeto_caracteristica "
                    + "inner join pessoa_projeto on pessoa_projeto.id_pessoa_projeto =  pessoa_projeto_caracteristica.id_pessoa_projeto \n"
                    + sClausula.montarsClausula()
                    + "GROUP BY pessoa_projeto_caracteristica.saida_tipo";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setDescricao(rs.getString("descricao"));
                pessoa.setValor(rs.getString("valor"));

                listaPessoa.add(pessoa);
            }
            ps.close();
            rs.close();
            return listaPessoa;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar tipo saída.," + ex.getMessage());
        }

    }

    public List<Pessoa> listaPessoaPGM(ClausulaWhere sClausula) {
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.conectarSGM();
            List<Pessoa> listaPessoa = new ArrayList<Pessoa>();
            String sql = " select \n"
                    + "' Membro' Descricao,\n"
                    + " COUNT(*) AS 'Valor'\n"
                    + " from \n"
                    + " dual\n"
                    + " left join pessoa_projeto on 1 = 1 " + sClausula.montarsClausula()
                    + " left join pessoa_projeto_caracteristica on pessoa_projeto_caracteristica.id_pessoa_projeto = pessoa_projeto.id_pessoa_projeto\n"
                    + " where pessoa_projeto_caracteristica.membro_pgm = 'true' \n"
                    + " UNION ALL\n"
                    + " select \n"
                    + "'Visitante' Descricao,\n"
                    + " COUNT(*) AS 'Valor'\n"
                    + " from \n"
                    + " dual\n"
                    + " left join pessoa_projeto on 1 = 1 " + sClausula.montarsClausula()
                    + " left join pessoa_projeto_caracteristica on pessoa_projeto_caracteristica.id_pessoa_projeto = pessoa_projeto.id_pessoa_projeto\n"
                    + " where pessoa_projeto_caracteristica.visitante_pgm = 'true' ";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setDescricao(rs.getString("descricao"));
                pessoa.setValor(rs.getString("valor"));

                listaPessoa.add(pessoa);
            }
            ps.close();
            rs.close();
            return listaPessoa;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao listar PGM.," + ex.getMessage());
        }

    }
}
