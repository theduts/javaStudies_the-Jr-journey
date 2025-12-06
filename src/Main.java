import java.util.Scanner;
import controller.CRUD;
import model.Veiculo;
import utils.VeiculoLoader;
import utils.Tools;
import view.Menu;
import java.util.HashMap;



public class Main {
    public static void main(String[] args) throws Exception {
        Tools.limparTela();
        String caminhoArquivo = "javaStudies_the-Jr-journey\\src\\data\\veiculos.csv";
        Menu menu = new Menu();    
        HashMap<String, Veiculo> mapaDeVeiculos = VeiculoLoader.carregarVeiculos(caminhoArquivo);
    
        int opcao = 0;
        try (Scanner scan = new Scanner(System.in);) {

            CRUD crud = new CRUD(scan, mapaDeVeiculos);

            if(!mapaDeVeiculos.isEmpty()){
                while (opcao != 7) {
                    menu.mostrarMenu();
                    opcao = scan.nextInt();
                    scan.nextLine(); // Consome o "Enter" que o nextInt() deixou
        
                    switch (opcao) {
                        case 1:
                            crud.readTodosOsVeiculos();
                            scan.nextLine();
                            break;
        
                        case 2:
                            crud.readVeiculo();
                            scan.nextLine();
                            break;
        
                        case 3:
                            crud.createVeiculo();
                            scan.nextLine();
                            break;
        
                        case 4:
                            crud.updateVeiculo();
                            scan.nextLine();
                            break;
        
                        case 5:
                            crud.deleteVeiculo();
                            scan.nextLine();
                            break;
        
                        case 6:
                            crud.readVeiculosPorMarca();
                            scan.nextLine();
                            break;
                        
                        case 7:
                            Tools.salvarDados(mapaDeVeiculos, caminhoArquivo);
                            break;
        
                        default:
                            System.out.println("Opção inválida.");
                            scan.nextLine();
                            break;
                    }
                    Tools.limparTela();
                }
            }else{System.out.println("O banco de carros não foi carregado");}
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}