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

    public static String cook = new String();
    public static String noCook = new String();
    public static String idDish = new String();
    public static String noIdDish = new String();

    public static void ConnectDB() {
        Map<Integer, Integer> dictionary = Dictionary.dictionary;
        ArrayList keyList = Dictionary.keyList;
        ArrayList indexList = Dictionary.indexList;
        ArrayList canCook = Dictionary.canCook;
        ArrayList noCanCook = Dictionary.noCanCook;
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

            Dictionary.DistributionId(keyList, indexList, indexListTable);
            keyList.clear();
            indexList.clear();
            indexListTable.clear();

            System.out.println("Новые ключи: " + keyList );
            System.out.println("Могу приготовить: " + canCook );
            System.out.println("Не могу приготовить: " + noCanCook );


            for (int j = 0; j < canCook.size(); j++)
            {
                String query = "SELECT * \n" +
                        "FROM dish \n" +
                        "WHERE id =" + (int)canCook.get(j) + " \n";
                ResultSet resultCook = statement.executeQuery(query);

                while (resultCook.next()) {
                    int id = resultCook.getInt(1);
                    String name = resultCook.getString(2);

                    idDish = idDish + id + ";";
                    cook = cook + name + ";";
                }
            }
            for (int j = 0; j < noCanCook.size(); j++)
            {
                String query = "SELECT * \n" +
                        "FROM dish \n" +
                        "WHERE id =" + (int)noCanCook.get(j) + " \n";
                ResultSet resultNoCook = statement.executeQuery(query);

                while (resultNoCook.next()) {
                    int id = resultNoCook.getInt(1);
                    String name = resultNoCook.getString(2);
                    //
                    String recipedb = resultNoCook.getString(4);
                    //

                    noIdDish = noIdDish + id + ";";
                    noCook = noCook + name + ";";
                }
            }

            System.out.println("Строка, что могу приготовить: " + cook);
            System.out.println("Строка, что не могу приготовить: " + noCook);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

       /* connection.close();
        if (connection.isClosed()){
            System.out.println("Соединение с БД закрыто!");
        }*/
    }


    public static String RecipeSearch(String title) {
        String idDB = "";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {

                String query = "SELECT recipe \n" +
                        "FROM dish \n" +
                        "WHERE id =" + title + " \n";
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    idDB = resultSet.getString(1);
                    System.out.println(idDB);
                }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return idDB;
    }



}
