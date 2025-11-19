package controller;
import java.util.Scanner;
import model.Carro;
import utils.Tools;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

public class CRUD {

    Scanner scan = new Scanner(System.in);

    // --------------------------------------------------CREATE
    public void createCarro(Map<String, Carro> mapaDeCarros) {
        
        
        LocalDate anoDeCadastro = LocalDate.now();
        System.out.println("Digite o modelo do carro:");
        String novoModelo = scan.nextLine();
        System.out.println("Digite a marca do carro:");
        String novaMarca = scan.nextLine();

        int novoAno = 0;
        boolean inputValido = false;

        while(!inputValido){
            System.out.println("Digite o ano do carro:");
            String anoString = scan.nextLine();

            try {
                novoAno = Integer.parseInt(anoString);
                inputValido = true;
            } catch (Exception e) {
                System.out.println("O ano deve ser um número!");
                scan.nextLine();
            }
        }

        Carro novoCarro = new Carro(novoModelo, novaMarca, novoAno, anoDeCadastro);
        mapaDeCarros.put(novoModelo, novoCarro);
        System.out.println("Carro cadastrado.");
        
        Tools.log("CARRO "+ novoModelo +" CRIADO");
    }

    // --------------------------------------------------READ
    
    public Optional<Carro> busca(String modeloBuscado, Map<String, Carro> mapaDeCarros){
        Carro carro = mapaDeCarros.get(modeloBuscado);
        return Optional.ofNullable(carro);
    }
    
    public void imprimirInfo(Carro carroAlvo) {
        String modelo = carroAlvo.getModelo();
        String marca = carroAlvo.getMarca();
        int ano = carroAlvo.getAno();
        LocalDate dataCadastro = carroAlvo.getDataCadastro();

        System.out.println("----- Informações do " + modelo + " -----");
        System.out.println("Modelo: " + modelo);
        System.out.println("Marca: " + marca);
        System.out.println("Ano: " + ano);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Data do cadastro: " + dataCadastro.format(formatter));
        System.out.println("-".repeat(33));
    }

    public void readCarro(Map<String, Carro> mapaDeCarros) {
        System.out.println("Digite o modelo do carro que deseja buscar:");
        String modeloBuscado = scan.nextLine();
        
        Optional<Carro> carro = this.busca(modeloBuscado, mapaDeCarros);

        carro.ifPresent(carroEncontrado -> {
            this.imprimirInfo(carroEncontrado);
            Tools.log("CARRO "+ carroEncontrado.getModelo() +" BUSCADO");

        });

        if(carro.isEmpty()){
            System.out.println("Carro não encontrado.");
            
            Tools.log("FALHA NO BUSCA DO CARRO");
        }
    }

    public void readTodosOsCarros(Map<String, Carro> mapaDeCarros) {
        System.out.println("---- Informações dos carros ----");
        mapaDeCarros.forEach((modelo, carro) -> System.out.println(carro + "\n" + "-".repeat(30)));
    
        Tools.log("TODOS OS CARROS BUSCADOS");
    }
    
    public void readCarrosPorMarca(Map<String, Carro> mapaDeCarros){
        System.out.println("Digite a marca dos carros que deseja buscar:");
        String marcaBuscada = scan.nextLine();
        
        mapaDeCarros.values().stream()
            .filter(c -> c.getMarca().equalsIgnoreCase(marcaBuscada))
            .forEach(System.out::println);

        Tools.log("CARROS DA MARCA "+ marcaBuscada +" BUSCADOS");
    }

    // --------------------------------------------------UPDATE
    public void updateCarro(Map<String, Carro> mapaDeCarros) {
        System.out.println("Digite o modelo do carro que deseja alterar:");
        String modeloBuscado = scan.nextLine();
        
        Optional<Carro> carro = this.busca(modeloBuscado, mapaDeCarros);

        carro.ifPresent(carroEncontrado -> {
            System.out.println("Digite o novo modelo\n(deixe vazio para não alterar):");
            String novoModelo = scan.nextLine();
            System.out.println("Digite a nova marca\n(deixe vazio para não alterar):");
            String novaMarca = scan.nextLine();
            System.out.println("Digite o novo ano\n(deixe vazio para não alterar):");
            String novoAnoStr = scan.nextLine();

            if (!novoModelo.isEmpty()) {
                carroEncontrado.setModelo(novoModelo);
            }
            if (!novaMarca.isEmpty()) {
                carroEncontrado.setMarca(novaMarca);
            }
            if (!novoAnoStr.isEmpty()) {
                try {
                    int novoAno = Integer.parseInt(novoAnoStr);
                    carroEncontrado.setAno(novoAno);
                } catch (NumberFormatException e) {
                    System.out.println("Ano inválido. O ano não foi alterado.");
                }
            }
            System.out.println("Carro atualizado com sucesso!");
            System.out.println("Novos dados: " + carroEncontrado);

            Tools.log("CARRO "+ carroEncontrado.getModelo() +" ALTERADO");
        });

        if(carro.isEmpty()){
            System.out.println("Carro não encontrado.");
            Tools.log("FALHA DA ALTERAÇÃO DO CARRO " + modeloBuscado);
        }
    }

    // --------------------------------------------------DELETE
    public void deleteCarro(Map<String, Carro> mapaDeCarros) {
        System.out.println("Digite o modelo do carro que deseja remover:");
        String modeloBuscado = scan.nextLine();
        
        Optional<Carro> carro = this.busca(modeloBuscado, mapaDeCarros);
        
        carro.ifPresent(carroEncontrado -> {
            mapaDeCarros.remove(modeloBuscado);
            System.out.println("Carro removido.");

            Tools.log("CARRO " + modeloBuscado + " REMOVIDO");
        });

        if(carro.isEmpty()){
            System.out.println("Carro não encontrado.");
            Tools.log("FALHA NA REMOÇÂO DO MODELO " + modeloBuscado);
        }
    }
}