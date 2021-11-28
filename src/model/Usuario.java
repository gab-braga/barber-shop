package model;

import java.util.Date;

/**
 *
 * @author Gabriel Braga
 */
public class Usuario extends Pessoa {

    public final static String USUARIO = "usuário";
    public final static String ADMINISTRADOR = "administrador";

    private String senha;
    private String nivelAcesso;

    public Usuario(int id, String nome, char sexo, Date dataNascimento, String telefone, String email, String cpf, String senha, String nivelAcesso) {
        super(id, nome, sexo, dataNascimento, telefone, email, cpf);
        this.senha = senha;
        this.nivelAcesso = nivelAcesso;
    }

    public Usuario(String nome, char sexo, Date dataNascimento, String telefone, String email, String cpf, String senha, String nivelAcesso) {
        super(nome, sexo, dataNascimento, telefone, email, cpf);
        this.senha = senha;
        this.nivelAcesso = nivelAcesso;
    }

    public Usuario(int id, String nome, String senha) {
        super(id, nome);
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(String nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

}
