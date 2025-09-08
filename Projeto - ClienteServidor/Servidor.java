import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(12345)) {
            System.out.println("Servidor aguardando conexão...");

            Socket socket = servidor.accept();
            System.out.println("Cliente conectado!");

            BufferedReader entrada = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );

            PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);

            String mensagem;
            while ((mensagem = entrada.readLine()) != null) {
                System.out.println("Cliente: " + mensagem);

                if (mensagem.equalsIgnoreCase("sair")) {
                    saida.println("Conexão encerrada pelo cliente.");
                    break;
                }
                saida.println("Servidor recebeu: " + mensagem);
            }

            socket.close();
            System.out.println("Conexão encerrada com o cliente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
