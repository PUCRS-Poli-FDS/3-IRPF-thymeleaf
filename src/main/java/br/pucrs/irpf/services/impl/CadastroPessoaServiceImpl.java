package br.pucrs.irpf.services.impl;

import br.pucrs.irpf.model.Pessoa;
import br.pucrs.irpf.services.CadastroPessoaService;
import br.pucrs.irpf.services.CalculaImposto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CadastroPessoaServiceImpl implements CadastroPessoaService {

    @Autowired
    private CalculaImposto calculaImposto;

    private List<Pessoa> pessoaList = new ArrayList<>();

    public boolean addPessoa(Pessoa pessoa) {
        return pessoaList.add(pessoa) ? true : false;
    }

    public List<Pessoa> getPessoaList() {
        return pessoaList.stream()
                .collect(Collectors.toList());
    }

    public List<Pessoa> calculaImpostoPessoas(List<Pessoa> pessoas) {
        return pessoas.stream()
                .map(pessoa -> calculaImposto.calculaImposto(pessoa))
                .collect(Collectors.toList());
    }
}
