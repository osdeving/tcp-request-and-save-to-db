package com.telefonica.willams;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class DataReceiver {
    private Database database;

    public DataReceiver(Database database) {
        this.database = database;
    }

    public void start(String ip, int port) {
        try (Socket socket = new Socket(ip, port)) {
            InputStream inputStream = socket.getInputStream();
            byte[] buffer = new byte[Constants.SIZE_OF_RESPONSE];

            while (true) {
                int bytesRead = inputStream.read(buffer);
                if (bytesRead == -1) {
                    System.out.println("Conexão encerrada pelo servidor.");
                    break;
                } else if (bytesRead == Constants.SIZE_OF_RESPONSE) {
                    DataCLP dataCLP = DataCLP.fromByteArray(buffer);
                    System.out.println(dataCLP);
                    System.out.println("Salvando resposta no banco de dados...");
                    database.saveResponse(dataCLP);
                } else {
                    System.out.println("Dados incompletos recebidos. Pulando...");
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao conectar ou ler dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

