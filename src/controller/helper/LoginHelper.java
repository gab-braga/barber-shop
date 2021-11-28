package controller.helper;

import model.Usuario;
import view.Login;

/**
 *
 * @author Gabriel Braga
 */
public class LoginHelper implements Helper {

    private final Login view;

    public LoginHelper(Login view) {
        this.view = view;
    }

    public void setar_modelo_usuario(Usuario modelo) {
        String nome = modelo.getNome();
        String senha = modelo.getSenha();
        this.view.getCampoUsuario().setText(nome);
        this.view.getCampoSenha().setText(senha);
    }

    @Override
    public Usuario obter_modelo() {
        String nome = this.view.getCampoUsuario().getText();
        String senha = this.view.getCampoSenha().getText();
        return new Usuario(0, nome, senha);
    }

    @Override
    public void limpar_campos() {
        this.view.getCampoUsuario().setText("");
        this.view.getCampoSenha().setText("");
    }
}
