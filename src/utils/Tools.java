package utils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.nio.file.Files;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import model.Veiculo;


public class Tools {
    public static void limparTela() {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Não foi possível limpar a tela.");
        }
    }

    public static void log(String mensagem){
        String caminhoArquivo = "src/data/log.txt";

        try (FileWriter writer = new FileWriter(caminhoArquivo, true) ; BufferedWriter bw = new BufferedWriter(writer)){
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            LocalDateTime dataHora = LocalDateTime.now();
            String mensagemLog = dataHora.format(formatador) + ": " + mensagem;
            bw.newLine();
            bw.write(mensagemLog);
            
        } catch (IOException e) {
            System.out.println("Erro ao salvar log: " + e.getMessage());
        }
    }

    public static void salvarDados(Map<String, Veiculo> mapaDeVeiculos, String caminhoArquivo){
        StringBuilder sb = new StringBuilder();

        sb.append("tipo,modelo,marca,ano,tipoVeiculo,dataDeCadastro,cilindrada\n");
        
        for(Veiculo veiculo : mapaDeVeiculos.values()){
            sb.append(veiculo.toFileString()).append("\n");
        }

        try {
            Path caminho = Paths.get(caminhoArquivo);
            Files.writeString(caminho, sb.toString());
            
            String mensagemLog = "CARROS SALVOS";
            Tools.log(mensagemLog);

        } catch (Exception e) {
            String mensagemLog = "FALHA AO SALVAR CARROS: " + e.getMessage();
            Tools.log(mensagemLog);
        }
    }
}