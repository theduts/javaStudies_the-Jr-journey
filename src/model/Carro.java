package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Carro extends Veiculo{
    
    String tipoCarro;

    public Carro(String tipoCarro, String modelo, String marca, int ano, LocalDate dataCadastro) {
        super(modelo, marca, ano, dataCadastro);
        this.tipoCarro = tipoCarro;
    }

    public String getTipoCarro(){return this.tipoCarro;}

    public void setTipoCarro(String tipoCarro){this.tipoCarro = tipoCarro;}

    // POLIMORFISMO DA toString() PARA AUXILIAR O println
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Modelo: " + this.modelo + "\nMarca: " + this.marca + "\nAno: " + this.ano + "\nTipo: "+ this.tipoCarro + "\nData de cadastro: " + this.dataCadastro.format(formatter);
    }

    public String toFileString() {
        // Retorna uma string simples no formato CSV - Ex: "Fusca,Volkswagen,1996,2025-11-17"
        return "carro," + this.modelo + "," + this.marca + "," + this.ano + "," + this.tipoCarro + "," + this.dataCadastro + ",";
    }
}