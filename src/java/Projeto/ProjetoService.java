/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto;

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
            throw  new RuntimeException("Erro ao listar projetos" + ex.getMessage());
        }
    }
}
