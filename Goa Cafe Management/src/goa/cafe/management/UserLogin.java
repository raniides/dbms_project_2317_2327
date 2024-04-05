
package goa.cafe.management;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

/**
 *
 * @author raani
 */
public class UserLogin extends JFrame implements ActionListener{
    
    JTextField username;
    JPasswordField pass;
    JButton login,cancel,adminlogin;
    
    
    UserLogin() {
                
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
               
        JLabel userlogin = new JLabel("USER LOGIN");
        userlogin.setBounds(510,300,300,45);
        userlogin.setForeground(Color.CYAN);
        userlogin.setFont(new Font("Tahoma",Font.BOLD, 35));
        image.add(userlogin);
            
        JLabel user = new JLabel("Username");
        user.setBounds(470,380,140,35);
        user.setForeground(Color.WHITE);
        user.setFont(new Font("Tahoma",Font.BOLD, 25));
        image.add(user);
        
        username = new JTextField();
       username.setBounds(620, 380, 170, 35);
       username.setFont(new Font("Tahoma",Font.PLAIN,15));
       image.add(username);
                
        JLabel password = new JLabel("Password");
        password.setBounds(470,460,140,35);
        password.setForeground(Color.WHITE);
        password.setFont(new Font("Tahoma",Font.BOLD, 25));
        image.add(password);
       
        pass = new JPasswordField();
       pass.setBounds(620, 460, 170, 35);
       pass.setFont(new Font("Tahoma",Font.PLAIN,15));
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
        
        adminlogin = new JButton("Admin login");
        adminlogin.setBounds(580,620,120,40);
        adminlogin.setBackground(Color.WHITE);
        adminlogin.setForeground(Color.BLACK);
        adminlogin.addActionListener(this);
        image.add(adminlogin);
        
        
        setBounds(80,60,1300,900);
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == login){
          String user = username.getText();
          String passs = new String(pass.getPassword());
            
            try{
                Conn c = new Conn();
                
                String query = "select * from User_Login where username = '" + user + "' and  password = '" + passs +"' ";
                
                ResultSet rs = c.s.executeQuery(query);
                
                if(rs.next()){
                    setVisible(false);
                    new User_Dashboard();
                }else{
                    JOptionPane.showMessageDialog(null,"Invalid username or password");
                }
            } catch (Exception e){
               e.printStackTrace(); 
            }
        }else if(ae.getSource() == adminlogin){
            setVisible(false);
            new AdminLogin();
        }else if (ae.getSource() == cancel){
            setVisible(false);
        }
        
    }
    
    public static void main(String[] args) {
        new UserLogin();
    }
    
}