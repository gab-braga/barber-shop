package model.dao;

import java.sql.*;

/**
 *
 * @author Gabriel Braga
 */
public abstract class FabricaDeConexao {

    final static protected String banco_de_dados = "barbershop";
    final static private String url = "jdbc:mysql://127.0.0.1:3306/";
    final static private String usuario = "usuario";
    final static private String senha = "senha";

    protected static Connection conexao = null;

    protected static boolean abrir_conexao() {
        boolean flag = false;
        try {
            conexao = DriverManager.getConnection(url, usuario, senha);
            flag = true;
        } catch (SQLException e) {
            System.err.println("ERRO (ABRIR CONEXÃO): " + e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    protected static boolean fechar_conexao() {
        boolean flag = false;
        try {
            conexao.close();
            flag = true;
        } catch (SQLException e) {
            System.err.println("ERRO (FECHAR CONEXÃO): " + e.getMessage());
        }
        return flag;
    }

    protected static boolean criar_banco_de_dados() {
        boolean flag = false;
        try {
            if (!conexao.isClosed()) {
                Statement statement = conexao.createStatement();
                String sql = "CREATE DATABASE IF NOT EXISTS " + banco_de_dados + ";";
                statement.execute(sql);
                flag = true;
            }
        } catch (SQLException e) {
            System.err.println("ERRO (CRIAR BANCO DE DADOS): " + e.getMessage());
        }
        return flag;
    }

    protected static boolean usar_banco_de_dados() {
        boolean flag = false;
        try {
            if (!conexao.isClosed()) {
                Statement statement = conexao.createStatement();
                String sql = "USE " + banco_de_dados + ";";
                statement.execute(sql);
                flag = true;
            }
        } catch (SQLException e) {
            System.err.println("ERRO (USAR BANCO DE DADOS): " + e.getMessage());
        }
        return flag;
    }
}
