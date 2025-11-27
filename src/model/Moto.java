package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Moto extends Veiculo{
    String tipoMoto;
    int cilindrada;

    public Moto(String tipoMoto, int cilindrada, String modelo, String marca, int ano, LocalDate dataCadastro) {
        super(modelo, marca, ano, dataCadastro);
        this.tipoVeiculo = "Moto";
        this.tipoMoto = tipoMoto;
        this.cilindrada = cilindrada;
    }

    public String getTipoMoto(){return this.tipoMoto;}
    public void setTipoMoto(String tipoMoto){this.tipoMoto = tipoMoto;}

    public int getCilindrada(){return this.cilindrada;}
    public void setCilindrada(int cilindrada){this.cilindrada = cilindrada;}

    // POLIMORFISMO DA toString() PARA AUXILIAR O println
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Modelo: " + this.modelo + "\nCilindrada: " + this.cilindrada + "\nMarca: " + this.marca + "\nAno: " + this.ano + "\nData de cadastro: " + this.dataCadastro.format(formatter);
    }

    public String toFileString() {
        // Retorna uma string simples no formato CSV - Ex: "Fusca,Volkswagen,1996,2025-11-17"
        return this.tipoVeiculo + "," +this.modelo + "," + this.marca + "," + this.ano + "," + this.dataCadastro + "," + this.cilindrada + ",";
    }
}


//tipo,modelo,marca,ano,tipoVeiculo,dataDeCadastro,cilindrada