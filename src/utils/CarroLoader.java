package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import model.Carro;
import model.Moto;

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
                String[] dados = linha.trim().split("[,;]", -1); // aceita vírgula OU ponto e vírgula E lê o último elemento ainda que vazio

                if (dados.length != 7) {
                    System.out.println("Linha inválida ignorada: " + linha);
                    continue;
                }
                
                String tipo = dados[0].trim();
                String modelo = dados[1].trim();
                String marca = dados[2].trim();
                int ano = Integer.parseInt(dados[3].trim().replaceAll("[^0-9]", "")); // garante que só números vão
                String tipoCarro = dados[4].trim();
                String dataString = dados[5].trim();
                LocalDate dataCadastro = LocalDate.parse(dataString);
                int cilindrada = 0;
                if(!dados[6].isEmpty()){cilindrada = Integer.parseInt(dados[6].trim().replaceAll("[^0-9]", ""));}
                

                if(tipo.equalsIgnoreCase("CARRO")){
                    Carro carro = new Carro(tipoCarro, modelo, marca, ano, dataCadastro);
                    mapaDeCarros.put(modelo, carro);
                }else if(tipo.equalsIgnoreCase("MOTO")){
                    Moto moto = new Moto(tipoCarro, cilindrada, modelo, marca, ano, dataCadastro);
                    //mapaDeCarros.put(modelo, moto);
                }
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter ano em número: " + e.getMessage());
        }

        return mapaDeCarros;
    }
    
}
//String tipoMoto, int cilindrada, String modelo, String marca, int ano, LocalDate dataCadastro