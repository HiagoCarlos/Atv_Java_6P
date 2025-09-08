import java.net.*;
import java.io.*;
import java.util.*;

public class ServidorUDP {
    public static void main(String[] args) {
        final int PORTA = 12345;
        Map<String, String> leituras = new HashMap<>();

        try (DatagramSocket socket = new DatagramSocket(PORTA)) {
            byte[] buffer = new byte[1024];
            System.out.println("Servidor UDP ouvindo na porta " + PORTA + "...");

            while (true) {
                DatagramPacket pacote = new DatagramPacket(buffer, buffer.length);
                socket.receive(pacote); 

                String mensagem = new String(pacote.getData(), 0, pacote.getLength());
                String[] partes = mensagem.split(":", 2);

                if (partes.length == 2) {
                    leituras.put(partes[0].trim(), partes[1].trim());
                }

            
                System.out.println("===== Leituras Atuais =====");
                leituras.forEach((sensor, valor) ->
                    System.out.println(sensor + " -> " + valor)
                );
                System.out.println("===========================");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
