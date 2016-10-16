package com.example.artem.cw;

import android.app.Activity;
import android.provider.Settings;
import android.widget.Toast;

import java.net.*;
import java.io.*;

public class Client extends Thread {
    public volatile String stroka = "";
    static Socket socket;

    public void run() {


        // int serverPort = 31007; // здесь обязательно нужно указать порт к которому привязывается сервер.
        //String address = "192.168.0.102"; // это IP-адрес компьютера, где исполняется наша серверная программа.
        // Здесь указан адрес того самого компьютера где будет исполняться и клиент.

        try {
            InetSocketAddress inetAddress = new InetSocketAddress("192.168.0.102", 31010); // создаем объект который отображает вышеописанный IP-адрес.
            System.out.println("Сокет с адресом : " + inetAddress);
            socket = new Socket(inetAddress.getAddress(), 31010); // создаем сокет используя IP-адрес и порт сервера.

            System.out.println("Сокет создан.");

            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиентом.
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            // Создаем поток для чтения с клавиатуры.
            //BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            System.out.println();

            //while (true) {
            //line = keyboard.readLine(); // ждем пока пользователь введет что-то и нажмет кнопку Enter.
            System.out.println("Посылаем данные серверу...");
            stroka = Meals.Convert();

            out.writeUTF(stroka); // отсылаем введенную строку текста серверу.
            System.out.println("Посылаем серверу: " + stroka);
            out.flush(); // заставляем поток закончить передачу данных.
            stroka = in.readUTF(); // ждем пока сервер отошлет строку текста.
            //System.out.println("Сервер прислал: : " + stroka);

            // }
        } catch (Exception x) {
            x.printStackTrace();
        }

        finally {
            try {
                socket.close();
                System.out.println("Сокет закрыт");
            } catch (IOException e) {
                System.err.println("Сокет не закрыт");
            }
        }
    }
};