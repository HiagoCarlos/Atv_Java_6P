import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            ControleRemoto controle = (ControleRemoto) registry.lookup("ControleRemoto");

            controle.ligar();
            controle.trocarCanal(7);
            controle.aumentarVolume();
            controle.diminuirVolume();
            controle.desligar();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
