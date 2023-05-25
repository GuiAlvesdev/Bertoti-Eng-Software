package br.com.guialvedev.project.bertoti.model;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Email
    private String email;

    @Size(min = 3, max = 20, message = "usuario deve conter entre 3 e 20 caracteres")
    private String nomedeusuario;
    private String senha;


    public Usuario(Long id, String email, String nomedeusuario, String senha) {
        this.id = id;
        this.email = email;
        this.nomedeusuario = nomedeusuario;
        this.senha = senha;
    }

    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomedeusuario() {
        return nomedeusuario;
    }

    public void setNomedeusuario(String nomedeusuario) {
        this.nomedeusuario = nomedeusuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


}
