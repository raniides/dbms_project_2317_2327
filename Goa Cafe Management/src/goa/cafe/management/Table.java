
package goa.cafe.management;

/**
 *
 * @author raani
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;

public class Table extends JFrame implements ActionListener{
    
    JTextField tftableno;
    JComboBox seatscombo;
    JButton add,check, cancel;
    
    Table(){
      
        getContentPane().setBackground(Color.ORANGE);
        setLayout(null);
        
        JLabel heading = new JLabel("ADD TABLE");
        heading.setBounds(550,80,400,30);
        heading.setFont(new Font("Tahoma",Font.BOLD,40));
        add(heading);
        
        JLabel lbltableno = new JLabel("Table Number");
        lbltableno.setBounds(430,180,220,35);
        lbltableno.setFont(new Font("Tahoma",Font.PLAIN,25));
        add(lbltableno);
        
        tftableno = new JTextField();
        tftableno.setBounds(600,185,180,25);
        tftableno.setFont(new Font("Tahoma", Font.PLAIN, 20)); 
        add(tftableno);
        
        JLabel lblseats = new JLabel("No of Seats");
        lblseats.setBounds(430,300,220,35);
        lblseats.setFont(new Font("Tahoma",Font.PLAIN,25));
        add(lblseats);
        
        String availableOption[] = { "6 seats", "4 seats","2 Seats"};
        seatscombo = new JComboBox(availableOption);
        seatscombo.setBounds(600,300,180,35);
        seatscombo.setBackground(Color.WHITE);
        seatscombo.setFont(new Font("Tahoma", Font.PLAIN, 20)); 
        add(seatscombo);
        
        add = new JButton("Add Table");
        add.setForeground(Color.BLACK);
        add.setBackground(Color.WHITE);
        add.setBounds(510,420,130,40);
        add.setFont(new Font("Tahoma",Font.PLAIN,15));
        add.addActionListener(this);
        add(add);
        
        cancel = new JButton("Cancel");
        cancel.setForeground(Color.BLACK);
        cancel.setBackground(Color.WHITE);
        cancel.setBounds(680,420,130,40);
        cancel.setFont(new Font("Tahoma",Font.PLAIN,15));
        cancel.addActionListener(this);
        add(cancel);
        
        setBounds(80,60,1300,900);

        setVisible(true);
        
        
    }
    
     public void actionPerformed(ActionEvent ae){
         if (ae.getSource() == add ){
             String tablenumber = tftableno.getText();
             String seats = (String) seatscombo.getSelectedItem();
             
             try {
                 Conn conn =new Conn();
                 
                 // Check if the employee ID already exists in the database
            String checkQuery = "select * from Tablee where Table_no = '" + tablenumber + "'";
            ResultSet rs = conn.s.executeQuery(checkQuery);
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Table_no already exists", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
                 
                 String query1 = "insert into Tablee values('"+tablenumber+"','"+seats+"')";
                 
                 conn.s.executeUpdate(query1);
                 
                 JOptionPane.showMessageDialog(null,"Table added successfully");
                 
             }catch (Exception e){
                 e.printStackTrace();
             }
         }else if(ae.getSource()==cancel){
             setVisible(false);
             new Admin_Dashboard();
         }
     }
    
     public static void main(String[] args){
        new Table();
    }
    
}
