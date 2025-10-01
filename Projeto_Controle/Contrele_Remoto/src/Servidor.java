import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {
    public static void main(String[] args) {
        try {
            ControleRemoto controle = new ControleRemotoImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ControleRemoto", controle);
            System.out.println("Servidor iniciado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
