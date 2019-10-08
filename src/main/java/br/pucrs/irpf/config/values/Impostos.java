package br.pucrs.irpf.config.values;

import br.pucrs.irpf.model.Pessoa;
import br.pucrs.irpf.services.CalculaImposto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class Impostos {

    @Autowired
    private CalculaImposto calculaImposto;

//    @PostConstruct
//    public void teste() {
//        Pessoa p = br.pucrs.irpf.model.Pessoa.builder()
//                .nome("Mario Specht")
//                .cpf("01409004031")
//                .idade(64)
//                .numeroDependentes(1)
//                .tipoImposto('C')
//                .totalRendimentos(89000)
//                .contribuicaoPrevidencial(5000)
//                .build();
//
//        Pessoa p1 = calculaImposto.calculaImposto(p);
//        System.out.println(
//                "Imposto a pagar ----> " + p1.getTotalPagar() +
//                "\nTotal impostos -----> " + p1.getImposto() +
//                "\nTotal Descontos -----> " + p1.getDesconto() +
//                "\nNumero Dependentes -----> " + p1.getNumeroDependentes()
//        );
//    }
}
