
package goa.cafe.management;

/**
 *
 * @author raani
 */
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import net.proteanit.sql.*;

public class ManageEmployee extends JFrame implements ActionListener{
    JTable table;
    Choice e_id;
    JButton search,removeemployee,back,show;
    JComboBox job;
    
    ManageEmployee(){
        
        getContentPane().setBackground(Color.ORANGE);
        setLayout(null);
        
        JLabel heading = new JLabel("MANAGE EMPLOYEE");
        heading.setBounds(550,10,400,30);
        heading.setFont(new Font("Tahoma",Font.BOLD,25));
        add(heading);
        
        JLabel lbljob = new JLabel("JOB");
        lbljob.setBounds(100,50,40,30);
        lbljob.setFont(new Font("Tahoma", Font.PLAIN, 18)); 
        add(lbljob);
        
        job = new JComboBox(new String[]{"All","Front Desk Clerks","HouseKeeping","Security","IT staff","Human resource","Kitchen staff","Accountant","Manager","Chef"});
        job.setBounds(150,50,180,30);
        job.setBackground(Color.WHITE);
        add(job);
        
        search = new JButton("Search");
        search.setForeground(Color.BLACK);
        search.setBackground(Color.WHITE);
        search.setBounds(330,50,100,30);
        search.addActionListener(this);
        add(search);
        
        JLabel lblid = new JLabel("Employee_id");
        lblid.setBounds(610,50,120,30);
        lblid.setFont(new Font("Tahoma", Font.PLAIN, 18)); 
        add(lblid);
        
        e_id = new Choice();
        e_id.setBounds(750,50,100,25);
        e_id.setFont(new Font("Tahoma",Font.PLAIN,16));
        add(e_id);
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from employee");
            
            while(rs.next()){
                 e_id.add(rs.getString("eid"));
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        show = new JButton("Show");
        show.setForeground(Color.BLACK);
        show.setBackground(Color.WHITE);
        show.setBounds(870,50,100,30);
        show.addActionListener(this);
        add(show);
        
        JLabel l1=new JLabel("Employee_Name");
        l1.setBounds(15,90,140,20);
        l1.setFont(new Font("Tahoma", Font.PLAIN, 18)); 
        add(l1);
        
        JLabel l2= new JLabel("Age");
        l2.setBounds(210,90,100,20);
        l2.setFont(new Font("Tahoma", Font.PLAIN, 18)); 
        add(l2);
        
        JLabel l3= new JLabel("Gender");
        l3.setBounds(360,90,100,20);
        l3.setFont(new Font("Tahoma", Font.PLAIN, 18)); 
        add(l3);
        
        JLabel l4= new JLabel("Job");
        l4.setBounds(530,90,100,20);
        l4.setFont(new Font("Tahoma", Font.PLAIN, 18)); 
        add(l4);
        
        JLabel l5= new JLabel("Salary");
        l5.setBounds(670,90,100,20);
        l5.setFont(new Font("Tahoma", Font.PLAIN, 18)); 
        add(l5);
        
        JLabel l6= new JLabel("Phone_NO");
        l6.setBounds(840,90,100,20);
        l6.setFont(new Font("Tahoma", Font.PLAIN, 18)); 
        add(l6);
        
        JLabel l7= new JLabel("Email_ID");
        l7.setBounds(1010,90,100,20);
        l7.setFont(new Font("Tahoma", Font.PLAIN, 18)); 
        add(l7);
        
        JLabel l8= new JLabel("Aadhar_NO");
        l8.setBounds(1160,90,100,20);
        l8.setFont(new Font("Tahoma", Font.PLAIN, 18)); 
        add(l8);
        
        table= new JTable();
        table.setBounds(0,120,1300,590);
        table.setBackground(Color.ORANGE);
        table.setFont(new Font("Tahoma", Font.PLAIN, 18)); 
        table.setRowHeight(30);
        add(table);
        
        try{
            Conn c =new Conn();
            ResultSet rs= c.s.executeQuery("select * from employee");
            table.setModel(DbUtils.resultSetToTableModel(rs)); 
        }catch(Exception e){
            e.printStackTrace();
        }
        
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);
        
        removeemployee = new JButton("Remove Employee");
        removeemployee.setForeground(Color.BLACK);
        removeemployee.setBackground(Color.WHITE);
        removeemployee.setBounds(500,730,200,40);
        removeemployee.setFont(new Font("Tahoma",Font.PLAIN,20));
        removeemployee.addActionListener(this);
        add(removeemployee);
        
        back = new JButton("Back");
        back.setForeground(Color.BLACK);
        back.setBackground(Color.WHITE);
        back.setBounds(740,730,120,40);
        back.setFont(new Font("Tahoma", Font.PLAIN, 20));
        back.addActionListener(this);
        add(back);
        
        
        setBounds(80,60,1300,900);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == search){
            try {
             String employeeAll = (String) job.getSelectedItem();
                
            String query1 = "select * from employee where job = '"+job.getSelectedItem()+"'";
            String query2 = "select * from employee";
               
            
            Conn c = new Conn();
            ResultSet rs;
            
            rs = c.s.executeQuery(query1);
            
            if(employeeAll.equals("All")){
                    rs = c.s.executeQuery(query2);
             }
            
            
            table.setModel(DbUtils.resultSetToTableModel(rs));
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }else if(ae.getSource() == show){
            try {
            String query1 = "select * from employee where eid = '"+e_id.getSelectedItem()+"'";
            
            Conn c = new Conn();
            ResultSet rs;
            
            rs = c.s.executeQuery(query1);
            
            table.setModel(DbUtils.resultSetToTableModel(rs));
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }else if(ae.getSource() == removeemployee){
            try {
                Conn c = new Conn();
                
                String emp_id = e_id.getSelectedItem();
                
                String query1 = "delete from employee where eid = '"+emp_id+"'";
                
                c.s.executeUpdate(query1);
                
                ResultSet rs4;
                    
                String query2 = "select * from employee";
                
                rs4 = c.s.executeQuery(query2);
                
                table.setModel(DbUtils.resultSetToTableModel(rs4));
                
                JOptionPane.showMessageDialog(null, "Data Updated Successfully");
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(ae.getSource() == back){
        setVisible(false);
        new Admin_Dashboard();
        }
    }
    
    public static void main(String[] args){
        new ManageEmployee();
    }
    
}
