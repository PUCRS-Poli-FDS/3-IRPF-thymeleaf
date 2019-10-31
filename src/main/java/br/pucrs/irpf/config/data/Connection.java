package br.pucrs.irpf.config.data;

import org.springframework.stereotype.Service;

import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class Connection {

    public java.sql.Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:file:~/Documents/Facul/FundamentosDeSW/git/irpf/db/irpf", "sa", "password");
    }
}
