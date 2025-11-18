import java.util.Scanner;
import controller.CRUD;
import model.Carro;
import utils.CarroLoader;
import utils.Tools;
import view.Menu;
import java.util.HashMap;



public class Main {
    public static void main(String[] args) throws Exception {
        String caminhoArquivo = "src/data/carros.csv";
        Scanner scan = new Scanner(System.in);
        Menu menu = new Menu();
        
        HashMap<String, Carro> mapaDeCarros = CarroLoader.carregarCarros(caminhoArquivo);
        CRUD crud = new CRUD();
        

        int opcao = 0;

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
        scan.close();
    }
}