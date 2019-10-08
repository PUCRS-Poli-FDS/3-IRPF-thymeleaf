package br.pucrs.irpf.services.impl;

import br.pucrs.irpf.model.Pessoa;
import br.pucrs.irpf.services.CalculaImposto;
import br.pucrs.irpf.utils.Values;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class CalculaImpostoImpl implements CalculaImposto {

    public Pessoa calculaImposto(@NonNull Pessoa pessoa) {
        pessoa.setImposto(impostoBase(pessoa));
        pessoa.setDesconto(desconto(pessoa));
        pessoa.setTotalPagar(impostoBase(pessoa) - desconto(pessoa));
        return pessoa;
    }

    private double desconto(@NonNull Pessoa pessoa) {
        int dependentes = pessoa.getNumeroDependentes();
        double base = baseCalculo(pessoa);
        double desconto = 0;

        if (pessoa.getTipoImposto() == 'S') {
            return (base * Values.PORCENTAGEM_BASE) / 100;
        }

        if (pessoa.getIdade() >= Values.IDADE) {
            if (dependentes >= 3 && dependentes <= 5) {
                desconto = (base * Values.DEP_5_MAIS_IDADE) / 100;
            } else if (dependentes > 5) {
                desconto = (base * Values.DEP_MAIS_5_MAIS_IDADE) / 100;
            } else {
                desconto = (base * Values.DEP_2_MAIS_IDADE) / 100;
            }
        } else {
            if (dependentes >= 3 && dependentes <= 5) {
                desconto = (base * Values.DEP_5_MENOS_IDADE) / 100;
            } else if (dependentes > 5) {
                desconto = (base * Values.DEP_MAIS_5_MENOS_IDADE) / 100;
            } else {
                desconto = (base * Values.DEP_2_MENOS_IDADE) / 100;
            }
        }

        return desconto;
    }

    private double impostoBase(@NonNull Pessoa pessoa) {
        double valorImposto = 0;
        double valorBase = baseCalculo(pessoa);

        if (valorBase > Values.BASE_MIN) {
            valorImposto = ((valorBase - Values.BASE_MIN) * Values.EXED_1) / 100;
            if (valorBase >= Values.BASE_MAX) {
                valorImposto +=  ((valorBase - Values.BASE_MAX) * Values.EXED_2) / 100;
            }
        }
        return valorImposto;
    }

    private double baseCalculo(@NonNull Pessoa pessoa) {
        return pessoa.getTotalRendimentos() - pessoa.getContribuicaoPrevidencial();
    }
}
