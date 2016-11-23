package SRV;

import java.net.*;
import java.io.*;
public class Server {
    static String line = new String();
    static String title = new String();
    public void CreateServer() {
        int port = 31010; //случайный порт (может быть любое число от 1025 до 65535)

        try {
            ServerSocket ss = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту
            System.out.println("Ожидаем клиента...");
            while(true) {
                Socket socket = ss.accept(); // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
                KEK kek = new KEK(socket);


                new Thread(kek).start();


            }
        } catch(Exception x) { x.printStackTrace(); }
    }

    public static String[] SplitString()
    {
        String[] parts = line.split(" ");
        int i = 0;
        for (String part : parts )
        {
            parts[i] = parts[i].replaceFirst ("fr", "");
            parts[i] = parts[i].replaceFirst ("ve", "");
            parts[i] = parts[i].replaceFirst ("se", "");
            parts[i] = parts[i].replaceFirst ("da", "");
            parts[i] = parts[i].replaceFirst ("su", "");
            parts[i] = parts[i].replaceFirst ("nu", "");
            parts[i] = parts[i].replaceFirst ("me", "");
            parts[i] = parts[i].replaceFirst ("re", "");
            parts[i] = parts[i].replaceFirst ("sf", "");
            parts[i] = parts[i].replaceFirst ("ce", "");
            parts[i] = parts[i].replaceFirst ("gr", "");
            parts[i] = parts[i].replaceFirst ("be", "");
            i++;
        }
        return parts;
    }

}