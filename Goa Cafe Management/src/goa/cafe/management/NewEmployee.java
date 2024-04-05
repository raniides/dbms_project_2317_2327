
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


public class NewEmployee extends JFrame implements ActionListener {
    
    JTextField tfid,tfname, tfage,tfsalary,tfphone,tfemail,tfadhar;
    JRadioButton rbmale,rbfemale,rbother;
    JButton submit,back;
    JComboBox cbjob;
    
    NewEmployee() {
         
        getContentPane().setBackground(Color.ORANGE);
        setLayout(null);
        
        JLabel heading = new JLabel("ADD NEW EMPLOYEE");
        heading.setBounds(120,50,400,30);
        heading.setFont(new Font("Tahoma",Font.BOLD,30));
        add(heading);
        
        JLabel lblname = new JLabel("EMPLOYEE ID");
        lblname.setBounds(60,130,220,35);
        lblname.setFont(new Font("Tahoma",Font.PLAIN,25));
        add(lblname);
        
        tfid = new JTextField();
        tfid.setBounds(270,130,190,30);
        tfid.setFont(new Font("Tahoma", Font.PLAIN, 20)); 
        add(tfid);
        
        JLabel lblid = new JLabel("FULL NAME");
        lblid.setBounds(60,190,220,35);
        lblid.setFont(new Font("Tahoma",Font.PLAIN,25));
        add(lblid);
        
        tfname = new JTextField();
        tfname.setBounds(270,190,190,30);
        tfname.setFont(new Font("Tahoma", Font.PLAIN, 20)); 
        add(tfname);
        
        JLabel lblage = new JLabel("AGE");
        lblage.setBounds(60,250,220,35);
        lblage.setFont(new Font("Tahoma",Font.PLAIN,25));
        add(lblage);
        
        tfage = new JTextField();
        tfage.setBounds(270,250,190,30);
        tfage.setFont(new Font("Tahoma", Font.PLAIN, 20)); 
        add(tfage);
        
        JLabel lblgender = new JLabel("GENDER");
        lblgender.setBounds(50,310,150,35);
        lblgender.setFont(new Font("Tahoma",Font.PLAIN,25));
        add(lblgender);
        
        ButtonGroup genderGroup = new ButtonGroup();
        
        rbmale = new JRadioButton("MALE");
        rbmale.setBounds(230,310,80,30);
        rbmale.setFont(new Font("Tahoma",Font.PLAIN,18));
        rbmale.setBackground(Color.WHITE);
        genderGroup.add(rbmale);
        add(rbmale);
        
        rbfemale = new JRadioButton("FEMALE");
        rbfemale.setBounds(310,310,110,30);
        rbfemale.setFont(new Font("Tahoma",Font.PLAIN,18));
        rbfemale.setBackground(Color.WHITE);
        genderGroup.add(rbfemale);
        add(rbfemale);
        
        rbother = new JRadioButton("OTHERS");
        rbother.setBounds(400,310,100,30);
        rbother.setFont(new Font("Tahoma",Font.PLAIN,18));
        rbother.setBackground(Color.WHITE);
        genderGroup.add(rbother);
        add(rbother);
        
        JLabel lbljob = new JLabel("JOB");
        lbljob.setBounds(60,370,220,35);
        lbljob.setFont(new Font("Tahoma",Font.PLAIN,25));
        add(lbljob);
        
        String str[] = {"Front Desk Clerks","HouseKeeping","Security","IT staff","Human resource","Kitchen staff","Accountant","Manager","Chef"};
        cbjob = new JComboBox(str);
        cbjob.setBounds(270, 370, 190, 30);
        cbjob.setBackground(Color.WHITE);
        cbjob.setFont(new Font("Tahoma", Font.PLAIN, 16)); 
        add(cbjob);
        
        JLabel lblsalary = new JLabel("SALARY");
        lblsalary.setBounds(60,420,220,35);
        lblsalary.setFont(new Font("Tahoma",Font.PLAIN,25));
        add(lblsalary);
        
        tfsalary = new JTextField();
        tfsalary.setBounds(270,420,190,30);
        tfsalary.setFont(new Font("Tahoma", Font.PLAIN, 20)); 
        add(tfsalary);
         
        JLabel lblphone = new JLabel("PHONE NO");
        lblphone.setBounds(60,490,220,35);
        lblphone.setFont(new Font("Tahoma",Font.PLAIN,25));
        add(lblphone);
        
        tfphone = new JTextField();
        tfphone.setBounds(270,490,190,30);
        tfphone.setFont(new Font("Tahoma", Font.PLAIN, 20));
        tfphone.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

        // Check if the entered character is a digit
        if (!Character.isDigit(c)) {
            e.consume(); // Ignore non-digit characters
        }

        // Check if the length exceeds 10 digits
        if (tfphone.getText().length() >= 10) {
            e.consume(); // Ignore additional characters
            JOptionPane.showMessageDialog(null, "Phone number should not exceed 10 digits", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
});
        
        add(tfphone);
        
        JLabel lblemail = new JLabel("EMAIL ID");
        lblemail.setBounds(60,550,220,35);
        lblemail.setFont(new Font("Tahoma",Font.PLAIN,25));
        add(lblemail);
        
        tfemail = new JTextField();
        tfemail.setBounds(270,550,190,30);
        tfemail.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(tfemail);
        
        JLabel lbladhar = new JLabel("AADHAR NO");
        lbladhar.setBounds(60,610,220,35);
        lbladhar.setFont(new Font("Tahoma",Font.PLAIN,25));
        add(lbladhar);
        
        tfadhar = new JTextField();
        tfadhar.setBounds(270,610,190,30);
        tfadhar.setFont(new Font("Tahoma", Font.PLAIN, 20)); 
        tfadhar.addKeyListener(new KeyAdapter(){
        public void keyTyped(KeyEvent e){
            char c = e.getKeyChar();
            
            if(!Character.isDigit(c)){
                e.consume();
            }
            
            if(tfadhar.getText().length() >= 12){
                e.consume();
                JOptionPane.showMessageDialog(null, "Adhar card number should not exceed 12 digit","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        });
        add(tfadhar);
        
        submit = new JButton("SUBMIT");
        submit.setBackground(Color.WHITE);
        submit.setForeground(Color.BLACK);
        submit.setBounds(90,700,150,40);
        submit.setFont(new Font("Tahoma",Font.PLAIN,20));
        submit.addActionListener(this);
        add(submit);
        
        back = new JButton("BACK");
        back.setBackground(Color.WHITE);
        back.setForeground(Color.BLACK);
        back.setBounds(300,700,150,40);
        back.setFont(new Font("Tahoma",Font.PLAIN,20));
        back.addActionListener(this);
        add(back);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/i4.jpeg"));
        JLabel image = new JLabel(i1);
        image.setBounds(550,50,700,680);
        add(image);
        
       
        setBounds(80,60,1300,900);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==submit){
        String id = tfid.getText();
        id = "gfcEmp"+id;
        String name = tfname.getText();
        String age = tfage.getText();
        String salary = tfsalary.getText();
        String phone = tfphone.getText();
        String email = tfemail.getText();
        String aadhar = tfadhar.getText();
        
        
        String gender = null;
        
        if (name.equals("")){
            JOptionPane.showMessageDialog(null, "name should not be empty");
            return;        
        }    
        
        if (rbmale.isSelected()){
            gender = "Male";
        }else if (rbfemale.isSelected()){
            gender = "Female";
        }
        
        String job = (String) cbjob.getSelectedItem();
        
        try{
            Conn conn = new Conn();
            
            // Check if the employee ID already exists in the database
            String checkQuery = "select * from employee where id = '" + id + "'";
            ResultSet rs = conn.s.executeQuery(checkQuery);
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Employee ID already exists", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String query = "insert into employee values('"+id+"','"+name+"','"+age+"','"+gender+"','"+job+"','"+salary+"','"+phone+"','"+email+"','"+aadhar+"')";
            
            conn.s.executeUpdate(query);
            
            JOptionPane.showMessageDialog(null,"Employee added successfully");
            
            setVisible(false);
            new Admin_Dashboard();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        }else if(ae.getSource()==back){
            setVisible(false);
            new Admin_Dashboard();
        }
         
        
    }
    
    public static void main(String[] args){
        new NewEmployee();
    }
}

