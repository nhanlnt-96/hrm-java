package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
  // database variable -> mysql://b71a3f72ed637d:457ba7a3@us-cdbr-east-05.cleardb.net/heroku_28fdb8291d6b6a4?reconnect=true

  // Kết nối vào MySQL.
  public static Connection getMySQLConnection()
      throws SQLException, ClassNotFoundException {

    String hostName = "us-cdbr-east-05.cleardb.net";
    String dbName = "heroku_28fdb8291d6b6a4";
    String userName = "b71a3f72ed637d";
    String password = "457ba7a3";

    return getMySQLConnection(hostName, dbName, userName, password);
  }

  public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password)
      throws SQLException, ClassNotFoundException {

    Class.forName("com.mysql.cj.jdbc.Driver");

    String connectionURL = "jdbc:mysql://" + hostName + "/" + dbName;

    Connection conn = DriverManager.getConnection(connectionURL, userName, password);
    return conn;
  }
}
