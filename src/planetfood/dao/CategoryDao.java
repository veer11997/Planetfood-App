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
import planetfood.pojo.Category;

/**
 *
 * @author hp
 */
public class CategoryDao {
    
    public static HashMap<String,String> getAllCategoryId() throws SQLException{
        
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("Select cat_name,cat_id from categories");
        HashMap<String,String> categories=new HashMap<>();
        while(rs.next()){
            String catName=rs.getString(1);
            String cat_Id=rs.getString(2);
            categories.put(catName,cat_Id);
        }
   
            return categories;
    }
    
    public static ArrayList<Category> getAllData()throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        String qry="Select * from categories";
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(qry);
        ArrayList<Category> productList =new ArrayList<Category>();
        while(rs.next()){
            Category p=new Category();
            p.setCatId(rs.getString("cat_id"));
            p.setCatName(rs.getString("cat_name"));
           
           productList.add(p);
    }   
    
    return productList;
}
    
    public static String getNewID()throws SQLException {
        
        Connection conn =DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Select count(*) from categories");
        int id=101;
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
            id=id+rs.getInt(1);
        }
        return "C"+id;
        }
    
    public static boolean addProduct (Category p)throws SQLException{
        
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into categories values(?,?)");
      
        ps.setString(1,p.getCatId());
        ps.setString(2,p.getCatName());
        
        int x=ps.executeUpdate();
        return(x>0);
    }
    
     
    public static HashMap<String,Category> getProductsByCategory(String catId)throws SQLException
    {
     Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("select * from Categories where cat_id=?");
     HashMap<String,Category> productList=new HashMap<String,Category>();
     ps.setString(1,catId);
     ResultSet rs=ps.executeQuery();
     
while(rs.next()){
    Category p=new Category();
    p.setCatId(catId);
    p.setCatId(rs.getString("cat_id"));
    p.setCatName(rs.getString("cat_name"));
   
    productList.put(p.getCatId(),p);
}
return productList;
}
    
    public static ArrayList<Category> getAllDataCat()throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        String qry="Select * from Categories";
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(qry);
        ArrayList<Category> productList =new ArrayList<Category>();
        while(rs.next()){
            Category p=new Category();
            p.setCatId(rs.getString("cat_id"));
            p.setCatName(rs.getString("cat_name"));
           productList.add(p);
    }   
   
    return productList;
}
    
    public static boolean updateProduct(Category p)throws SQLException
    {
     Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("Update categories set cat_name=? where cat_id=?");
      
      
        ps.setString(1,p.getCatName());
        ps.setString(2,p.getCatId());
    
        int x=ps.executeUpdate();
    return (x>0);
    }
    
      public static HashMap<String,String> getActiveProductsByCategory(String catId)throws SQLException
    {
     Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("select cat_id,cat_name from Categories where cat_id=?");
     HashMap<String,String> productList=new HashMap<>();
     ps.setString(1,catId);
     ResultSet rs=ps.executeQuery();
     
while(rs.next()){
  //  Category p=new Category();
   // p.setCatId(catId);
   String catid=rs.getString("cat_id");
   String catname=rs.getString("cat_name");
   
    productList.put(catid,catname);
}
return productList;
}
      
    public static HashMap<String,String> getAllCategory() throws SQLException{
      
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("Select cat_id,cat_name from categories");
        HashMap<String,String> categories=new HashMap<>();
        while(rs.next()){
            String catid=rs.getString(1);
            String catname=rs.getString(2);
            categories.put(catid,catname);
        }
   
            return categories;
    }
    
     public static HashMap<String,String> getProductsCategory(String catId)throws SQLException
    {
     Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("select cat_id,cat_name from Categories where cat_id=?");
     HashMap<String,String> catList=new HashMap<>();
     ps.setString(1,catId);
     ResultSet rs=ps.executeQuery();
     
while(rs.next()){
  //  Category p=new Category();
   // p.setCatId(catId);
   String catid=rs.getString("cat_id");
   String catname=rs.getString("cat_name");
   
    catList.put(catid,catname);
}
return catList;
}
    

}
