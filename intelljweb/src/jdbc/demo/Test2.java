package jdbc.demo;

import org.apache.commons.dbcp2.cpdsadapter.DriverAdapterCPDS;
import org.apache.commons.dbcp2.datasources.SharedPoolDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;

public class Test2 {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            DriverAdapterCPDS cpds = new DriverAdapterCPDS();
            cpds.setDriver("com.mysql.cj.jdbc.Driver");
            cpds.setUrl("jdbc:mysql://127.0.0.1:3306/demo?serverTimezone=CTT&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true");
            cpds.setUser("root");
            cpds.setPassword("root");
            SharedPoolDataSource tds = new SharedPoolDataSource();
            tds.setConnectionPoolDataSource(cpds);

            connection = tds.getConnection();
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
