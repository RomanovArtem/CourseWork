import java.net.*;
import java.io.*;
public class ServerNew {
   public static void main(String[] ar)    {
     int port = 31010; //��������� ���� (����� ���� ����� ����� �� 1025 �� 65535)
       try {
         ServerSocket ss = new ServerSocket(port); // ������� ����� ������� � ����������� ��� � �������������� �����
         System.out.println("������� �������...");

         Socket socket = ss.accept(); // ���������� ������ ����� ����������� � ������� ��������� ����� ���-�� �������� � ��������
         System.out.println("������ �����������.");
         System.out.println();

 // ����� ������� � �������� ������ ������, ������ ����� �������� � �������� ������ �������. 
         InputStream sin = socket.getInputStream();
         OutputStream sout = socket.getOutputStream();

 // ������������ ������ � ������ ���, ���� ����� ������������ ��������� ���������.
         DataInputStream in = new DataInputStream(sin);
         DataOutputStream out = new DataOutputStream(sout);

         String line = null;
        // while(true) {
           line = in.readUTF(); // ������� ���� ������ ������� ������ ������.
           System.out.println("������ ������� : " + line);
           System.out.println("� ��� ��������:...");
           out.writeUTF("End"); // �������� ������� ������� �� ����� ������ ������.
           out.flush(); // ���������� ����� ��������� �������� ������.
           System.out.println();
       //  }
      } catch(Exception x) { x.printStackTrace(); }
   }
}