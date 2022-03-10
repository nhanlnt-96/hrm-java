package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
  // database variable -> mysql://b71a3f72ed637d:457ba7a3@us-cdbr-east-05.cleardb
  // .net/heroku_28fdb8291d6b6a4?reconnect=true
  public Connection conn;

  public Connection getConn() {
    String dbName = "heroku_28fdb8291d6b6a4";
    String dbUser = "b71a3f72ed637d";
    String dbPass = "457ba7a3";
    String dbUrl = "jdbc:mysql://us-cdbr-east-05.cleardb.net";

    try {
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
    return conn;
  }
}
