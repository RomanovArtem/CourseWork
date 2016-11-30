package SRV;

import java.io.*;
import java.net.Socket;

import static SRV.Server.SplitString;
import static SRV.Server.line;
import static SRV.Server.title;

/**
 * Created by Artem on 23.11.2016.
 */
public class KEK implements Runnable {

    Socket socket;
    String str;
    String id;



    public KEK (Socket _socket) {
        socket = _socket;
    }


    public void run() {
        System.out.println("Клиент подключился.");
        System.out.println();

        // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту.
        InputStream sin = null;
        try {
            sin = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        OutputStream sout = null;
        try {
            sout = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
        DataInputStream in = new DataInputStream(sin);
        DataOutputStream out = new DataOutputStream(sout);


        try {
            line = in.readUTF(); // ожидаем пока клиент пришлет строку текста.
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Клиент прислал : " + line);
        SplitString();
        System.out.println("Я ему отсылкаю:...");
        //
        //
        //
        DataBase.ConnectDB();
       // if (DataBase.flag == true) {
            String cook = DataBase.cook;
            String noCook = DataBase.noCook;
            String idDish = DataBase.idDish;
            String noIdDish = DataBase.noIdDish;

           str = "";
           id = "";
            str = cook + "/" + noCook;
            id = idDish + "/" + noIdDish;
        String a = idDish + noIdDish;
        System.out.println(a);
        if (a.length() == 0) {
            str = "";
            id = "";
            System.out.println(str + "  " + id);
        }
        try {
            out.writeUTF(str); // отсылаем клиенту обратно ту самую строку текста.
        } catch (IOException e) {
            e.printStackTrace();
        }
        str = "";
        try {
            out.flush(); // заставляем поток закончить передачу данных.
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.writeUTF(id); // отсылаем клиенту обратно ту самую строку текста.
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            out.flush(); // заставляем поток закончить передачу данных.
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("id которые уйдут: " + id);
        id = "";

        try {
            title = in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("id выбранного товара: " + title);
        if(title != "0") {
            title = DataBase.RecipeSearch(title);
            try {
                out.writeUTF(title); // отсылаем клиенту обратно ту самую строку текста.
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                out.flush(); // заставляем поток закончить передачу данных.
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(title);
            title = "";
        }
    }
}
