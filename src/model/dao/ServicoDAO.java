package model.dao;

import java.util.List;
import java.util.ArrayList;

import model.Servico;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Date;

/**
 *
 * @author Gabriel Braga
 */
public abstract class ServicoDAO {

    protected static boolean criar_tabela_servico() {
        boolean flag = false;
        try {
            String sql
                    = "CREATE TABLE IF NOT EXISTS servico("
                    + "SERVICO_ID INT AUTO_INCREMENT,"
                    + "SERVICO_DESCRICAO VARCHAR(100),"
                    + "SERVICO_VALOR FLOAT,"
                    + "PRIMARY KEY (SERVICO_ID)"
                    + ")"
                    + "ENGINE=InnoDB;";
            FabricaDeConexao.conexao.createStatement().execute(sql);
            flag = true;
        } finally {
            return flag;
        }
    }

    private static List<Servico> obter_lista_de_servicos(ResultSet resultados) throws SQLException {
        List<Servico> servicos = new ArrayList<Servico>();
        while (resultados.next()) {
            int id = resultados.getInt("SERVICO_ID");
            String descricao = resultados.getString("SERVICO_DESCRICAO");
            float valor = resultados.getFloat("SERVICO_VALOR");
            servicos.add(new Servico(id, descricao, valor));
        }
        return servicos;
    }

    public static boolean inserir_servico(Servico servico) {
        boolean flag = false;
        if (FabricaDeConexao.abrir_conexao()) {
            if (FabricaDeConexao.criar_banco_de_dados()) {
                if (FabricaDeConexao.usar_banco_de_dados()) {
                    if (criar_tabela_servico()) {
                        try {
                            String sql = "INSERT INTO servico (SERVICO_DESCRICAO, SERVICO_VALOR) VALUES (?, ?);";
                            PreparedStatement statement = FabricaDeConexao.conexao.prepareStatement(sql);
                            statement.setString(1, servico.getDescricao());
                            statement.setFloat(2, servico.getValor());
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

    public static boolean atualizar_servico(Servico servico) {
        boolean flag = false;
        if (FabricaDeConexao.abrir_conexao()) {
            if (FabricaDeConexao.criar_banco_de_dados()) {
                if (FabricaDeConexao.usar_banco_de_dados()) {
                    if (criar_tabela_servico()) {
                        try {
                            String sql = "UPDATE servico SET SERVICO_DESCRICAO = ?, SERVICO_VALOR = ? WHERE SERVICO_ID = ?;";
                            PreparedStatement statement = FabricaDeConexao.conexao.prepareStatement(sql);
                            statement.setString(1, servico.getDescricao());
                            statement.setFloat(2, servico.getValor());
                            statement.setInt(3, servico.getId());
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

    public static boolean deletar_servico(Servico servico) {
        boolean flag = false;
        if (FabricaDeConexao.abrir_conexao()) {
            if (FabricaDeConexao.criar_banco_de_dados()) {
                if (FabricaDeConexao.usar_banco_de_dados()) {
                    if (criar_tabela_servico()) {
                        try {
                            String sql = "DELETE FROM servico WHERE id = ?;";
                            PreparedStatement statement = FabricaDeConexao.conexao.prepareStatement(sql);
                            statement.setInt(1, servico.getId());
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

    public static List<Servico> obter_todos_servicos() {
        List<Servico> usuarios = new ArrayList<Servico>();
        if (FabricaDeConexao.abrir_conexao()) {
            if (FabricaDeConexao.criar_banco_de_dados()) {
                if (FabricaDeConexao.usar_banco_de_dados()) {
                    if (criar_tabela_servico()) {
                        try {
                            String sql = "SELECT * FROM servico;";
                            ResultSet resultados = FabricaDeConexao.conexao.createStatement().executeQuery(sql);
                            usuarios = obter_lista_de_servicos(resultados);
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

    public static List<Servico> obter_servico_por_id(int id) {
        List<Servico> servicos = new ArrayList<Servico>();
        if (FabricaDeConexao.abrir_conexao()) {
            if (FabricaDeConexao.criar_banco_de_dados()) {
                if (FabricaDeConexao.usar_banco_de_dados()) {
                    if (criar_tabela_servico()) {
                        try {
                            String sql = "SELECT * FROM servico WHERE SERVICO_ID = " + id + ";";
                            ResultSet resultados = FabricaDeConexao.conexao.createStatement().executeQuery(sql);
                            servicos = obter_lista_de_servicos(resultados);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
            FabricaDeConexao.fechar_conexao();
        }
        return servicos;
    }
}
