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
        String sql = "select * from projeto " ;
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

}
