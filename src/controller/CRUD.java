package controller;
import java.util.Scanner;
import exceptions.CarroNaoEncontradoException;
import model.Carro;

import java.util.Map;

public class CRUD {

    Scanner scan = new Scanner(System.in);

    // --------------------------------------------------CREATE
    public void createCarro(Map<String, Carro> mapaDeCarros) {
        System.out.println("Digite o modelo do carro:");
        String novoModelo = scan.nextLine();
        System.out.println("Digite a marca do carro:");
        String novaMarca = scan.nextLine();
        System.out.println("Digite o ano do carro:");
        int novoAno = scan.nextInt();
        scan.nextLine(); // Consome o "Enter" que o nextInt() deixou

        Carro novoCarro = new Carro(novoModelo, novaMarca, novoAno);
        mapaDeCarros.put(novoModelo, novoCarro);
        System.out.println("Carro cadastrado.");
    }

    // --------------------------------------------------READ
    
    public Carro busca(String modeloBuscado, Map<String, Carro> mapaDeCarros) throws CarroNaoEncontradoException {

        Carro carro = mapaDeCarros.get(modeloBuscado);

        if (carro == null) {
            throw new CarroNaoEncontradoException(); 
        }
        return carro;
    }
    
    public void imprimirInfo(Carro carroAlvo) {
        String modelo = carroAlvo.getModelo();
        String marca = carroAlvo.getMarca();
        int ano = carroAlvo.getAno();

        System.out.println("----- Informações do " + modelo + " -----");
        System.out.println("Modelo: " + modelo);
        System.out.println("Marca: " + marca);
        System.out.println("Ano: " + ano);
        System.out.println("-".repeat(33));
    }

    public void readCarro(Map<String, Carro> mapaDeCarros) {
        System.out.println("Digite o modelo do carro que deseja buscar:");
        String modeloBuscado = scan.nextLine();
        
        try {
            Carro carroEncontrado = this.busca(modeloBuscado, mapaDeCarros);
            this.imprimirInfo(carroEncontrado);

        } catch (CarroNaoEncontradoException e) {
            System.out.println("Carro não encontrado.");
        }
    }

    public void readTodosOsCarros(Map<String, Carro> mapaDeCarros) {
        System.out.println("---- Informações dos carros ----");
        mapaDeCarros.forEach((modelo, carro) -> System.out.println(carro + "\n" + "-".repeat(30)));
    }
    
    public void readCarrosPorMarca(Map<String, Carro> mapaDeCarros){
        System.out.println("Digite a marca dos carros que deseja buscar:");
        String marcaBuscada = scan.nextLine();
        
        mapaDeCarros.values().stream()
            .filter(c -> c.getMarca().equalsIgnoreCase(marcaBuscada))
            .forEach(System.out::println);
    }

    // --------------------------------------------------UPDATE
    public void updateCarro(Map<String, Carro> mapaDeCarros) {
        System.out.println("Digite o modelo do carro que deseja alterar:");
        String modeloBuscado = scan.nextLine();
        
        try {
            Carro setCarro = this.busca(modeloBuscado, mapaDeCarros);
            
            System.out.println("Digite o novo modelo\n(deixe vazio para não alterar):");
            String novoModelo = scan.nextLine();
            System.out.println("Digite a nova marca\n(deixe vazio para não alterar):");
            String novaMarca = scan.nextLine();
            System.out.println("Digite o novo ano\n(deixe vazio para não alterar):");
            String novoAnoStr = scan.nextLine();

            if (!novoModelo.isEmpty()) {
                setCarro.setModelo(novoModelo);
            }
            if (!novaMarca.isEmpty()) {
                setCarro.setMarca(novaMarca);
            }
            if (!novoAnoStr.isEmpty()) {
                try {
                    int novoAno = Integer.parseInt(novoAnoStr);
                    setCarro.setAno(novoAno);
                } catch (NumberFormatException e) {
                    System.out.println("Ano inválido. O ano não foi alterado.");
                }
            }
            System.out.println("Carro atualizado com sucesso!");
            System.out.println("Novos dados: " + setCarro);

        } catch (CarroNaoEncontradoException e) {
            System.out.println("Carro não encontrado.");
        }
    }

    // --------------------------------------------------DELETE
    public void deleteCarro(Map<String, Carro> mapaDeCarros) {
        System.out.println("Digite o modelo do carro que deseja remover:");
        String modeloBuscado = scan.nextLine();
        
        try {
            this.busca(modeloBuscado, mapaDeCarros);

            mapaDeCarros.remove(modeloBuscado);
            System.out.println("Carro removido.");

        } catch (CarroNaoEncontradoException e) {
            System.out.println("Carro não encontrado.");
        }

    }
}