package SRV;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Artem on 10.10.2016.
 */

public class Main {

    public static void main(String[] args) {
        Server.CreateServer();

    }
}
