package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Caminhao extends Veiculo{
    String tipoCaminhao;

    public Caminhao(String tipoCaminhao, String modelo, String marca, int ano, LocalDate dataCadastro) {
        super(modelo, marca, ano, dataCadastro);
        this.tipoCaminhao = tipoCaminhao;
    }

    public String getTipoCaminhao(){return this.tipoCaminhao;}

    public void setTipoCaminhao(String tipoCaminhao){this.tipoCaminhao = tipoCaminhao;}

    // POLIMORFISMO DA toString() PARA AUXILIAR O println
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Modelo: " + this.modelo + "\nMarca: " + this.marca + "\nAno: " + this.ano + "\nTipo: "+ this.tipoCaminhao + "\nData de cadastro: " + this.dataCadastro.format(formatter);
    }

    public String toFileString() {
        //      tipo,             modelo,             marca,             ano,             tipoVeiculo,        dataDeCadastro
        return "caminhao," + this.modelo + "," + this.marca + "," + this.ano + "," + this.tipoCaminhao + "," + this.dataCadastro + ",";
    }
}
