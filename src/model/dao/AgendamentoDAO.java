package model.dao;

import java.util.List;
import java.util.ArrayList;

import model.Agendamento;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.Time;

import model.Cliente;
import model.Servico;

/**
 *
 * @author Gabriel Braga
 */
public abstract class AgendamentoDAO {

    private static boolean criar_tabela_agendamento() {
        boolean flag = false;
        try {
            if (ClienteDAO.criar_tabela_cliente() && ServicoDAO.criar_tabela_servico()) {
                String sql
                        = "CREATE TABLE IF NOT EXISTS agendamento("
                        + "AGENDAMENTO_ID INT,"
                        + "AGENDAMENTO_CLIENTE INT NOT NULL,"
                        + "AGENDAMENTO_SERVICO INT NOT NULL,"
                        + "AGENDAMENTO_VALOR FLOAT,"
                        + "AGENDAMENTO_DATA DATE,"
                        + "AGENDAMENTO_HORA TIME,"
                        + "AGENDAMENTO_OBSERVACAO VARCHAR(100),"
                        + "PRIMARY KEY (AGENDAMENTO_ID),"
                        + "FOREIGN KEY (AGENDAMENTO_CLIENTE) REFERENCES cliente (CLIENTE_ID),"
                        + "FOREIGN KEY (AGENDAMENTO_SERVICO) REFERENCES servico (SERVICO_ID)"
                        + ")"
                        + "ENGINE=InnoDB;";
                FabricaDeConexao.conexao.createStatement().execute(sql);
                flag = true;
            }
        } finally {
            return flag;
        }
    }

    private static List<Agendamento> obter_lista_de_agendamentos(ResultSet resultados) throws SQLException {
        List<Agendamento> agendamentos = new ArrayList<Agendamento>();
        while (resultados.next()) {
            int id = resultados.getInt("AGENDAMENTO_ID");
            Cliente cliente = ClienteDAO.obter_cliente_por_id(resultados.getInt("AGENDAMENTO_CLIENTE")).get(0);
            Servico servico = ServicoDAO.obter_servico_por_id(resultados.getInt("AGENDAMENTO_SERVICO")).get(0);
            java.util.Date data = Datetime.getTimestamp(resultados.getDate("AGENDAMENTO_DATA"), resultados.getTime("AGENDAMENTO_HORA"));
            float valor = resultados.getFloat("AGENDAMENTO_VALOR");
            String observacao = resultados.getString("AGENDAMENTO_OBSERVACAO");
            agendamentos.add(new Agendamento(id, cliente, servico, valor, data, observacao));
        }
        return agendamentos;
    }

    public static boolean inserir_agendamento(Agendamento agendamento) {
        boolean flag = false;
        if (FabricaDeConexao.abrir_conexao()) {
            if (FabricaDeConexao.criar_banco_de_dados()) {
                if (FabricaDeConexao.usar_banco_de_dados()) {
                    if (criar_tabela_agendamento()) {
                        try {
                            String sql = "INSERT INTO agendamento (AGENDAMENTO_ID, AGENDAMENTO_CLIENTE, AGENDAMENTO_SERVICO, AGENDAMENTO_VALOR, AGENDAMENTO_DATA, AGENDAMENTO_HORA, AGENDAMENTO_OBSERVACAO) VALUES (?, ?, ?, ?, ?, ?, ?);";
                            PreparedStatement statement = FabricaDeConexao.conexao.prepareStatement(sql);
                            statement.setInt(1, agendamento.getId());
                            statement.setInt(2, agendamento.getCliente().getId());
                            statement.setInt(3, agendamento.getServico().getId());
                            statement.setFloat(4, agendamento.getServico().getValor());
                            statement.setDate(5, new Date(agendamento.getData().getTime()));
                            statement.setTime(6, new Time(agendamento.getData().getTime()));
                            statement.setString(7, agendamento.getObservacao());
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

    public static boolean atualizar_agendamento(Agendamento agendamento) {
        boolean flag = false;
        if (FabricaDeConexao.abrir_conexao()) {
            if (FabricaDeConexao.criar_banco_de_dados()) {
                if (FabricaDeConexao.usar_banco_de_dados()) {
                    if (criar_tabela_agendamento()) {
                        try {
                            String sql = "UPDATE cliente SET AGENDAMENTO_CLIENTE = ?, AGENDAMENTO_SERVICO = ?, AGENDAMENTO_VALOR = ?, AGENDAMENTO_DATA = ?, AGENDAMENTO_HORA = ?, AGENDAMENTO_OBSERVACAO = ? WHERE AGENDAMENTO_ID = ?;";
                            PreparedStatement statement = FabricaDeConexao.conexao.prepareStatement(sql);
                            statement.setInt(1, agendamento.getCliente().getId());
                            statement.setInt(2, agendamento.getServico().getId());
                            statement.setFloat(3, agendamento.getServico().getValor());
                            statement.setDate(4, new Date(agendamento.getData().getTime()));
                            statement.setTime(5, new Time(agendamento.getData().getTime()));
                            statement.setString(6, agendamento.getObservacao());
                            statement.setInt(7, agendamento.getId());
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

    public static boolean deletar_agendamento(Agendamento agendamento) {
        boolean flag = false;
        if (FabricaDeConexao.abrir_conexao()) {
            if (FabricaDeConexao.criar_banco_de_dados()) {
                if (FabricaDeConexao.usar_banco_de_dados()) {
                    if (criar_tabela_agendamento()) {
                        try {
                            String sql = "DELETE FROM agendamento WHERE id = ?;";
                            PreparedStatement statement = FabricaDeConexao.conexao.prepareStatement(sql);
                            statement.setInt(1, agendamento.getId());
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

    public static List<Agendamento> obter_todos_agendamentos() {
        List<Agendamento> usuarios = new ArrayList<Agendamento>();
        if (FabricaDeConexao.abrir_conexao()) {
            if (FabricaDeConexao.criar_banco_de_dados()) {
                if (FabricaDeConexao.usar_banco_de_dados()) {
                    if (criar_tabela_agendamento()) {
                        try {
                            String sql = "SELECT * FROM agendamento;";
                            ResultSet resultados = FabricaDeConexao.conexao.createStatement().executeQuery(sql);
                            usuarios = obter_lista_de_agendamentos(resultados);
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
