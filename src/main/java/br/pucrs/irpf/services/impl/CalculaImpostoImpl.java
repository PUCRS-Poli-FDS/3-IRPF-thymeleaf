package br.pucrs.irpf.services.impl;

import br.pucrs.irpf.config.values.Impostos;
import br.pucrs.irpf.model.Pessoa;
import br.pucrs.irpf.services.CalculaImposto;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class CalculaImpostoImpl implements CalculaImposto {

    public Pessoa calculaImposto(@NonNull Pessoa pessoa) {
        pessoa.setImposto(impostoBase(pessoa));
        pessoa.setDesconto(desconto(pessoa));
        pessoa.setTotalPagar(pessoa.getImposto() - pessoa.getDesconto());
        return pessoa;
    }

    private double desconto(@NonNull Pessoa pessoa) {
        int dependentes = pessoa.getNumeroDependentes();
        double base = baseCalculo(pessoa);
        double desconto = 0;

        if (pessoa.getTipoImposto() == 'S') {
            return (base * Impostos.PORCENTAGEM_BASE) / 100;
        }

        if (pessoa.getIdade() >= Impostos.IDADE) {
            if (dependentes >= 3 && dependentes <= 5) {
                desconto = (base * Impostos.DEP_5_MAIS_IDADE) / 100;
            } else if (dependentes > 5) {
                desconto = (base * Impostos.DEP_MAIS_5_MAIS_IDADE) / 100;
            } else {
                desconto = (base * Impostos.DEP_2_MAIS_IDADE) / 100;
            }
        } else {
            if (dependentes >= 3 && dependentes <= 5) {
                desconto = (base * Impostos.DEP_5_MENOS_IDADE) / 100;
            } else if (dependentes > 5) {
                desconto = (base * Impostos.DEP_MAIS_5_MENOS_IDADE) / 100;
            } else {
                desconto = (base * Impostos.DEP_2_MENOS_IDADE) / 100;
            }
        }

        return desconto;
    }

    private double impostoBase(@NonNull Pessoa pessoa) {
        double valorImposto = 0;
        double valorBase = baseCalculo(pessoa);

        if (valorBase > Impostos.BASE_MIN) {
            valorImposto = ((valorBase - Impostos.BASE_MIN) * Impostos.EXED_1) / 100;
            if (valorBase >= Impostos.BASE_MAX) {
                valorImposto +=  ((valorBase - Impostos.BASE_MAX) * Impostos.EXED_2) / 100;
            }
        }
        return valorImposto;
    }

    private double baseCalculo(@NonNull Pessoa pessoa) {
        return pessoa.getTotalRendimentos() - pessoa.getContribuicaoPrevidencial();
    }
}
