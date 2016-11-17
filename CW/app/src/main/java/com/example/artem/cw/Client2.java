package com.example.artem.cw;

import android.app.Activity;
import android.provider.Settings;
import android.widget.Toast;

import java.net.*;
import java.io.*;

public class Client2 extends Thread {
    public volatile String recipe = "";
    static Socket socket;
    InputStream sin;
    OutputStream sout;
    DataInputStream in;
    DataOutputStream out;
    //InputStream sin;
    //OutputStream sout;
    //DataInputStream in;
    //DataOutputStream out;
    String id;
    public void Client2(String _id)
    {
        id = _id;
        System.out.println("id" + id);
    }

    final Client client=new Client();
    public void run() {


        // int serverPort = 31007; // здесь обязательно нужно указать порт к которому привязывается сервер.
        //String address = "192.168.0.102"; // это IP-адрес компьютера, где исполняется наша серверная программа.
        // Здесь указан адрес того самого компьютера где будет исполняться и клиент.

        try {

            sin = client.socket.getInputStream();
            sout = client.socket.getOutputStream();

            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
            in = new DataInputStream(sin);
            out = new DataOutputStream(sout);

            System.out.println("Посылаем данные серверу...");

            out.writeUTF(id); // отсылаем введенную строку текста серверу.
            out.flush(); // заставляем поток закончить передачу данных.
            recipe = in.readUTF(); // ждем пока сервер отошлет строку текста.
            System.out.println(recipe);

        } catch (Exception x) {
            x.printStackTrace();
        }

        finally {
            try {
                client.socket.close();
                System.out.println("Сокет закрыт");
            } catch (IOException e) {
                System.err.println("Сокет не закрыт");
            }
        }
    }
};