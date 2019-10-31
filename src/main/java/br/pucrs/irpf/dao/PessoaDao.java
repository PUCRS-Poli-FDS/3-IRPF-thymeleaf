package br.pucrs.irpf.dao;

import br.pucrs.irpf.model.Pessoa;

import java.sql.SQLException;
import java.util.List;

public interface PessoaDao {
    void save(Pessoa pessoa) throws SQLException;
    Pessoa getPessoaById(Pessoa pessoa);
    Pessoa getPessoaByName(Pessoa pessoa);
    List<Pessoa> getAll();

}
