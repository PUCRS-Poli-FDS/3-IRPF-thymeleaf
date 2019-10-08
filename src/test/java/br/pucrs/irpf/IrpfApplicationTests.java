package br.pucrs.irpf;

import br.pucrs.irpf.config.IrpfApplication;
import br.pucrs.irpf.config.values.Impostos;
import br.pucrs.irpf.model.Pessoa;
import br.pucrs.irpf.services.CalculaImposto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {IrpfApplication.class})
public class IrpfApplicationTests {

    @Autowired
    private CalculaImposto calculaImposto;

    @Test
    public void calculaImpostoTest() {
        double impostoBase = 0;
        double impostoPagar = 0;
        double desconto = 0;

        Pessoa pessoa = Pessoa.builder()
                .nome("Mario Specht")
                .cpf("01409004031")
                .idade(64)
                .numeroDependentes(1)
                .tipoImposto('C')
                .totalRendimentos(89000)
                .contribuicaoPrevidencial(5000)
                .build();

        impostoBase = pessoa.getTotalRendimentos() - pessoa.getContribuicaoPrevidencial();
        desconto = (impostoBase * Impostos.DEP_2_MENOS_IDADE) / 100;
        impostoPagar = ((((impostoBase - Impostos.BASE_MIN) * Impostos.EXED_1) / 100) + (((impostoBase - Impostos.BASE_MAX) * Impostos.EXED_2) / 100)) - desconto;
        Assert.assertEquals(impostoPagar, pessoa.getTotalPagar(), calculaImposto.calculaImposto(pessoa).getTotalPagar());
    }
}
