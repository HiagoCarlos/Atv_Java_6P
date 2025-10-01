import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class ControleRemotoImpl extends UnicastRemoteObject implements ControleRemoto {
    private int volume = 10;
    private int canal = 1;
    private boolean ligado = false;

    public ControleRemotoImpl() throws RemoteException {}

    public void ligar() throws RemoteException {
        ligado = true;
        System.out.println("TV ligada");
    }

    public void desligar() throws RemoteException {
        ligado = false;
        System.out.println("TV desligada");
    }

    public void aumentarVolume() throws RemoteException {
        if(ligado) {
            volume++;
            System.out.println("Volume " + volume);
        }
    }

    public void diminuirVolume() throws RemoteException {
        if(ligado && volume > 0) {
            volume--;
            System.out.println("Volume " + volume);
        }
    }

    public void trocarCanal(int canal) throws RemoteException {
        if(ligado) {
            this.canal = canal;
            System.out.println("Canal " + canal);
        }
    }
}
