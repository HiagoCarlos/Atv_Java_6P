import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ControleRemoto extends Remote {
    void ligar() throws RemoteException;
    void desligar() throws RemoteException;
    void aumentarVolume() throws RemoteException;
    void diminuirVolume() throws RemoteException;
    void trocarCanal(int canal) throws RemoteException;
}
