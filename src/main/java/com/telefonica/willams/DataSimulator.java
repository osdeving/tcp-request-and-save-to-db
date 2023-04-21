package com.telefonica.willams;

import java.util.Timer;
import java.util.TimerTask;

import java.util.Timer;
import java.util.TimerTask;

public class DataSimulator {
    public static void main(String[] args) {
        // Inicia o DataSender em uma nova thread
        Thread senderThread = new Thread(() -> {
            DataSender.main(null);
        });
        senderThread.start();

        // Espera um pouco para garantir que o DataSender esteja em execução antes de iniciar o

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Inicia o DataReceiver em uma nova thread
        Thread receiverThread = new Thread(() -> {
            Database database = new Database();
            DataReceiver dataReceiver = new DataReceiver(database);
            dataReceiver.start("127.0.0.1", Constants.PORT);
        });
        receiverThread.start();

        // Configura um temporizador para encerrar a simulação após 5 segundos
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Encerrando a simulação após 5 segundos.");
                System.exit(0);
            }
        }, 5000); // Encerra a simulação após 5 segundos (5000 milissegundos)
    }
}
