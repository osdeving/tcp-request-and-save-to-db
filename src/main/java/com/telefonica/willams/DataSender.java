package com.telefonica.willams;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class DataSender {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(Constants.PORT)) {
            System.out.println("Aguardando conexão...");

            try (Socket socket = serverSocket.accept()) {
                System.out.println("Conexão estabelecida!");

                OutputStream outputStream = socket.getOutputStream();
                Random random = new Random();

                for (int i = 0; i < 10; i++) {
                    DataCLP dataCLP = new DataCLP();
                    dataCLP.bitSet = (byte) random.nextInt(256);
                    dataCLP.sensor1 = (short) random.nextInt(Short.MAX_VALUE + 1);
                    dataCLP.sensor2 = random.nextFloat() * 100;

                    byte[] byteArray = dataCLP.toByteArray();
                    outputStream.write(byteArray);
                    outputStream.flush();

                    System.out.println("Dados enviados: " + dataCLP);
                    Thread.sleep(1000); // Pausa de 1 segundo entre cada envio
                }
            } catch (InterruptedException e) {
                System.out.println("Erro ao pausar entre os envios: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Erro ao criar o servidor ou enviar dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

