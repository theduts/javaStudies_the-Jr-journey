package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import model.Carro;

public class CarroLoader {

    public static HashMap<String, Carro> carregarCarros(String caminhoArquivo){
        HashMap<String, Carro> mapaDeCarros = new HashMap<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            boolean primeiraLinha = true;

            while ((linha = br.readLine()) != null) {
                // Ignora a primeira linha do cabeçalho
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }

                // Divide corretamente os dados — ajusta conforme o separador do CSV
                String[] dados = linha.trim().split("[,;]"); // aceita vírgula OU ponto e vírgula

                if (dados.length != 3) {
                    System.out.println("Linha inválida ignorada: " + linha);
                    continue;
                }

                String modelo = dados[0].trim();
                String marca = dados[1].trim();
                int ano = Integer.parseInt(dados[2].trim().replaceAll("[^0-9]", "")); // garante que só números vão

                Carro carro = new Carro(modelo, marca, ano);
                mapaDeCarros.put(modelo, carro);
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter ano em número: " + e.getMessage());
        }

        return mapaDeCarros;
    }
    
}