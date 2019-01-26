package sample;

import java.sql.Connection;
import java.sql.DriverManager;

public class connectionConfiguration
{


    public static Connection getConnection() throws InterruptedException
    {
        System.out.print("Connecting to the server");
        for(int i=0;i<5;i++)
        {
            Thread.sleep(300);
            System.out.print(".");
        }
        System.out.print("\n");
        Connection connection=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection("URL","USENAME","PASSWORD");
        }catch (Exception e)
        {
            System.out.println("Unable to connect with the server, Please try again later");
        }

        return connection;
    }
}