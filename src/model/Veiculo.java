package model;

import java.time.LocalDate;

public abstract class Veiculo {
    String tipoVeiculo;
    String modelo;
    String marca;
    int ano;
    LocalDate dataCadastro;

    // -----------------------------------SETTER
    public Veiculo(String modelo, String marca, int ano, LocalDate dataCadastro) {
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.dataCadastro = dataCadastro;
    }

    public void setModelo(String novoModelo) {
        this.modelo = novoModelo;
    }

    public void setMarca(String novaMarca) {
        this.marca = novaMarca;
    }

    public void setAno(int novoAno) {
        this.ano = novoAno;
    }

    public void setDataCadastro(LocalDate novoDataCadastro) {
        this.dataCadastro = novoDataCadastro;
    }

    // -----------------------------------GETTERS
    public String getModelo() {
        return this.modelo;
    }

    public String getMarca() {
        return this.marca;
    }

    public int getAno() {
        return this.ano;
    }

    public LocalDate getDataCadastro() {
        return this.dataCadastro;
    }

    //-------------------------------------MÉTODOS

    public abstract String toFileString();

}
