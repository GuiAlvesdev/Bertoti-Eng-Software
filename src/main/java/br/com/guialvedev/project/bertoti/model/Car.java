package br.com.guialvedev.project.bertoti.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "marca")
    @NotBlank(message = " campo obrigatorio")
    private String marca;

    @Column(name = "modelo")
    @NotNull(message = "modelo nao pode ser nulo")
    private String modelo;

    @Column(name = "placa")
    @Size(min = 8, message = "a placa deve conter 8 caracteres")
    @NotBlank(message = " campo obrigatorio")
    private String placa;

    @Column(name = "cor")
    private String cor;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }






}
