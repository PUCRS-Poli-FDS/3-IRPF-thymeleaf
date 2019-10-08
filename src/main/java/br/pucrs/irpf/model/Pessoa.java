package br.pucrs.irpf.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pessoa {

    private String nome;
    private String cpf;
    private int idade;
    private int numeroDependentes;
    private double contribuicaoPrevidencial;
    private double totalRendimentos;
    private char tipoImposto;
    private double desconto;
    private double imposto;
    private double totalPagar;
}
