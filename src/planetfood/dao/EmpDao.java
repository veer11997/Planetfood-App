
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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import planetfood.dbutil.DBConnection;
import planetfood.pojo.Emp;
import planetfood.pojo.User;

/**
 *
 * @author hp
 */
public class EmpDao {
    
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
    
    public static String getNewID()throws SQLException {
        
        Connection conn =DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Select count(*) from employees");
        int id=101;
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
            id=id+rs.getInt(1);
        }
       
        System.out.println(id);
        return "E"+id;
        }
    
    public static boolean addEmployee(Emp p)throws SQLException{
        
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into employees values(?,?,?,?)");
      
        ps.setString(1,p.getEmpId());
        ps.setString(2,p.getEname());
        ps.setString(3,p.getJob());
        ps.setDouble(4,p.getSal());
        int x=ps.executeUpdate();
       return(x>0);
       // return (x==1);
        
        
    }
    
    public static ArrayList<Emp> getAllData()throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        String qry="Select * from employees";
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(qry);
        ArrayList<Emp> productList =new ArrayList<Emp>();
        while(rs.next()){
            Emp p=new Emp();
            p.setEmpId(rs.getString("EmpId"));
            p.setEname(rs.getString("Ename"));
            p.setJob(rs.getString("JOB"));
             p.setSal(rs.getDouble("SAL"));
           productList.add(p);
    }   
    
    return productList;
}
    public static HashMap<String,String> getAllCategoryId() throws SQLException{
        
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("Select empid,ename,job  from employees");
        HashMap<String,String> employees=new HashMap<>();
        while(rs.next()){
            String empId=rs.getString(1);
            String ename=rs.getString(2);
            String job=rs.getString(3);
            
            employees.put(empId,ename);
        }
   
            return employees;
    }
    
      public static HashMap<String,Emp> getProductsByCategory(String empId)throws SQLException
    {
     Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("select * from employees where empid=?");
     HashMap<String,Emp> productList=new HashMap<String,Emp>();
     ps.setString(1,empId);
     ResultSet rs=ps.executeQuery();
     
while(rs.next()){
    Emp p=new Emp();
    p.setEmpId(empId);
    p.setEmpId(rs.getString("empId"));
    p.setEname(rs.getString("ename"));
    p.setJob(rs.getString("job"));
    p.setSal(rs.getDouble("sal"));
    productList.put(p.getEmpId(),p);
}
return productList;
}
      
       public static ArrayList<Emp> getEmp(String empId)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select * from employees where empid =?");
       ps.setString(1, empId);
        ResultSet rs=ps.executeQuery();
        
        ArrayList<Emp> orderList=new ArrayList<Emp>();
        while(rs.next()){
        Emp obj=new Emp();
        obj.setEmpId(rs.getString("empid"));
         obj.setEname(rs.getString("ename"));
        obj.setJob(rs.getString("job"));
        obj.setSal(rs.getDouble("sal"));
        
        orderList.add(obj);
        
    }
return orderList;
     }
       
        public static boolean removeEmp(String empId)throws SQLException{
        
      Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("Delete from employees where empid=?");
     ps.setString(1, empId);
     int x=ps.executeUpdate();
     return x>0;
    }
        
          public static boolean updateProduct(Emp p)throws SQLException
    {
     Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("Update employees set empid=?,ename=?,job=?,sal=? where empid=?");
      
        ps.setString(1,p.getEmpId());
        ps.setString(2,p.getEname());
     //   ps.setDouble(3,p.getSal());
        ps.setString(3,p.getJob());
        ps.setDouble(4,p.getSal());
         ps.setString(5,p.getEmpId());
        int x=ps.executeUpdate();
    return (x>0);
    }
       
}
  
