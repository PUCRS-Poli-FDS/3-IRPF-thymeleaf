package br.pucrs.irpf;

import br.pucrs.irpf.config.IrpfApplication;
import br.pucrs.irpf.model.Pessoa;
import br.pucrs.irpf.services.CalculaImposto;
import br.pucrs.irpf.utils.Values;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@SpringBootTest
//@ComponentScan(basePackages ={"br.pucrs.irpf"})
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
        desconto = (impostoBase * Values.DEP_2_MENOS_IDADE) / 100;
        impostoPagar = ((((impostoBase - Values.BASE_MIN) * Values.EXED_1) / 100) + (((impostoBase - Values.BASE_MAX) * Values.EXED_2) / 100)) - desconto;
        Assert.assertEquals(impostoPagar, pessoa.getTotalPagar(), calculaImposto.calculaImposto(pessoa).getTotalPagar());
    }
}
