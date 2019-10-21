package br.pucrs.irpf.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {

    private String nome;
    private String cpf;
    private int idade;
    private int numeroDependentes;
    private double contribuicaoPrevidencial;
    private double totalRendimentos;
    private char tipoImposto;
    @Nullable
    private double desconto;
    @Nullable
    private double imposto;
    @Nullable
    private double totalPagar;
}
