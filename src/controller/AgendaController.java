package controller;

import controller.helper.AgendaHelper;
import java.util.List;
import model.Agendamento;
import model.Cliente;
import model.Servico;
import model.dao.AgendamentoDAO;
import model.dao.ClienteDAO;
import model.dao.ServicoDAO;
import view.Agenda;

/**
 *
 * @author Gabriel Braga
 */
public class AgendaController {

    private final Agenda view;
    private final AgendaHelper helper;

    public AgendaController(Agenda view) {
        this.view = view;
        this.helper = new AgendaHelper(view);
    }

    public void atualizar_tabela() {
        List<Agendamento> agendamentos = AgendamentoDAO.obter_todos_agendamentos();
        helper.preencher_tabela(agendamentos);
    }

    public void atualizar_campo_cliente() {
        List<Cliente> clientes = ClienteDAO.obter_todos_clientes();
        helper.preencher_campo_cliente(clientes);
    }

    public void atualizar_campo_servico() {
        List<Servico> servicos = ServicoDAO.obter_todos_servicos();
        helper.preencher_campo_servico(servicos);
    }

    public void atualizar_campo_valor() {
        Servico servico = helper.obter_servico();
        helper.setar_valor(servico.getValor());
    }

    public void agendar() {
        Agendamento agendamento = helper.obter_modelo();
        if (AgendamentoDAO.inserir_agendamento(agendamento)) {
            view.exibir_mesagem("Agendado!");
            helper.limpar_campos();
            atualizar_tabela();
        } else {
            view.exibir_mesagem("Não foi possível agendar!");
        }
    }

}
