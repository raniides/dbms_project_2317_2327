
package goa.cafe.management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

/**
 *
 * @author raani
 */
public class AdminLogin extends JFrame implements ActionListener{
    
    JTextField username;
    JPasswordField pass;
    JButton login,cancel,userlogin;
    
    
    AdminLogin() {
        setLayout(null);
        
        ImageIcon i1 =new  ImageIcon(ClassLoader.getSystemResource("icons/i2.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(1300, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,1300,900);
        add(image);
        
        JPanel whiteBox = new JPanel();
        whiteBox.setBounds(395, 240, 510, 470);
        whiteBox.setBackground(new Color(255, 255, 255, 128)); // 128 is the alpha value for transparency
        image.add(whiteBox);
               
        JLabel adminlogin = new JLabel("ADMIN LOGIN");
        adminlogin.setBounds(510,300,300,45);
        adminlogin.setForeground(Color.CYAN);
        adminlogin.setFont(new Font("serif",Font.BOLD, 35));
        image.add(adminlogin);
            
        JLabel user = new JLabel("Username");
        user.setBounds(470,380,140,35);
        user.setForeground(Color.MAGENTA);
        user.setFont(new Font("serif",Font.BOLD, 25));
        image.add(user);
        
        username = new JTextField();
       username.setBounds(620, 380, 170, 35);
       username.setFont(new Font("Tahoma", Font.PLAIN, 16)); 
       image.add(username);
                
        JLabel password = new JLabel("Password");
        password.setBounds(470,460,140,35);
        password.setForeground(Color.MAGENTA);
        password.setFont(new Font("serif",Font.BOLD, 25));
        image.add(password);
       
        pass = new JPasswordField();
       pass.setBounds(620, 460, 170, 35);
       pass.setFont(new Font("Tahoma", Font.PLAIN, 16)); 
       image.add(pass);
        
        login = new JButton("Login");
        login.setBounds(500,550,120,40);
        login.setBackground(Color.WHITE);
        login.setForeground(Color.BLACK);
        login.addActionListener(this);
        image.add(login);
        
        cancel = new JButton("cancel");
        cancel.setBounds(650,550,120,40);
        cancel.setBackground(Color.WHITE);
        cancel.setForeground(Color.BLACK);
        cancel.addActionListener(this);
        image.add(cancel);
        
        userlogin = new JButton("User login");
        userlogin.setBounds(580,620,120,40);
        userlogin.setBackground(Color.WHITE);
        userlogin.setForeground(Color.BLACK);
        userlogin.addActionListener(this);
        image.add(userlogin);
        
        
        setBounds(80,60,1300,900);
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == login){
            String user = username.getText();
            String passs = new String(pass.getPassword());
            
            try{
                Conn c = new Conn();
                
                String query = "select * from Admin_Login where username = '" + user + "' and  password = '" + passs +"' ";
                
                ResultSet rs = c.s.executeQuery(query);
                
                if(rs.next()){
                    setVisible(false);
                    new Admin_Dashboard();
                }else{
                    JOptionPane.showMessageDialog(null,"Invalid username or password");
                }
            } catch (Exception e){
               e.printStackTrace(); 
            }
        }else if(ae.getSource() == userlogin){
            setVisible(false);
            new UserLogin();
        }else if (ae.getSource() == cancel){
            setVisible(false);
        }
        
    }
    
    public static void main(String[] args) {
        new AdminLogin();
    }
    
}
