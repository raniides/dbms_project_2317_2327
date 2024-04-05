package goa.cafe.management;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

public class ManageProduct extends JFrame implements ActionListener {

    JTextField txtname, txtprice, txtCategory;
    JButton jButton1, btnupdate, btnclear, btndelete,back;
    JLabel jLabel1, jLabel2, jLabel3,jLabel5, txtid, jLabel6, lblCategory;
    JTable jTable1;
    JScrollPane jScrollPane1;
    DefaultTableModel originalTableModel;
    Conn conn;

    public ManageProduct() {
        getContentPane().setBackground(Color.ORANGE);
        setLayout(null);

        jLabel1 = new JLabel("Manage Product");
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 25));
        jLabel1.setBounds(200, 30, 250, 35);
        add(jLabel1);

        jLabel2 = new JLabel("ID");
        jLabel2.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jLabel2.setBounds(90, 120, 30, 30);
        add(jLabel2);

        txtid = new JLabel("00");
        txtid.setFont(new Font("Segoe UI", Font.BOLD, 18));
        txtid.setBounds(204, 120, 100, 30);
        add(txtid);

        jLabel3 = new JLabel("Name");
        jLabel3.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jLabel3.setBounds(90, 172, 100, 30);
        add(jLabel3);

        txtname = new JTextField();
        txtname.setFont(new Font("Segoe UI", Font.BOLD, 18));
        txtname.setBounds(204, 169, 340, 40);
        txtname.addActionListener(this);
        txtname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnameKeyReleased(evt);
            }
        });
        add(txtname);

        lblCategory = new JLabel("Category");
        lblCategory.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblCategory.setBounds(90, 221, 100, 30);
        add(lblCategory);

        txtCategory = new JTextField();
        txtCategory.setFont(new Font("Segoe UI", Font.BOLD, 18));
        txtCategory.setBounds(204, 218, 340, 40);
        txtCategory.setEditable(false);
        add(txtCategory);

        jLabel5 = new JLabel("Price");
        jLabel5.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jLabel5.setBounds(90, 270, 100, 30);
        add(jLabel5);

        txtprice = new JTextField();
        txtprice.setFont(new Font("Segoe UI", Font.BOLD, 18));
        txtprice.setBounds(204, 267, 340, 40);
        txtprice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpriceKeyReleased(evt);
            }
        });
        add(txtprice);

        btnupdate = new JButton("Update");
        btnupdate.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnupdate.setBounds(100, 350, 120, 40);
        btnupdate.setEnabled(false);
        btnupdate.addActionListener(this);
        add(btnupdate);

        btnclear = new JButton("Clear");
        btnclear.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnclear.setBounds(250, 350, 120, 40);
        btnclear.addActionListener(this);
        add(btnclear);

        btndelete = new JButton("Delete");
        btndelete.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btndelete.setBounds(400, 350, 120, 40);
        btndelete.setEnabled(false);
        btndelete.addActionListener(this);
        add(btndelete);
        
        back = new JButton("Back");
        back.setForeground(Color.BLACK);
        back.setBackground(Color.WHITE);
        back.setBounds(250,450,120,40);
        back.setFont(new Font("Tahoma", Font.PLAIN, 14));
        back.addActionListener(this);
        add(back);

        jTable1 = new JTable();
        jTable1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        originalTableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{
                        "ID", "NAME", "CATEGORY", "PRICE"
                }
        ) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable1.setModel(originalTableModel);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1 = new JScrollPane();
        jScrollPane1.setViewportView(jTable1);
        jScrollPane1.setBounds(660, 30, 700, 700);
        add(jScrollPane1);

        jLabel6 = new JLabel();
        jLabel6.setBounds(0, 0, 1400, 800);
        add(jLabel6);

        setSize(1400, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        conn = new Conn();
        updateTable();
        loadCategories();
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == jButton1) {
            new Admin_Dashboard().setVisible(true);
            dispose();
        } else if (ae.getSource() == btnupdate) {
            try {
                String query = "update products set product_name='" + txtname.getText() + "', category='" + txtCategory.getText() + "', price='" + txtprice.getText() + "' where product_id='" + txtid.getText() + "'";
                conn.s.executeUpdate(query);
                updateTable();
                JOptionPane.showMessageDialog(null, "Product updated successfully");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: Unable to update product");
            }
        } else if (ae.getSource() == btnclear) {
            clearFields();
        } else if (ae.getSource() == btndelete) {
            try {
                String query = "delete from products where product_id='" + txtid.getText() + "'";
                conn.s.executeUpdate(query);
                updateTable();
                JOptionPane.showMessageDialog(null, "Product deleted successfully");
                clearFields();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: Unable to delete product");
            }
        }else if(ae.getSource() == back){
        setVisible(false);
        new Admin_Dashboard();
        }
    }

    public void txtnameKeyReleased(java.awt.event.KeyEvent evt) {
        validateField();
    }

    public void txtpriceKeyReleased(java.awt.event.KeyEvent evt) {
        validateField();
    }

    public void validateField() {
        String name = txtname.getText();
        String price = txtprice.getText();
        if (!name.equals("") && !price.equals("")) {
            btnupdate.setEnabled(true);
        } else {
            btnupdate.setEnabled(false);
        }
    }

    public void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
        int index = jTable1.getSelectedRow();
        TableModel model = jTable1.getModel();
        String id = model.getValueAt(index, 0).toString();
        txtid.setText(id);
        String name = model.getValueAt(index, 1).toString();
        txtname.setText(name);
        String category = model.getValueAt(index, 2).toString();
        String price = model.getValueAt(index, 3).toString();
        txtprice.setText(price);
        txtCategory.setText(category);
        btnupdate.setEnabled(true);
        btndelete.setEnabled(true);
    }

    public void updateTable() {
        try {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            ResultSet rs = conn.s.executeQuery("select * from products");
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("product_id"));
                v.add(rs.getString("product_name"));
                v.add(rs.getString("category"));
                v.add(rs.getString("price"));
                model.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Unable to fetch data from products table");
        }
    }

    public void clearFields() {
        txtid.setText("");
        txtname.setText("");
        txtprice.setText("");
        txtCategory.setText("");
        btnupdate.setEnabled(false);
        btndelete.setEnabled(false);
    }

    public void loadCategories() {
        // No need to load categories for text field
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new ManageProduct().setVisible(true);
        });
    }
}
