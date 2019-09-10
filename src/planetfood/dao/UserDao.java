/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package planetfood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import planetfood.dbutil.DBConnection;
import planetfood.pojo.Emp;
import planetfood.pojo.User;

/**
 *
 * @author hp
 */
public class UserDao {
    public static String validateUser(User user)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        String qry="Select username from Users where userid=? and password=? and usertype=?";
        PreparedStatement ps =conn.prepareStatement(qry);
        ps.setString(1,user.getUserId());
        ps.setString(2,user.getPassword());
        ps.setString(3,user.getUserType());
        ResultSet rs=ps.executeQuery();
        String username=null;
        if(rs.next()){
            username=rs.getString(1);
            
        }
        return username;
            
    }
    
      public static boolean addUser(User user)throws SQLException
    {
        String qry = "Select * from users where userid = ?";
        boolean status = true;
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1,user.getUserId());
        ResultSet rs = ps.executeQuery();
        if(rs.next())
            status = false;
        else
        {
            qry = "Insert into users values(?,?,?,?,?)";
            ps = conn.prepareStatement(qry);
            ps.setString(1, user.getUserId());
            ps.setString(2,user.getUserName());
            ps.setString(3,user.getEmpId());
            ps.setString(4,user.getPassword());
            ps.setString(5,user.getUserType());
            ps.executeUpdate();
        }
        return status;
    }
    
    public static boolean changePassword(String userid,String password)throws SQLException{
        String qry="Update users set password=? where userid=?";
        boolean status=true;
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement(qry);
        ps.setString(1,password);
        ps.setString(2,userid);
        int ans=ps.executeUpdate();
        if(ans==0)
               status=false;
     return status;
     } 
    
      public static String validateUserName(User user)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        String qry="Select username from Users where userid=? ";
        PreparedStatement ps =conn.prepareStatement(qry);
        ps.setString(1,user.getUserId());
       // ps.setString(2,user.getPassword());
      //  ps.setString(3,user.getUserType());
        ResultSet rs=ps.executeQuery();
        String username=null;
      //  if(rs.next()){
       //     username=rs.getString(1);
            
       // }
        return username;
            
    }
        public static ArrayList<User> getCashier(String userId)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select * from users where userid =?");
       ps.setString(1, userId);
        ResultSet rs=ps.executeQuery();
        
        ArrayList<User> userList=new ArrayList<User>();
        while(rs.next()){
        User obj=new User();
        obj.setUserId(rs.getString("userid"));
         obj.setUserName(rs.getString("username"));
        obj.setEmpId(rs.getString("empId"));
       
        userList.add(obj);
        
    }
return userList;
     }
        
         public static boolean removeCashier(String userId)throws SQLException{
        
      Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("Delete from users where userid=?");
     ps.setString(1, userId);
     int x=ps.executeUpdate();
     return x>0;
    }

    
}
