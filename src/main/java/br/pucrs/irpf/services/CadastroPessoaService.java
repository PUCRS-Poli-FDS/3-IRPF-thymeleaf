package br.pucrs.irpf.services;

import br.pucrs.irpf.model.Pessoa;

import java.util.List;

public interface CadastroPessoaService {

    boolean addPessoa(Pessoa pessoa);

    List<Pessoa> getPessoaList();

    Pessoa getPessoaById(Pessoa pessoa);

    List<Pessoa> calculaImpostoPessoas(List<Pessoa> pessoas);
}
