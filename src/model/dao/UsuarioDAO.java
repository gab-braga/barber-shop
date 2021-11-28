package model.dao;

import java.util.List;
import java.util.ArrayList;

import model.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Date;

/**
 *
 * @author Gabriel Braga
 */
public abstract class UsuarioDAO {

    private static boolean criar_tabela_usuario() {
        boolean flag = false;
        try {
            String sql
                    = "CREATE TABLE IF NOT EXISTS usuario("
                    + "USUARIO_ID INT AUTO_INCREMENT,"
                    + "USUARIO_NOME VARCHAR(100),"
                    + "USUARIO_SEXO VARCHAR(1),"
                    + "USUARIO_DATA_NASC DATE,"
                    + "USUARIO_TELEFONE VARCHAR(20),"
                    + "USUARIO_EMAIL VARCHAR(80),"
                    + "USUARIO_CPF VARCHAR(20),"
                    + "USUARIO_SENHA VARCHAR(20),"
                    + "USUARIO_NIVEL_ACESSO VARCHAR(20),"
                    + "PRIMARY KEY (USUARIO_ID)"
                    + ")"
                    + "ENGINE=InnoDB;";
            FabricaDeConexao.conexao.createStatement().execute(sql);
            flag = true;
        } finally {
            return flag;
        }
    }

    private static List<Usuario> obter_lista_de_usuarios(ResultSet resultados) throws SQLException {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        while (resultados.next()) {
            int id = resultados.getInt("USUARIO_ID");
            String nome = resultados.getString("USUARIO_NOME");
            char sexo = resultados.getString("USUARIO_SEXO").charAt(0);
            java.util.Date dataNascimento = Datetime.getDate(resultados.getDate("USUARIO_DATA_NASC"));
            String telefone = resultados.getString("USUARIO_TELEFONE");
            String email = resultados.getString("USUARIO_EMAIL");
            String cpf = resultados.getString("USUARIO_CPF");
            String senha = resultados.getString("USUARIO_SENHA");
            String nivelAcesso = resultados.getString("USUARIO_NIVEL_ACESSO");
            usuarios.add(new Usuario(id, nome, sexo, dataNascimento, telefone, email, cpf, senha, nivelAcesso));
        }
        return usuarios;
    }

    public static boolean inserir_usuario(Usuario usuario) {
        boolean flag = false;
        if (FabricaDeConexao.abrir_conexao()) {
            if (FabricaDeConexao.criar_banco_de_dados()) {
                if (FabricaDeConexao.usar_banco_de_dados()) {
                    if (criar_tabela_usuario()) {
                        try {
                            String sql = "INSERT INTO usuario (USUARIO_NOME, USUARIO_SEXO, USUARIO_DATA_NASC, USUARIO_TELEFONE, USUARIO_EMAIL, USUARIO_CPF, USUARIO_SENHA, USUARIO_NIVEL_ACESSO) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
                            PreparedStatement statement = FabricaDeConexao.conexao.prepareStatement(sql);
                            statement.setString(1, usuario.getNome());
                            statement.setString(2, String.valueOf(usuario.getSexo()));
                            statement.setDate(3, new Date(usuario.getDataNascimento().getTime()));
                            statement.setString(4, usuario.getTelefone());
                            statement.setString(5, usuario.getEmail());
                            statement.setString(6, usuario.getCpf());
                            statement.setString(7, usuario.getSenha());
                            statement.setString(8, usuario.getNivelAcesso());
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

    public static boolean atualizar_usuario(Usuario usuario) {
        boolean flag = false;
        if (FabricaDeConexao.abrir_conexao()) {
            if (FabricaDeConexao.criar_banco_de_dados()) {
                if (FabricaDeConexao.usar_banco_de_dados()) {
                    if (criar_tabela_usuario()) {
                        try {
                            String sql = "UPDATE usuario SET USUARIO_NOME = ?, USUARIO_SEXO = ?, USUARIO_DATA_NASC = ?, USUARIO_TELEFONE = ?, USUARIO_EMAIL = ?, USUARIO_CPF = ?, USUARIO_SENHA = ?, USUARIO_NIVEL_ACESSO = ? WHERE USUARIO_ID = ?;";
                            PreparedStatement statement = FabricaDeConexao.conexao.prepareStatement(sql);
                            statement.setString(1, usuario.getNome());
                            statement.setString(2, String.valueOf(usuario.getSexo()));
                            statement.setDate(3, new Date(usuario.getDataNascimento().getTime()));
                            statement.setString(4, usuario.getTelefone());
                            statement.setString(5, usuario.getEmail());
                            statement.setString(6, usuario.getCpf());
                            statement.setString(7, usuario.getSenha());
                            statement.setString(8, usuario.getNivelAcesso());
                            statement.setInt(9, usuario.getId());
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

    public static boolean deletar_usuario(Usuario usuario) {
        boolean flag = false;
        if (FabricaDeConexao.abrir_conexao()) {
            if (FabricaDeConexao.criar_banco_de_dados()) {
                if (FabricaDeConexao.usar_banco_de_dados()) {
                    if (criar_tabela_usuario()) {
                        try {
                            String sql = "DELETE FROM usuario WHERE USUARIO_ID = ?;";
                            PreparedStatement statement = FabricaDeConexao.conexao.prepareStatement(sql);
                            statement.setInt(1, usuario.getId());
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

    public static List<Usuario> obter_todos_usuarios() {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        if (FabricaDeConexao.abrir_conexao()) {
            if (FabricaDeConexao.criar_banco_de_dados()) {
                if (FabricaDeConexao.usar_banco_de_dados()) {
                    if (criar_tabela_usuario()) {
                        try {
                            String sql = "SELECT * FROM usuario;";
                            ResultSet resultados = FabricaDeConexao.conexao.createStatement().executeQuery(sql);
                            usuarios = obter_lista_de_usuarios(resultados);
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

    public static List<Usuario> obter_usuario(Usuario usuario) {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        if (FabricaDeConexao.abrir_conexao()) {
            if (FabricaDeConexao.criar_banco_de_dados()) {
                if (FabricaDeConexao.usar_banco_de_dados()) {
                    if (criar_tabela_usuario()) {
                        try {
                            String sql = "SELECT * FROM usuario WHERE USUARIO_NOME = '" + usuario.getNome() + "' AND USUARIO_SENHA = '" + usuario.getSenha() + "';";
                            ResultSet resultados = FabricaDeConexao.conexao.createStatement().executeQuery(sql);
                            usuarios = obter_lista_de_usuarios(resultados);
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

    public static List<Usuario> obter_usuario_por_id(int id) {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        if (FabricaDeConexao.abrir_conexao()) {
            if (FabricaDeConexao.criar_banco_de_dados()) {
                if (FabricaDeConexao.usar_banco_de_dados()) {
                    if (criar_tabela_usuario()) {
                        try {
                            String sql = "SELECT * FROM usuario WHERE id = " + id + ";";
                            ResultSet resultados = FabricaDeConexao.conexao.createStatement().executeQuery(sql);
                            usuarios = obter_lista_de_usuarios(resultados);
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

}
