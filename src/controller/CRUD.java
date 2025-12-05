package controller;
import java.util.Scanner;

import model.Caminhao;
import model.Carro;
import model.Moto;
import model.Veiculo;
import utils.Tools;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;

public class CRUD{

    private HashMap<String, Veiculo> veiculos;

    private final Scanner scan;

    public CRUD(Scanner injectedScan, HashMap<String, Veiculo> mapaDeVeiculos){
        this.scan = injectedScan;
        this.veiculos = mapaDeVeiculos;
    }

    // --------------------------------------------------CREATE
    public void createVeiculo() {
        
        
        LocalDate anoDeCadastro = LocalDate.now();
        System.out.println("Tipo do veículo\nCarro (A)\nMoto (B)\nCaminhão (C)(OPÇÃO NÃO ADICIONADA)");
        String tipo = scan.nextLine();
        System.out.println("Digite o modelo:");
        String novoModelo = scan.nextLine();
        System.out.println("Digite o tipo:");
        String novoTipoVeiculo = scan.nextLine();
        System.out.println("Digite a marca:");
        String novaMarca = scan.nextLine();

        int novoAno = 0;
        boolean inputAnoValido = false;

        while(!inputAnoValido){
            System.out.println("Digite o ano do veículo:");
            String anoString = scan.nextLine();

            try {
                novoAno = Integer.parseInt(anoString);
                inputAnoValido = true;
            } catch (Exception e) {
                System.out.println("O ano deve ser um número!");
                scan.nextLine();
            }
        }

        int novoCilindrada = 0;
        if(tipo.equalsIgnoreCase("B")){
            boolean inputCilindradaValido = false;

            while(!inputCilindradaValido){
                System.out.println("Digite a cilindrada da moto:");
                String cilindradaString = scan.nextLine();

                try {
                    novoCilindrada = Integer.parseInt(cilindradaString);
                    inputCilindradaValido = true;
                } catch (Exception e) {
                    System.out.println("O ano deve ser um número!");
                    scan.nextLine();
                }
            }

            Moto novaMoto = new Moto(tipo, novoCilindrada, novoModelo, novaMarca, novoAno, anoDeCadastro);
            this.veiculos.put(novoModelo, novaMoto);

        }else if(tipo.equalsIgnoreCase("A")){

            Carro novoCarro = new Carro(novoTipoVeiculo, novoModelo, novaMarca, novoAno, anoDeCadastro);
            this.veiculos.put(novoModelo, novoCarro);
            
        }else{

            Caminhao novoCaminhao = new Caminhao(novoTipoVeiculo, novoModelo, novaMarca, novoAno, anoDeCadastro);
            this.veiculos.put(novoModelo, novoCaminhao);
        }
        
        System.out.println("Veículo cadastrado.");
        
        Tools.log("VEICULO "+ novoModelo +" CRIADO");
    }

    // --------------------------------------------------READ
    
    public Optional<Veiculo> busca(String modeloBuscado){
        Veiculo veiculo = this.veiculos.get(modeloBuscado);
        return Optional.ofNullable(veiculo);
    }
    
    public void imprimirInfo(Veiculo veiculoAlvo) {
        String modelo = veiculoAlvo.getModelo();

        System.out.println("----- Informações do " + modelo + " -----");
        System.out.println(veiculoAlvo);
        System.out.println("-".repeat(33));
    }

    public void readVeiculo() {
        System.out.println("Digite o modelo do veículo que deseja buscar:");
        String modeloBuscado = scan.nextLine();
        
        Optional<Veiculo> veiculo = this.busca(modeloBuscado);

        veiculo.ifPresent(carroEncontrado -> {
            this.imprimirInfo(carroEncontrado);
            Tools.log("VEÍCULO "+ carroEncontrado.getModelo() +" BUSCADO");

        });

        if(veiculo.isEmpty()){
            System.out.println("Veículo não encontrado.");
            
            Tools.log("FALHA NO BUSCA DO VEÍCULO");
        }
    }

    public void readTodosOsVeiculos() {
        System.out.println("---- Informações dos veículos ----");
        this.veiculos.forEach((modelo, carro) -> System.out.println(carro + "\n" + "-".repeat(30)));
    
        Tools.log("TODOS OS VEÍCULOS BUSCADOS");
    }
    
    public void readVeiculosPorMarca(){
        System.out.println("Digite a marca dos veículos que deseja buscar:");
        String marcaBuscada = scan.nextLine();
        
        this.veiculos.values().stream()
            .filter(c -> c.getMarca().equalsIgnoreCase(marcaBuscada))
            .forEach(System.out::println);

        Tools.log("VEÍCULOS DA MARCA "+ marcaBuscada +" BUSCADOS");
    }

    // --------------------------------------------------UPDATE
    public void updateVeiculo() {
        System.out.println("Digite o modelo do veículo que deseja alterar:");
        String modeloBuscado = scan.nextLine();
        
        Optional<Veiculo> veiculo = this.busca(modeloBuscado);

        veiculo.ifPresent(veiculoEncontrado -> {
            System.out.println("Digite o novo modelo\n(deixe vazio para não alterar):");
            String novoModelo = scan.nextLine();
            System.out.println("Digite a nova marca\n(deixe vazio para não alterar):");
            String novaMarca = scan.nextLine();
            System.out.println("Digite o novo ano\n(deixe vazio para não alterar):");
            String novoAnoStr = scan.nextLine();

            if (!novoModelo.isEmpty()) {
                veiculoEncontrado.setModelo(novoModelo);
            }
            if (!novaMarca.isEmpty()) {
                veiculoEncontrado.setMarca(novaMarca);
            }
            if (!novoAnoStr.isEmpty()) {
                try {
                    int novoAno = Integer.parseInt(novoAnoStr);
                    veiculoEncontrado.setAno(novoAno);
                } catch (NumberFormatException e) {
                    System.out.println("Ano inválido. O ano não foi alterado.");
                }
            }
            System.out.println("Veículo atualizado com sucesso!");
            System.out.println("Novos dados: " + veiculoEncontrado);

            Tools.log("VEÍCULO "+ veiculoEncontrado.getModelo() +" ALTERADO");
        });

        if(veiculo.isEmpty()){
            System.out.println("Veículo não encontrado.");
            Tools.log("FALHA DA ALTERAÇÃO DO VEÍCULO " + modeloBuscado);
        }
    }

    // --------------------------------------------------DELETE
    public void deleteVeiculo() {
        System.out.println("Digite o modelo do veículo que deseja remover:");
        String modeloBuscado = scan.nextLine();
        
        Optional<Veiculo> veiculo = this.busca(modeloBuscado);
        
        veiculo.ifPresent(veiculoEncontrado -> {
            this.veiculos.remove(modeloBuscado);
            System.out.println("Veículo removido.");

            Tools.log("VEICULO " + modeloBuscado + " REMOVIDO");
        });

        if(veiculo.isEmpty()){
            System.out.println("Veículo não encontrado.");
            Tools.log("FALHA NA REMOÇÂO DO MODELO " + modeloBuscado);
        }
    }
}
//adicionando comentário só pra dar um commit correto