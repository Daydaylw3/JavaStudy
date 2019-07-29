package jdbc.demo;

import java.sql.Connection;
import java.sql.DriverManager;

public class Test1 {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/demo?serverTimezone=CTT&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true";
            String userName = "root";
            String password = "root";
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println(connection);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
