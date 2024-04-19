package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class NumberClient {

    public static void main(String[] args) {
        String serverAddress = "localhost"; // Địa chỉ IP hoặc tên máy chủ
        int port = 9999; // Port mặc định

        try (Socket socket = new Socket(serverAddress, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String number;
            while ((number = in.readLine()) != null) {
                System.out.println("Nhận số: " + number);
            }
        } catch (IOException e) {
            System.err.println("Lỗi: " + e.getMessage());
        }
    }
}
