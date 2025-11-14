package model;

public class Carro {

    String modelo;
    String marca;
    int ano;

    // -----------------------------------SETTER
    public Carro(String modelo, String marca, int ano) {
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
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

    // -----------------------------------MÉTODOS
    public void metodoAcelerar() {
        System.out.println(this.modelo + " acelerando");
    }

    public void metodoFreiar() {
        System.out.println(this.modelo + " freiando");
    }

    public void metodoTrocarMarcha() {
        System.out.println(this.modelo + " trocando marcha");
    }

    // POLIMORFISMO DA toString() PARA AUXILIAR O println
    @Override
    public String toString() {
        return "Modelo: " + this.modelo + "\nMarca: " + this.marca + "\nAno: " + this.ano;
    }
}