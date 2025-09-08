import java.net.*;
import java.io.*;
import java.util.Random;

public class ClienteUDP {
    public static void main(String[] args) {
        final String HOST = "localhost"; 
        final int PORTA = 12345;
        Random random = new Random();
        String nomeSensor = "Sensor" + (random.nextInt(100) + 1);

        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress endereco = InetAddress.getByName(HOST);

            while (true) {
                
                double temperatura = 20 + random.nextDouble() * 10; 
                int umidade = 40 + random.nextInt(40); 
                
        
                String mensagem = nomeSensor + ": " +
                        String.format("%.1f°C, %d%%RH", temperatura, umidade);

                byte[] dados = mensagem.getBytes();

                DatagramPacket pacote = new DatagramPacket(dados, dados.length, endereco, PORTA);
                socket.send(pacote);

                System.out.println("Enviado: " + mensagem);

                Thread.sleep(1000); 
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
