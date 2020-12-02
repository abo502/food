package DBUtil;

import java.sql.*;

public class Db {
    private static String DBDriver = "com.mysql.cj.jdbc.Driver";
    private static String User = "root";
    private static String Password = "root";
    private static String URL = "jdbc:mysql://localhost:3306/food?serverTimezone=UTC";
    static Connection connection = null;
    static PreparedStatement preparedStatement=null;


    public static Connection getConnection(){
        try {
            Class.forName(DBDriver);
            connection=DriverManager.getConnection(URL,User,Password);
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static PreparedStatement createPreparedStatement(String sql){
        try {
            preparedStatement=getConnection().prepareStatement(sql);
            return preparedStatement;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeAll(){
        if (preparedStatement==null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection==null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
