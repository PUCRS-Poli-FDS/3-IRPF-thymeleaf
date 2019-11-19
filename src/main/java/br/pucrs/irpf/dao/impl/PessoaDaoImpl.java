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
    private String selectById = "SELECT id, nome, cpf, idade, num_dependentes, contrinuicao_previdencial, total_rendimentos, desconto, valor_imposto, total_pagar, tipo_imposto " +
            "FROM PESSOA " +
            "WHERE ID = ?";
    private String selectByName = "";
    private String selectAll = "SELECT id, nome, cpf, idade, num_dependentes, contrinuicao_previdencial, total_rendimentos, desconto, valor_imposto, total_pagar, tipo_imposto FROM PESSOA";

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

    @Override
    public Pessoa getPessoaById(Pessoa pessoa) {
        Pessoa result = null;
        try {
            PreparedStatement preparedStatement = connection.connection().prepareStatement(selectById);
            preparedStatement.setInt(1, pessoa.getId());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                result = Pessoa.builder()
                        .id(rs.getInt(1))
                        .nome(rs.getString(2))
                        .cpf(rs.getString(3))
                        .idade(rs.getInt(4))
                        .numeroDependentes(rs.getInt(5))
                        .contribuicaoPrevidencial(rs.getDouble(6))
                        .totalRendimentos(rs.getDouble(7))
                        .desconto(rs.getDouble(8))
                        .imposto(rs.getDouble(9))
                        .totalPagar(rs.getDouble(10))
                        .tipoImposto(rs.getString(11).charAt(0))
                        .build();
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
        return result;
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
                        .id(rs.getInt(1))
                        .nome(rs.getString(2))
                        .cpf(rs.getString(3))
                        .idade(rs.getInt(4))
                        .numeroDependentes(rs.getInt(5))
                        .contribuicaoPrevidencial(rs.getDouble(6))
                        .totalRendimentos(rs.getDouble(7))
                        .desconto(rs.getDouble(8))
                        .imposto(rs.getDouble(9))
                        .totalPagar(rs.getDouble(10))
                        .tipoImposto(rs.getString(11).charAt(0))
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
