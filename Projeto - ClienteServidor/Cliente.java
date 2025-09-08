import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345);
             PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Conectado ao servidor!");
            String mensagem;

            while (true) {
                System.out.print("Digite uma mensagem (ou 'sair' para encerrar): ");
                mensagem = teclado.readLine();
                saida.println(mensagem);

                String resposta = entrada.readLine();
                System.out.println("Servidor: " + resposta);

                if (mensagem.equalsIgnoreCase("sair")) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
