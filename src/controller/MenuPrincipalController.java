package controller;

import controller.helper.MenuPrincipalHelper;
import view.Agenda;
import view.MenuPrincipal;

/**
 *
 * @author Gabriel Braga
 */
public class MenuPrincipalController {

    private final MenuPrincipal view;
    private final MenuPrincipalHelper helper;

    public MenuPrincipalController(MenuPrincipal view) {
        this.view = view;
        this.helper = new MenuPrincipalHelper(view);
    }

    public void navegar_para_agenda() {
        Agenda agenda = new Agenda();
        agenda.setVisible(true);
    }

}
