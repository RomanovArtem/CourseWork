package com.example.artem.cw;

import android.app.Activity;
import android.provider.Settings;
import android.widget.Toast;

import java.net.*;
import java.io.*;

public class Client extends Thread {
    public volatile String outStroka = "";
    public volatile String inStroka = "";
    public volatile String idDish = "";
    static Socket socket = new Socket();
    InputStream sin;
    OutputStream sout;
    DataInputStream in;
    DataOutputStream out;
static boolean flagClient = true;


    public void run() {


        // int serverPort = 31007; // здесь обязательно нужно указать порт к которому привязывается сервер.
        //String address = "192.168.0.102"; // это IP-адрес компьютера, где исполняется наша серверная программа.
        // Здесь указан адрес того самого компьютера где будет исполняться и клиент.


            System.out.println("b");
            InetSocketAddress inetAddress = new InetSocketAddress("192.168.0.101", 31010); // создаем объект который отображает вышеописанный IP-адрес.
            System.out.println("Сокет с адресом : " + inetAddress);
            try {
                socket = new Socket(inetAddress.getAddress(), 31010); // создаем сокет используя IP-адрес и порт сервера.
            } catch (Exception ex) {
                inStroka = "-1";
                idDish = "-1";
                ex.printStackTrace();
            }

            System.out.println("Сокет создан.");
        try {
            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиентом.
            sin = socket.getInputStream();
            sout = socket.getOutputStream();

            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
            in = new DataInputStream(sin);
            out = new DataOutputStream(sout);

            // Создаем поток для чтения с клавиатуры.
            //BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            System.out.println();

            //while (true) {
            //line = keyboard.readLine(); // ждем пока пользователь введет что-то и нажмет кнопку Enter.
            System.out.println("Посылаем данные серверу...");
           // outStroka = Meals.Convert();

            out.writeUTF(Meals.outputString); // отсылаем введенную строку текста серверу.
            System.out.println("Посылаем серверу: " + Meals.outputString);
            out.flush(); // заставляем поток закончить передачу данных.
            inStroka = in.readUTF(); // ждем пока сервер отошлет строку текста.
            idDish = in.readUTF(); // ждем пока сервер отошлет строку текста.
            if (inStroka.length() == 0 && idDish.length() == 0)
            {
                flagClient = false;
            }
            //recipe = in.readUTF(); // ждем пока сервер отошлет строку текста.
            // System.out.println(recipe);
            //System.out.println("Сервер прислал: : " + stroka);

            // }
        } catch (Exception x) {
            x.printStackTrace();
        }

        /*finally {
            try {
                socket.close();
                System.out.println("Сокет закрыт");
            } catch (IOException e) {
                System.err.println("Сокет не закрыт");
            }
        } */
    }
}