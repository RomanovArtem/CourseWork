package com.example.artem.cw;

import android.provider.Settings;

import java.net.*;
import java.io.*;

public class Client {
    private Runnable doInThread = new Runnable() {
        Socket socket;
        public void run() {

            int serverPort = 6666; // здесь обязательно нужно указать порт к которому привязывается сервер.
            String address = "127.0.0.1"; // это IP-адрес компьютера, где исполняется наша серверная программа.
            // Здесь указан адрес того самого компьютера где будет исполняться и клиент.

            try {
                InetSocketAddress inetAddress = new InetSocketAddress("192.168.0.102", 31007); // создаем объект который отображает вышеописанный IP-адрес.
                System.out.println("Any of you heard of a socket with IP address " + address + " and port " + serverPort + "?");
                socket = new Socket(inetAddress.getAddress(), 31007); // создаем сокет используя IP-адрес и порт сервера.
                System.out.println("Yes! I just got hold of the program.");

                // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиентом.
                InputStream sin = socket.getInputStream();
                OutputStream sout = socket.getOutputStream();

                // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
                DataInputStream in = new DataInputStream(sin);
                DataOutputStream out = new DataOutputStream(sout);

                // Создаем поток для чтения с клавиатуры.
                BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
                String line = null;
                System.out.println("Type in something and press enter. Will send it to the server and tell ya what it thinks.");
                System.out.println();

                while (true) {
                    line = keyboard.readLine(); // ждем пока пользователь введет что-то и нажмет кнопку Enter.
                    System.out.println("Sending this line to the server...");
                    out.writeUTF(line); // отсылаем введенную строку текста серверу.
                    out.flush(); // заставляем поток закончить передачу данных.
                    line = in.readUTF(); // ждем пока сервер отошлет строку текста.
                    System.out.println("The server was very polite. It sent me this : " + line);
                    System.out.println("Looks like the server is pleased with us. Go ahead and enter more lines.");
                    System.out.println();
                }
            } catch (Exception x) {
                x.printStackTrace();
            }
        }
    };
}