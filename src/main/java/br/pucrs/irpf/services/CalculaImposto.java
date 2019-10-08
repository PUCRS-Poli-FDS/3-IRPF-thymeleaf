package br.pucrs.irpf.services;

import br.pucrs.irpf.model.Pessoa;

public interface CalculaImposto {
    Pessoa calculaImposto(Pessoa pessoa);
}
