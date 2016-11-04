package SRV;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Artem on 10.10.2016.
 */
public class DataBase {
    private static final String URL = "jdbc:mysql://localhost:3306/cw";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    public static void ConnectDB() {
        Map<Integer, Integer> dictionary = Dictionary.dictionary;
        ArrayList keyList = Dictionary.keyList;
        ArrayList indexList = Dictionary.indexList;
        ArrayList indexListTable =  new ArrayList();
        //Connection connection;

        // указываем какой jdbc драйвер для взаимодействия с бд будем использовать
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);   // регистрируем наш драйвер
        } catch (SQLException e) {
            System.err.println("Неудалось загрузить класс драйвера!");
        }

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            System.out.println("Соединение с БД установлено!");


            String[] a = Server.SplitString();
            int i = 0;
            for (String part : a )
            {
                String query = "SELECT id_dish \n" +
                        "FROM ingredient \n" +
                        "WHERE id_product =" + a[i] + " \n" +
                        "GROUP BY id_dish";
                ResultSet resultSet = statement.executeQuery(query);
                i++;

                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    System.out.println(id);
                    Dictionary.AddDictionary(id);
                }
            }

            Dictionary.addList();

            for (int j = 0; j < keyList.size(); j++)
            {
                String query = "SELECT count \n" +
                        "FROM dish \n" +
                        "WHERE id =" + keyList.get(j) + " \n";
                ResultSet result = statement.executeQuery(query);

                while (result.next()) {
                    int count = result.getInt(1);
                    indexListTable.add(count);
                }

            }

            System.out.println("Словарь: " + dictionary);
            System.out.println("Ключи: " + keyList );
            System.out.println("Значения: " + indexList);
            System.out.println("Значения В таблице: " + indexListTable);

            Dictionary.aaa(keyList, indexList, indexListTable);

            System.out.println("Новые ключи: " + keyList );
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

       /* connection.close();
        if (connection.isClosed()){
            System.out.println("Соединение с БД закрыто!");
        }*/
    }


}
