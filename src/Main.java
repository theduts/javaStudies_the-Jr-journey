import java.util.Scanner;
import controller.CRUD;
import model.Carro;
import utils.CarroLoader;
import utils.Tools;
import view.Menu;
import java.util.HashMap;



public class Main {
    public static void main(String[] args) throws Exception {
        Tools.limparTela();
        String caminhoArquivo = "src/data/carros.csv";
        Menu menu = new Menu();    
        HashMap<String, Carro> mapaDeCarros = CarroLoader.carregarCarros(caminhoArquivo);
    
        int opcao = 0;
        try (Scanner scan = new Scanner(System.in);) {

            CRUD crud = new CRUD(scan);

            if(!mapaDeCarros.isEmpty()){
                while (opcao != 7) {
                    menu.mostrarMenu();
                    opcao = scan.nextInt();
                    scan.nextLine(); // Consome o "Enter" que o nextInt() deixou
        
                    switch (opcao) {
                        case 1:
                            crud.readTodosOsCarros(mapaDeCarros);
                            scan.nextLine();
                            break;
        
                        case 2:
                            crud.readCarro(mapaDeCarros);
                            scan.nextLine();
                            break;
        
                        case 3:
                            crud.createCarro(mapaDeCarros);
                            scan.nextLine();
                            break;
        
                        case 4:
                            crud.updateCarro(mapaDeCarros);
                            scan.nextLine();
                            break;
        
                        case 5:
                            crud.deleteCarro(mapaDeCarros);
                            scan.nextLine();
                            break;
        
                        case 6:
                            crud.readCarrosPorMarca(mapaDeCarros);
                            scan.nextLine();
                            break;
                        
                        case 7:
                            Tools.salvarDados(mapaDeCarros, caminhoArquivo);
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