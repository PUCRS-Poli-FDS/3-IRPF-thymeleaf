package br.pucrs.irpf.dao.impl;

import br.pucrs.irpf.config.data.Connection;
import br.pucrs.irpf.dao.PessoaDao;
import br.pucrs.irpf.model.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class PessoaDaoImpl implements PessoaDao {

    private String insert = "INSERT INTO PESSOA(nome, cpf, idade, num_dependentes, contrinuicao_previdencial, total_rendimentos," +
                                                "desconto, valor_imposto, total_pagar, tipo_imposto) VALUES(?,?,?,?,?,?,?,?,?,?)";
    private String selectById = "";
    private String selectByName = "";
    private String selectAll = "SELECT nome, cpf, idade, num_dependentes, contrinuicao_previdencial, total_rendimentos, desconto, valor_imposto, total_pagar FROM PESSOA";

    @Autowired
    Connection connection;

    @Override
    public void save(Pessoa pessoa) throws SQLException {
        try {
            PreparedStatement preparedStatement = connection.connection().prepareStatement(insert);
            preparedStatement.setString(1, pessoa.getNome());
            preparedStatement.setString(2, pessoa.getCpf());
            preparedStatement.setInt(3, pessoa.getIdade());
            preparedStatement.setInt(4, pessoa.getNumeroDependentes());
            preparedStatement.setDouble(5, pessoa.getContribuicaoPrevidencial());
            preparedStatement.setDouble(6, pessoa.getTotalRendimentos());
            preparedStatement.setDouble(7, pessoa.getDesconto());
            preparedStatement.setDouble(8, pessoa.getImposto());
            preparedStatement.setDouble(9, pessoa.getTotalPagar());
            preparedStatement.setString(10, String.valueOf(pessoa.getTipoImposto()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.connection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

//    nome VARCHAR (250) NOT NULL,
//    cpf VARCHAR (250) NOT NULL,
//    idade INT (3) NOT NULL,
//    num_dependentes INT (3) NOT NULL,
//    contrinuicao_previdencial NUMBER (100) NOT NULL,
//    total_rendimentos NUMBER (100) NOT NULL,
//    desconto NUMBER (100) NOT NULL,
//    valor_imposto NUMBER (100) NOT NULL,
//    total_pagar NUMBER (100) NOT NULL,
//    tipo_imposto CHAR (1) NOT NULL,

    @Override
    public Pessoa getPessoaById(Pessoa pessoa) {
        return null;
    }

    @Override
    public Pessoa getPessoaByName(Pessoa pessoa) {
        return null;
    }

    @Override
    public List<Pessoa> getAll() {
        List<Pessoa> pessoaList = new ArrayList<>();
        try {
            Statement statement = connection.connection().createStatement();
            ResultSet rs = statement.executeQuery(selectAll);

            while (rs.next()) {
                pessoaList.add(Pessoa.builder()
                        .nome(rs.getString(1))
                        .cpf(rs.getString(2))
                        .idade(rs.getInt(3))
                        .numeroDependentes(rs.getInt(4))
                        .contribuicaoPrevidencial(rs.getDouble(5))
                        .totalRendimentos(rs.getDouble(6))
                        .desconto(rs.getDouble(7))
                        .imposto(rs.getDouble(8))
                        .totalPagar(rs.getDouble(9))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.connection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return pessoaList;
    }
}
