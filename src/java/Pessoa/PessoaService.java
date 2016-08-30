/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pessoa;

import ClausulaSQL.ClausulaWhere;
import java.util.List;

/**
 *
 * @author was
 */
public class PessoaService {

    public List<Pessoa> listarPessoaPorUF(long pIdProjeto) {
        PessoaDAO pessoaDAO = new PessoaDAO();
        ClausulaWhere condicao = new ClausulaWhere();
        if (pIdProjeto != 0) {
            condicao.AdicionarCondicaoManual("where pessoa_projeto.id_projeto = " + String.valueOf(pIdProjeto));
        } else {
            condicao.AdicionarCondicaoManual("where pessoa_projeto.id_projeto  <> 0");
        }
        List<Pessoa> listaPessoa = pessoaDAO.listarPessoaPorEstado(condicao);
        return listaPessoa;
    }

    public List<Pessoa> listarPessoaCongregado(long pIdProjeto) {
        PessoaDAO pessoaDAO = new PessoaDAO();
        ClausulaWhere condicao = new ClausulaWhere();
        if (pIdProjeto != 0) {
            condicao.AdicionarCondicaoManual(" and pessoa_projeto.id_projeto = " + String.valueOf(pIdProjeto));
        } else {
            condicao.AdicionarCondicaoManual(" and pessoa_projeto.id_projeto  <> 0");
        }
        List<Pessoa> listaPessoa = pessoaDAO.listarPessoaCongregado(condicao);

        return listaPessoa;
    }

    public List<Pessoa> listarPessoaTipoEntrada(long pIdProjeto) {
        PessoaDAO pessoaDAO = new PessoaDAO();
        ClausulaWhere condicao = new ClausulaWhere();
        if (pIdProjeto != 0) {
            condicao.AdicionarCondicaoManual(" where pessoa_projeto.id_projeto = " + String.valueOf(pIdProjeto));
        } else {
            condicao.AdicionarCondicaoManual(" where pessoa_projeto.id_projeto  <> 0");
        }
        List<Pessoa> listaPessoa = pessoaDAO.listaPessoaTipoEntrada(condicao);
        return listaPessoa;
    }

    public List<Pessoa> listarPessoaTipoSaida(long pIdProjeto) {
        PessoaDAO pessoaDAO = new PessoaDAO();
        ClausulaWhere condicao = new ClausulaWhere();
        if (pIdProjeto != 0) {
            condicao.AdicionarCondicaoManual(" where pessoa_projeto.id_projeto = " + String.valueOf(pIdProjeto));
        } else {
            condicao.AdicionarCondicaoManual(" where pessoa_projeto.id_projeto  <> 0");
        }
        List<Pessoa> listaPessoa = pessoaDAO.listaPessoaTipoSaida(condicao);
        return listaPessoa;
    }

    public List<Pessoa> listarPessoaPGM(long pIdProjeto) {
        PessoaDAO pessoaDAO = new PessoaDAO();
        ClausulaWhere condicao = new ClausulaWhere();
        if (pIdProjeto != 0) {
            condicao.AdicionarCondicaoManual(" and pessoa_projeto.id_projeto = " + String.valueOf(pIdProjeto));
        } else {
            condicao.AdicionarCondicaoManual(" and pessoa_projeto.id_projeto  <> 0");
        }
        List<Pessoa> listaPessoa = pessoaDAO.listaPessoaPGM(condicao);
        return listaPessoa;
    }

}
