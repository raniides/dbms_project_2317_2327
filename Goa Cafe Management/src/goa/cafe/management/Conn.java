/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package goa.cafe.management;

import java.sql.*;  
/**
 *
 * @author raani
 */
public class Conn {
    Connection c;
    Statement s;
    Conn(){  
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            c = DriverManager.getConnection("jdbc:mysql:///cafemanagementsystem", "root", "Ranisona@13"); 
            
            s =c.createStatement();  
            
           
        }catch(Exception e){ 
            System.out.println(e);
        }  
    }  
    
    public PreparedStatement prepareStatement(String query) throws SQLException {
        return c.prepareStatement(query);
    }
}
