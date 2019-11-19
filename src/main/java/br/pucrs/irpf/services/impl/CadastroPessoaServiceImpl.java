package br.pucrs.irpf.services.impl;

import br.pucrs.irpf.dao.PessoaDao;
import br.pucrs.irpf.model.Pessoa;
import br.pucrs.irpf.services.CadastroPessoaService;
import br.pucrs.irpf.services.CalculaImposto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CadastroPessoaServiceImpl implements CadastroPessoaService {

    @Autowired
    private CalculaImposto calculaImposto;

    @Autowired
    private PessoaDao pessoaDao;

    public boolean addPessoa(Pessoa pessoa) {
        try {
            pessoaDao.save(pessoa);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Pessoa> getPessoaList() {
        return pessoaDao.getAll().stream()
                .collect(Collectors.toList());
    }

    public Pessoa getPessoaById(Pessoa pessoa) {
        return pessoaDao.getPessoaById(pessoa);
    }

    public List<Pessoa> calculaImpostoPessoas(List<Pessoa> pessoas) {
        return pessoas.stream()
                .map(pessoa -> calculaImposto.calculaImposto(pessoa))
                .collect(Collectors.toList());
    }
}
