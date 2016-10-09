package SRV;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Artem on 10.10.2016.
 */
public class DataBase {
    private static final String URL = "jdbc:mysql://localhost:3306/cw";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    public static void ConnectDB() {
        Connection connection;

        // указываем какой jdbc драйвер для взаимодействия с бд будем использовать
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);   // регистрируем наш драйвер

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed()){
                System.out.println("Соединение с БД установлено!");
            }
            connection.close();
            if (connection.isClosed()){
                System.out.println("Соединение с БД закрыто!");
            }
        } catch (SQLException e) {
            System.err.println("Неудалось загрузить класс драйвера!");
        }
    }
}
