package SRV;

import java.net.*;
import java.io.*;
public class Server {
    static String line = new String();
    static String title = new String();
    public static void CreateServer() {
        int port = 31010; //случайный порт (может быть любое число от 1025 до 65535)

        try {
            ServerSocket ss = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту
            System.out.println("Ожидаем клиента...");
            while(true) {
                Socket socket = ss.accept(); // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
                System.out.println("Клиент подключился.");
                System.out.println();

                // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту.
                InputStream sin = socket.getInputStream();
                OutputStream sout = socket.getOutputStream();

                // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
                DataInputStream in = new DataInputStream(sin);
                DataOutputStream out = new DataOutputStream(sout);




                line = in.readUTF(); // ожидаем пока клиент пришлет строку текста.
                System.out.println("Клиент прислал : " + line);
                SplitString();
                System.out.println("Я ему отсылкаю:...");
                //
                //
                //
                DataBase.ConnectDB();
                String cook = DataBase.cook;
                String noCook = DataBase.noCook;
                String idDish = DataBase.idDish;
                String noIdDish = DataBase.noIdDish;

                String str = new String();
                String id = "";
                str = cook + "/" + noCook;
                id = idDish + "/" + noIdDish;

                out.writeUTF(str); // отсылаем клиенту обратно ту самую строку текста.
                str = "";
                out.flush(); // заставляем поток закончить передачу данных.
                out.writeUTF(id); // отсылаем клиенту обратно ту самую строку текста.

                out.flush(); // заставляем поток закончить передачу данных.
                System.out.println("id которые уйдут: " + id);
                id = "";

                title = in.readUTF();
                System.out.println("id выбранного товара: " + title);
                if(title != "0") {
                    title = DataBase.RecipeSearch(title);
                    out.writeUTF(title); // отсылаем клиенту обратно ту самую строку текста.

                    out.flush(); // заставляем поток закончить передачу данных.
                    System.out.println(title);
                    title = "";
                }
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