package com.deliveryhero.example.consumer.db.writer;

import example.simple.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class PostgresWriter {
    private final static String INSERT_QUERY_FORMAT = "INSERT INTO public.login_messages\n" +
            "(id, \"name\", password_hash, login_ts)\n" +
            "VALUES(%s, '%s', '%s', '%s');";

    @Autowired
    private String postgresJdbcUrl;

    @Autowired
    private String postgresUsername;

    @Autowired
    private String postgresPassword;

    public void executeQuery(String query) {
        try (Connection con = DriverManager.getConnection(postgresJdbcUrl, postgresUsername, postgresPassword);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "\n" + ex);
        }
    }

    public void writeData(Login.LoginMessage loginMessage) {
        long id = loginMessage.getId();
        String name = loginMessage.getName();
        String passwordHash = loginMessage.getPasswordHash();
        String loginTS = loginMessage.getLoginTs();

        String query = String.format(INSERT_QUERY_FORMAT, id, name, passwordHash, loginTS);
        executeQuery(query);
    }
}
