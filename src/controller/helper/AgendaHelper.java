package controller.helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import model.Agendamento;
import model.Cliente;
import model.Servico;
import view.Agenda;

/**
 *
 * @author Gabriel Braga
 */
public class AgendaHelper implements Helper {

    private final Agenda view;

    public AgendaHelper(Agenda view) {
        this.view = view;
    }

    public void preencher_tabela(List<Agendamento> agendamentos) {
        DefaultTableModel modelo_tabela = (DefaultTableModel) view.getTabela_agendamentos().getModel();
        modelo_tabela.setNumRows(0);
        for (Agendamento a : agendamentos) {
            modelo_tabela.addRow(new Object[]{
                a.getId(),
                a.getCliente().getNome(),
                a.getServico().getDescricao(),
                a.getValor(),
                obter_data_formatada(a.getData()),
                obter_hora_formatada(a.getData()),
                a.getObservacao()
            });
        }
    }

    public String obter_data_formatada(Date d) {
        return new SimpleDateFormat("dd/MM/yyyy").format(d);
    }

    public String obter_hora_formatada(Date d) {
        return new SimpleDateFormat("HH:mm").format(d);
    }

    public Date obter_data(String expressao) {
        Date data = null;
        try {
            data = new SimpleDateFormat("dd/MM/yyyy HH:mmm").parse(expressao);
        } finally {
            return data;
        }
    }

    public void preencher_campo_cliente(List<Cliente> clientes) {
        DefaultComboBoxModel combobox = (DefaultComboBoxModel) view.getCampo_cliente().getModel();
        for (Cliente c : clientes) {
            combobox.addElement(c);
        }
    }

    public void preencher_campo_servico(List<Servico> servicos) {
        DefaultComboBoxModel combobox = (DefaultComboBoxModel) view.getCampo_servico().getModel();
        for (Servico s : servicos) {
            combobox.addElement(s);
        }
    }

    public Servico obter_servico() {
        return (Servico) view.getCampo_servico().getSelectedItem();
    }

    public void setar_valor(float valor) {
        view.getCampo_valor().setText(String.valueOf(valor));
    }

    @Override
    public Agendamento obter_modelo() {
        Agendamento agendamento = null;
        try {
            int id = Integer.parseInt(view.getCampo_id().getText());
            Cliente cliente = (Cliente) view.getCampo_cliente().getSelectedItem();
            Servico servico = (Servico) view.getCampo_servico().getSelectedItem();
            float valor = Float.parseFloat(view.getCampo_valor().getText());
            Date data = obter_data(String.format("%s %s", view.getCampo_data().getText(), view.getCampo_hora().getText()));
            String observacao = view.getCampo_observacao().getText();
            agendamento = new Agendamento(id, cliente, servico, valor, data, observacao);
        } catch (Exception ex) {
            view.exibir_mesagem("Informações inválidas!");
        } finally {
            return agendamento;
        }
    }

    @Override
    public void limpar_campos() {
        view.getCampo_id().setText("");
        view.getCampo_data().setText("");
        view.getCampo_hora().setText("");
        view.getCampo_valor().setText("");
        view.getCampo_observacao().setText("");
    }

}
