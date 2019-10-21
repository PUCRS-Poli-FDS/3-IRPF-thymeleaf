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

    public Pessoa(String nome, String cpf, int idade, int numeroDependentes, double contribuicaoPrevidencial, double totalRendimentos, char tipoImposto, double desconto, double imposto, double totalPagar) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.numeroDependentes = numeroDependentes;
        this.contribuicaoPrevidencial = contribuicaoPrevidencial;
        this.totalRendimentos = totalRendimentos;
        this.tipoImposto = tipoImposto;
        this.desconto = desconto;
        this.imposto = imposto;
        this.totalPagar = totalPagar;
    }
}
