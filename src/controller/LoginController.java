package controller;

import controller.helper.LoginHelper;
import java.util.List;
import model.dao.UsuarioDAO;
import model.Usuario;
import view.Login;
import view.MenuPrincipal;

/**
 *
 * @author Gabriel Braga
 */
public class LoginController {

    private final Login view;
    private final LoginHelper helper;

    public LoginController(Login view) {
        this.view = view;
        this.helper = new LoginHelper(view);
    }

    public void entrar_no_sistema() {
        Usuario usuario = helper.obter_modelo();
        if (UsuarioDAO.obter_usuario(usuario).size() > 0) {
            MenuPrincipal menu_principal = new MenuPrincipal();
            menu_principal.setVisible(true);
            this.view.dispose();
        } else {
            view.exibir_mesagem("Usuário ou senha inválido!");
        }
    }
}
