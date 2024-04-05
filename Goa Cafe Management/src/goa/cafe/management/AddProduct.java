/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package goa.cafe.management;

/**
 *
 * @author raani
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class AddProduct extends JFrame implements ActionListener {

    JTextField txtid,txtname, txtprice;
    JComboBox txtcategory;
    JButton btnsave, btnclear,back;

    AddProduct() {

        getContentPane().setBackground(Color.ORANGE);
        setLayout(null);

        JLabel heading = new JLabel("ADD NEW PRODUCT");
        heading.setBounds(520, 100, 400, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 30));
        add(heading);
        
        JLabel lblid = new JLabel("Product ID");
        lblid.setBounds(370, 200, 220, 35);
        lblid.setFont(new Font("Tahoma", Font.PLAIN, 25));
        add(lblid);

        txtid = new JTextField();
        txtid.setBounds(620, 200, 270, 40);
        txtid.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(txtid);

        JLabel lblname = new JLabel("Product Name");
        lblname.setBounds(370, 300, 220, 35);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 25));
        add(lblname);

        txtname = new JTextField();
        txtname.setBounds(620, 300, 270, 40);
        txtname.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(txtname);

        JLabel lblcategory = new JLabel("Category");
        lblcategory.setBounds(370, 400, 220, 35);
        lblcategory.setFont(new Font("Tahoma", Font.PLAIN, 25));
        add(lblcategory);

        String[] categories = { "Soft Drinks", "Snacks", "Beverages","FastFood","Desert"}; //Add more categories here if need
        txtcategory = new JComboBox(categories);
        txtcategory.setBounds(620, 400, 190, 40);
        txtcategory.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(txtcategory);

        JLabel lblprice = new JLabel("Price");
        lblprice.setBounds(370, 500, 270, 35);
        lblprice.setFont(new Font("Tahoma", Font.PLAIN, 25));
        add(lblprice);

        txtprice = new JTextField();
        txtprice.setBounds(620, 500, 270, 40);
        txtprice.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(txtprice);

        btnsave = new JButton("ADD");
        btnsave.setBackground(Color.WHITE);
        btnsave.setForeground(Color.BLACK);
        btnsave.setBounds(430, 600, 150, 40);
        btnsave.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnsave.addActionListener(this);
        add(btnsave);

        btnclear = new JButton("CLEAR");
        btnclear.setBackground(Color.WHITE);
        btnclear.setForeground(Color.BLACK);
        btnclear.setBounds(630, 600, 150, 40);
        btnclear.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnclear.addActionListener(this);
        add(btnclear);  
        
        back = new JButton("BACK");
        back.setForeground(Color.BLACK);
        back.setBackground(Color.WHITE);
        back.setBounds(830,600,120,40);
        back.setFont(new Font("Tahoma", Font.PLAIN, 20));
        back.addActionListener(this);
        add(back);

        setBounds(80,60,1300,900);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnsave) {
            String id = txtid.getText();
            id = "prod_"+id;
            String name = txtname.getText();
            String category = (String) txtcategory.getSelectedItem();
            String price = txtprice.getText();

            if (name.equals("") || price.equals("")) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields");
                return;
            }

            try {
            Conn conn = new Conn();
            
            // Check if the employee ID already exists in the database
            String checkQuery = "select * from products where product_id = '" + id + "'";
            ResultSet rs = conn.s.executeQuery(checkQuery);
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Product ID already exists", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String checkQuery2 = "select * from products where product_name = '" + name + "'";
            ResultSet rs2 = conn.s.executeQuery(checkQuery2);
            if (rs2.next()) {
                JOptionPane.showMessageDialog(null, "Product Name already exists", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String query = "insert into products values('"+id+"','"+name+"','"+category+"','"+price+"')";
            
            conn.s.executeUpdate(query);
            
            JOptionPane.showMessageDialog(null, "Product added successfully");
                 
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: Unable to add product");
            }
        } else if (ae.getSource() == btnclear) {
            txtid.setText("");
            txtname.setText("");
            txtprice.setText("");
        }else if(ae.getSource() == back){
        setVisible(false);
        new Admin_Dashboard();
        }
    }

    public static void main(String[] args) {
        new AddProduct();
    }
}

