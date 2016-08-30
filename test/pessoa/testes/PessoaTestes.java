/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pessoa.testes;

import Pessoa.Pessoa;
import Pessoa.PessoaDAO;
import Projeto.Projeto;
import Projeto.ProjetoDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author was
 */
public class PessoaTestes {

    public List<Pessoa> listarPessoaPorUF() {
        PessoaDAO pessoaDAO = new PessoaDAO();
      //  List<Pessoa> listaPessoa = pessoaDAO.listarPessoaPorEstado();
        return null;
    }

    public List<Pessoa> listarPessoaCongregado() {
        PessoaDAO pessoaDAO = new PessoaDAO();
        List<Pessoa> listaPessoa = pessoaDAO.listarPessoaCongregado();
        return listaPessoa;
    }

    public List<Pessoa> listarPessoaTipoEntrada() {
        PessoaDAO pessoaDAO = new PessoaDAO();
        List<Pessoa> listaPessoa = pessoaDAO.listaPessoaTipoEntrada();
        
        return listaPessoa;
    }

    public List<Pessoa> listarPessoaTipoSaida() {
        PessoaDAO pessoaDAO = new PessoaDAO();
        List<Pessoa> listaPessoa = pessoaDAO.listaPessoaTipoSaida();
        return listaPessoa;
    }

    public List<Pessoa> listarPessoaPGM() {
        PessoaDAO pessoaDAO = new PessoaDAO();
        List<Pessoa> listaPessoa = pessoaDAO.listaPessoaPGM();
       
        return listaPessoa;
    }

    public List<Projeto> listarProjetos() {
        ProjetoDAO projetoDAO = new ProjetoDAO();
        try {
            List<Projeto> projetos = projetoDAO.listarProjetos();
           
            return projetos;
        } catch (SQLException ex) {
            throw  new RuntimeException("Erro ao listar projetos" + ex.getMessage());
        }
    }
    public static void main(String[] args) {
        PessoaTestes teste = new PessoaTestes();
        teste.listarProjetos();
    }

}
