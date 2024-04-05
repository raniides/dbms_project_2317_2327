
package goa.cafe.management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author raani
 */
public class Admin_Dashboard extends JFrame implements ActionListener {
    
    JButton newemployee,manageemployee,addproduct,manageproduct,addtable,order,viewbill,logout;
    
    Admin_Dashboard(){
        
        getContentPane().setBackground(Color.ORANGE);
        setLayout(null);
        
        JLabel heading = new JLabel("Reception");
        heading.setBounds(530,40,220,40);
        heading.setFont(new Font("Tahoma",Font.BOLD,40));
        add(heading);
        
        newemployee = new JButton("New Employee Form");
        newemployee.setBounds(70,150,300,60);
        newemployee.setFont(new Font("serif",Font.PLAIN,30));
        newemployee.setBackground(Color.WHITE);
        newemployee.setForeground(Color.BLACK);
        newemployee.addActionListener(this);
        add(newemployee);
        
        manageemployee = new JButton("Manage Employee");
        manageemployee.setBounds(920,150,300,60);
        manageemployee.setFont(new Font("serif",Font.PLAIN,30));
        manageemployee.setBackground(Color.WHITE);
        manageemployee.setForeground(Color.BLACK);
        manageemployee.addActionListener(this);
        add(manageemployee);
        
        addproduct = new JButton("Add Product");
        addproduct.setBounds(70,350,300,60);
        addproduct.setFont(new Font("serif",Font.PLAIN,30));
        addproduct.setBackground(Color.WHITE);
        addproduct.setForeground(Color.BLACK);
        addproduct.addActionListener(this);
        add(addproduct);
        
        manageproduct = new JButton("Manage Product");
        manageproduct.setBounds(920,350,300,60);
        manageproduct.setFont(new Font("serif",Font.PLAIN,30));
        manageproduct.setBackground(Color.WHITE);
        manageproduct.setForeground(Color.BLACK);
        manageproduct.addActionListener(this);
        add(manageproduct);
        
        addtable = new JButton("Add Table");
        addtable.setBounds(70,550,300,60);
        addtable.setFont(new Font("serif",Font.PLAIN,30));
        addtable.setBackground(Color.WHITE);
        addtable.setForeground(Color.BLACK);
        addtable.addActionListener(this);
        add(addtable);
        
        logout = new JButton("Logout");
        logout.setBounds(920,550,300,60);
        logout.setFont(new Font("serif",Font.PLAIN,30));
        logout.setBackground(Color.WHITE);
        logout.setForeground(Color.BLACK);
        logout.addActionListener(this);
        add(logout);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/i3.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(500, 600, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(50,90,1200,650);
        add(image);
        
        setBounds(80,60,1300,900);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if (ae.getActionCommand().equals("New Employee Form")){
            setVisible(false);
            new NewEmployee();
        }else if(ae.getSource() == manageemployee){
            setVisible(false);
            new ManageEmployee();
        }else if(ae.getSource() == addproduct){
            setVisible(false);
            new AddProduct();
        }else if(ae.getSource() == manageproduct){
            setVisible(false);
            new ManageProduct();
        }else if(ae.getSource() == addtable){
            setVisible(false);
            new Table();
        }else if(ae.getSource() == logout){
            setVisible(false);
            new AdminLogin();
        }
            
    }
    
    public static void main(String[] args){
        new Admin_Dashboard();
    }
    
}

