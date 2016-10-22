package SRV;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;

/**
 * Created by Artem on 10.10.2016.
 */
public class DataBase {
    private static final String URL = "jdbc:mysql://localhost:3306/cw";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    public static void ConnectDB() {
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


            String[] a = Server.AAA();
            int i = 0;
            for (String part : a )
            {
                String query = "SELECT id_dish \n" +
                        "FROM ingredient \n" +
                        "WHERE id_product =" + a[i];
                ResultSet resultSet = statement.executeQuery(query);
                i++;

                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    System.out.println(id);
                }
            }


            //statement.addBatch("INSERT INTO one(nameca, desca) VALUES('name1','desc');");
            //statement.addBatch("INSERT INTO one(nameca, desca) VALUES('name2','desc');");
            //statement.addBatch("INSERT INTO one(nameca, desca) VALUES('name3','');");

            //statement.executeBatch();
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
