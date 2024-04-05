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

public class AddProduct extends JFrame implements ActionListener {

    JTextField txtname, txtprice;
    JComboBox txtcategory;
    JButton btnsave, btnclear,back;

    AddProduct() {

        getContentPane().setBackground(Color.ORANGE);
        setLayout(null);

        JLabel heading = new JLabel("ADD NEW PRODUCT");
        heading.setBounds(520, 100, 400, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 30));
        add(heading);

        JLabel lblname = new JLabel("Product Name");
        lblname.setBounds(370, 200, 220, 35);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 25));
        add(lblname);

        txtname = new JTextField();
        txtname.setBounds(620, 200, 270, 40);
        txtname.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(txtname);

        JLabel lblcategory = new JLabel("Category");
        lblcategory.setBounds(370, 300, 220, 35);
        lblcategory.setFont(new Font("Tahoma", Font.PLAIN, 25));
        add(lblcategory);

        String[] categories = { "Soft Drinks", "Snacks", "Beverages","FastFood","Desert"}; //Add more categories here if need
        txtcategory = new JComboBox(categories);
        txtcategory.setBounds(620, 300, 190, 40);
        txtcategory.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(txtcategory);

        JLabel lblprice = new JLabel("Price");
        lblprice.setBounds(370, 400, 270, 35);
        lblprice.setFont(new Font("Tahoma", Font.PLAIN, 25));
        add(lblprice);

        txtprice = new JTextField();
        txtprice.setBounds(620, 400, 270, 40);
        txtprice.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(txtprice);

        btnsave = new JButton("SAVE");
        btnsave.setBackground(Color.WHITE);
        btnsave.setForeground(Color.BLACK);
        btnsave.setBounds(430, 500, 150, 40);
        btnsave.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnsave.addActionListener(this);
        add(btnsave);

        btnclear = new JButton("CLEAR");
        btnclear.setBackground(Color.WHITE);
        btnclear.setForeground(Color.BLACK);
        btnclear.setBounds(630, 500, 150, 40);
        btnclear.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnclear.addActionListener(this);
        add(btnclear);
        
        back = new JButton("BACK");
        back.setForeground(Color.BLACK);
        back.setBackground(Color.WHITE);
        back.setBounds(830,500,120,40);
        back.setFont(new Font("Tahoma", Font.PLAIN, 20));
        back.addActionListener(this);
        add(back);

        setBounds(80,60,1300,900);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnsave) {
            String name = txtname.getText();
            String category = (String) txtcategory.getSelectedItem();
            String price = txtprice.getText();

            if (name.equals("") || price.equals("")) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields");
                return;
            }

            try {
            Conn conn = new Conn();
            
            String query = "insert into products(product_name,category,price) values('"+name+"','"+category+"','"+price+"')";
            
            conn.s.executeUpdate(query);
            
            JOptionPane.showMessageDialog(null, "Product added successfully");
              
            setVisible(false);
            new Admin_Dashboard();    
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: Unable to add product");
            }
        } else if (ae.getSource() == btnclear) {
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

