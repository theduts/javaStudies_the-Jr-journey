package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Carro {

    String modelo;
    String marca;
    int ano;
    LocalDate dataCadastro;

    // -----------------------------------SETTER
    public Carro(String modelo, String marca, int ano, LocalDate dataCadastro) {
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

    // -----------------------------------MÉTODOS

    // POLIMORFISMO DA toString() PARA AUXILIAR O println
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Modelo: " + this.modelo + "\nMarca: " + this.marca + "\nAno: " + this.ano + "\nData de cadastro: " + this.dataCadastro.format(formatter);
    }

    public String toFileString() {
        // Retorna uma string simples no formato CSV - Ex: "Fusca,Volkswagen,1996,2025-11-17"
        return this.modelo + "," + this.marca + "," + this.ano + "," + this.dataCadastro;
    }
}