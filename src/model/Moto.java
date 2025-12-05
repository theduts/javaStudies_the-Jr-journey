package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Moto extends Veiculo{
    String tipoMoto;
    int cilindrada;

    public Moto(String tipoMoto, int cilindrada, String modelo, String marca, int ano, LocalDate dataCadastro) {
        super(modelo, marca, ano, dataCadastro);
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
        //      tipo,        modelo,              marca,             ano,           tipoVeiculo,          dataDeCadastro,             cilindrada
        return "moto," + this.modelo + "," + this.marca + "," + this.ano + "," + this.tipoMoto + "," + this.dataCadastro + "," + this.cilindrada;
    }
}


