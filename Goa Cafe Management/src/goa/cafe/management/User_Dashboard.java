
package goa.cafe.management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author raani
 */
public class User_Dashboard extends JFrame implements ActionListener {
    
    JButton order,viewbill,logout;
    
    User_Dashboard(){
        
        getContentPane().setBackground(Color.ORANGE);
        setLayout(null);
        
        JLabel heading = new JLabel("Reception");
        heading.setBounds(530,40,220,40);
        heading.setFont(new Font("Tahoma",Font.BOLD,40));
        add(heading);
        
        order = new JButton("Order");
        order.setBounds(880,150,300,80);
        order.setFont(new Font("serif",Font.PLAIN,40));
        order.setBackground(Color.WHITE);
        order.setForeground(Color.BLACK);
        order.addActionListener(this);
        add(order);
        
        viewbill = new JButton("View Bill");
        viewbill.setBounds(880,350,300,80);
        viewbill.setFont(new Font("serif",Font.PLAIN,40));
        viewbill.setBackground(Color.WHITE);
        viewbill.setForeground(Color.BLACK);
        viewbill.addActionListener(this);
        add(viewbill);
        
        logout = new JButton("Logout");
        logout.setBounds(880,550,300,80);
        logout.setFont(new Font("serif",Font.PLAIN,40));
        logout.setBackground(Color.WHITE);
        logout.setForeground(Color.BLACK);
        logout.addActionListener(this);
        add(logout);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/i3.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(600, 600, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(10,90,800,650);
        add(image);
        
        setBounds(80,60,1300,900);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == order){
            setVisible(false);
            new Order();
        }else if(ae.getSource() == viewbill){
            setVisible(false);
            new ViewBill();
        }else if(ae.getSource() == logout){
            setVisible(false);
            new UserLogin();
        }
            
    }
    
    public static void main(String[] args){
        new User_Dashboard();
    }
    
}

