package org.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class NumberServer {

    public static void main(String[] args) {
        int port = 9999; // Port mặc định

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server đang chờ kết nối...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Đã kết nối từ: " + clientSocket.getInetAddress().getHostAddress());
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("Lỗi: " + e.getMessage());
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
                for (int i = 1; i <= 1000; i++) {
                    out.println(i);
                    Thread.sleep(1000); // Gửi số tiếp theo sau mỗi giây
                }
            } catch (IOException | InterruptedException e) {
                System.err.println("Lỗi: " + e.getMessage());
            }
        }
    }
}
