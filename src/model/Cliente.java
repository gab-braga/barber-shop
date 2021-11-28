package model;

import java.util.Date;

/**
 *
 * @author Gabriel Braga
 */
public class Cliente extends Pessoa {

    private String endereco;
    private String cep;

    public Cliente(int id, String nome, char sexo, Date dataNascimento, String telefone, String email, String cpf, String endereco, String cep) {
        super(id, nome, sexo, dataNascimento, telefone, email, cpf);
        this.endereco = endereco;
        this.cep = cep;
    }

    public Cliente(String nome, char sexo, Date dataNascimento, String telefone, String email, String cpf, String endereco, String cep) {
        super(nome, sexo, dataNascimento, telefone, email, cpf);
        this.endereco = endereco;
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return getNome();
    }

}
