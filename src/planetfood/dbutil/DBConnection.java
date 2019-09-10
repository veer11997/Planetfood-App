/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package planetfood.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author hp
 */
public class DBConnection {
     private static Connection conn;
    static{
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-L0CLMK94:1521/xe","planetfood","student");
            System.out.println("Connection opened");
            }
            catch(Exception e)
        {
            
            JOptionPane.showMessageDialog(null,"Db  error in opening connection ","Error",JOptionPane.ERROR_MESSAGE);
              e.printStackTrace();
        }
           
    }

    public static  Connection getConnection()
    {
        return conn;
    }
    public static void closeConnection()
    {
     try{   
         conn.close();
         System.out.println("COnnection Closed");
     
     }
     catch(SQLException e)
     {
       JOptionPane.showMessageDialog(null,"Db erroe in connection closed","Error",JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
     }
    }
    
}
