import java.net.*;
import java.io.*;
public class ServerNew {
   public static void main(String[] ar)    {
     int port = 31010; //случайный порт (может быть любое число от 1025 до 65535)
       try {
         ServerSocket ss = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту
         System.out.println("Ожидаем клиента...");

         Socket socket = ss.accept(); // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
         System.out.println("Клиент подключился.");
         System.out.println();

 // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту. 
         InputStream sin = socket.getInputStream();
         OutputStream sout = socket.getOutputStream();

 // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
         DataInputStream in = new DataInputStream(sin);
         DataOutputStream out = new DataOutputStream(sout);

         String line = null;
        // while(true) {
           line = in.readUTF(); // ожидаем пока клиент пришлет строку текста.
           System.out.println("Клиент прислал : " + line);
           System.out.println("Я ему отсылкаю:...");
           out.writeUTF("End"); // отсылаем клиенту обратно ту самую строку текста.
           out.flush(); // заставляем поток закончить передачу данных.
           System.out.println();
       //  }
      } catch(Exception x) { x.printStackTrace(); }
   }
}