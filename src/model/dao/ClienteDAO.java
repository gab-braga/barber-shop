package model.dao;

import java.util.List;
import java.util.ArrayList;

import model.Cliente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Date;

/**
 *
 * @author Gabriel Braga
 */
public abstract class ClienteDAO {

    protected static boolean criar_tabela_cliente() {
        boolean flag = false;
        try {
            String sql
                    = "CREATE TABLE IF NOT EXISTS cliente("
                    + "CLIENTE_ID INT AUTO_INCREMENT,"
                    + "CLIENTE_NOME VARCHAR(100),"
                    + "CLIENTE_SEXO VARCHAR(1),"
                    + "CLIENTE_DATA_NASC DATE,"
                    + "CLIENTE_TELEFONE VARCHAR(20),"
                    + "CLIENTE_EMAIL VARCHAR(80),"
                    + "CLIENTE_CPF VARCHAR(20),"
                    + "CLIENTE_ENDERECO VARCHAR(80),"
                    + "CLIENTE_CEP VARCHAR(20),"
                    + "PRIMARY KEY (CLIENTE_ID)"
                    + ")"
                    + "ENGINE=InnoDB;";
            FabricaDeConexao.conexao.createStatement().execute(sql);
            flag = true;
        } finally {
            return flag;
        }
    }

    private static List<Cliente> obter_lista_de_clientes(ResultSet resultados) throws SQLException {
        List<Cliente> clientes = new ArrayList<Cliente>();
        while (resultados.next()) {
            int id = resultados.getInt("CLIENTE_ID");
            String nome = resultados.getString("CLIENTE_NOME");
            char sexo = resultados.getString("CLIENTE_SEXO").charAt(0);
            java.util.Date dataNascimento = Datetime.getDate(resultados.getDate("CLIENTE_DATA_NASC"));
            String telefone = resultados.getString("CLIENTE_TELEFONE");
            String email = resultados.getString("CLIENTE_EMAIL");
            String cpf = resultados.getString("CLIENTE_CPF");
            String endereco = resultados.getString("CLIENTE_ENDERECO");
            String cep = resultados.getString("CLIENTE_CEP");
            clientes.add(new Cliente(id, nome, sexo, dataNascimento, telefone, email, cpf, endereco, cep));
        }
        return clientes;
    }

    public static boolean inserir_cliente(Cliente cliente) {
        boolean flag = false;
        if (FabricaDeConexao.abrir_conexao()) {
            if (FabricaDeConexao.criar_banco_de_dados()) {
                if (FabricaDeConexao.usar_banco_de_dados()) {
                    if (criar_tabela_cliente()) {
                        try {
                            String sql = "INSERT INTO cliente (CLIENTE_NOME, CLIENTE_SEXO, CLIENTE_DATA_NASC, CLIENTE_TELEFONE, CLIENTE_EMAIL, CLIENTE_CPF, CLIENTE_ENDERECO, CLIENTE_CEP) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
                            PreparedStatement statement = FabricaDeConexao.conexao.prepareStatement(sql);
                            statement.setString(1, cliente.getNome());
                            statement.setString(2, String.valueOf(cliente.getSexo()));
                            statement.setDate(3, new Date(cliente.getDataNascimento().getTime()));
                            statement.setString(4, cliente.getTelefone());
                            statement.setString(5, cliente.getEmail());
                            statement.setString(6, cliente.getCpf());
                            statement.setString(7, cliente.getEndereco());
                            statement.setString(8, cliente.getCep());
                            statement.executeUpdate();
                            flag = true;
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
            FabricaDeConexao.fechar_conexao();
        }
        return flag;
    }

    public static boolean atualizar_cliente(Cliente cliente) {
        boolean flag = false;
        if (FabricaDeConexao.abrir_conexao()) {
            if (FabricaDeConexao.criar_banco_de_dados()) {
                if (FabricaDeConexao.usar_banco_de_dados()) {
                    if (criar_tabela_cliente()) {
                        try {
                            String sql = "UPDATE cliente SET CLIENTE_NOME = ?, CLIENTE_SEXO = ?, CLIENTE_DATA_NASC = ?, CLIENTE_TELEFONE = ?, CLIENTE_EMAIL = ?, CLIENTE_CPF = ?, CLIENTE_ENDERECO = ?, CLIENTE_CEP = ? WHERE CLIENTE_ID = ?;";
                            PreparedStatement statement = FabricaDeConexao.conexao.prepareStatement(sql);
                            statement.setString(1, cliente.getNome());
                            statement.setString(2, String.valueOf(cliente.getSexo()));
                            statement.setDate(3, new Date(cliente.getDataNascimento().getTime()));
                            statement.setString(4, cliente.getTelefone());
                            statement.setString(5, cliente.getEmail());
                            statement.setString(6, cliente.getCpf());
                            statement.setString(7, cliente.getEndereco());
                            statement.setString(8, cliente.getCep());
                            statement.setInt(9, cliente.getId());
                            statement.executeUpdate();
                            flag = true;
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
            FabricaDeConexao.fechar_conexao();
        }
        return flag;
    }

    public static boolean deletar_cliente(Cliente cliente) {
        boolean flag = false;
        if (FabricaDeConexao.abrir_conexao()) {
            if (FabricaDeConexao.criar_banco_de_dados()) {
                if (FabricaDeConexao.usar_banco_de_dados()) {
                    if (criar_tabela_cliente()) {
                        try {
                            String sql = "DELETE FROM cliente WHERE id = ?;";
                            PreparedStatement statement = FabricaDeConexao.conexao.prepareStatement(sql);
                            statement.setInt(1, cliente.getId());
                            statement.executeUpdate();
                            flag = true;
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
            FabricaDeConexao.fechar_conexao();
        }
        return flag;
    }

    public static List<Cliente> obter_todos_clientes() {
        List<Cliente> usuarios = new ArrayList<Cliente>();
        if (FabricaDeConexao.abrir_conexao()) {
            if (FabricaDeConexao.criar_banco_de_dados()) {
                if (FabricaDeConexao.usar_banco_de_dados()) {
                    if (criar_tabela_cliente()) {
                        try {
                            String sql = "SELECT * FROM cliente;";
                            ResultSet resultados = FabricaDeConexao.conexao.createStatement().executeQuery(sql);
                            usuarios = obter_lista_de_clientes(resultados);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
            FabricaDeConexao.fechar_conexao();
        }
        return usuarios;
    }

    public static List<Cliente> obter_cliente_por_id(int id) {
        List<Cliente> clientes = new ArrayList<Cliente>();
        if (FabricaDeConexao.abrir_conexao()) {
            if (FabricaDeConexao.criar_banco_de_dados()) {
                if (FabricaDeConexao.usar_banco_de_dados()) {
                    if (criar_tabela_cliente()) {
                        try {
                            String sql = "SELECT * FROM cliente WHERE CLIENTE_ID = " + id + ";";
                            ResultSet resultados = FabricaDeConexao.conexao.createStatement().executeQuery(sql);
                            clientes = obter_lista_de_clientes(resultados);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
            FabricaDeConexao.fechar_conexao();
        }
        return clientes;
    }
}
