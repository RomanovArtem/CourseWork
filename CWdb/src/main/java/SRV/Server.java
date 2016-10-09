package SRV;

import java.net.*;
import java.io.*;
public class Server {
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

                String line = new String();


                line = in.readUTF(); // ожидаем пока клиент пришлет строку текста.
                //  AAA(line);
                System.out.println("Клиент прислал : " + line);
                System.out.println("Я ему отсылкаю:...");
                out.writeUTF("End"); // отсылаем клиенту обратно ту самую строку текста.
                out.flush(); // заставляем поток закончить передачу данных.
                System.out.println();
            }
        } catch(Exception x) { x.printStackTrace(); }
    }

    public static void AAA(String inputString)
    {
        String[] parts = inputString.split(" ");
        int i = 0;
        for (String part : parts )
        {
            System.out.println(i + parts[i]);
            i++;
        }
    }

}