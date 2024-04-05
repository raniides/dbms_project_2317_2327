package goa.cafe.management;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;

public class Order extends JFrame implements ActionListener {

    JComboBox<String> productComboBox;
    JTextField txtQuantity, txtTotal;
    JButton btnAddToCart, btnPrint, back;
    JLabel jLabel1, jLabel2, jLabel3, jLabelTotal, lbltableno;
    JTable cartTable;
    DefaultTableModel cartTableModel;
    double totalPrice = 0; // Variable to keep track of total price
    Choice ctableno;
    int billId = 0;

    public Order() {
        getContentPane().setBackground(Color.ORANGE);
        setLayout(null);

        jLabel1 = new JLabel("Place Order");
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 25));
        jLabel1.setBounds(220, 40, 250, 35);
        add(jLabel1);

        jLabel2 = new JLabel("Product Name");
        jLabel2.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jLabel2.setBounds(90, 140, 160, 30);
        add(jLabel2);

        productComboBox = new JComboBox<>();
        productComboBox.setFont(new Font("Segoe UI", Font.BOLD, 18));
        productComboBox.setBounds(240, 140, 200, 40);
        add(productComboBox);

        jLabel3 = new JLabel("Quantity");
        jLabel3.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jLabel3.setBounds(90, 230, 160, 30);
        add(jLabel3);

        txtQuantity = new JTextField();
        txtQuantity.setFont(new Font("Segoe UI", Font.BOLD, 18));
        txtQuantity.setBounds(240, 230, 200, 40);
        add(txtQuantity);
        
        lbltableno = new JLabel("Table Number ");
        lbltableno.setBounds(90,320,160,30);
        lbltableno.setFont(new Font("Segoe UI",Font.BOLD,18));
        add(lbltableno);
        
        
        ctableno = new Choice();
        
        try {
            Conn conn = new Conn();
            
            String query = "select * from Tablee";
            
            ResultSet rs = conn.s.executeQuery(query);
            
            while(rs.next()){
                ctableno.add(rs.getString("Table_no"));
                
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        ctableno.setBounds(240,320,170,30);
        ctableno.setFont(new Font("Tahoma", Font.PLAIN, 16)); 
        add(ctableno);
        
        btnAddToCart = new JButton("Add To Cart");
        btnAddToCart.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnAddToCart.setBounds(190, 410, 200, 40);
        btnAddToCart.addActionListener(this);
        add(btnAddToCart);

        jLabelTotal = new JLabel("Total");
        jLabelTotal.setFont(new Font("Segoe UI", Font.BOLD, 20));
        jLabelTotal.setBounds(90, 500, 160, 30);
        add(jLabelTotal);

        txtTotal = new JTextField("0.00");
        txtTotal.setFont(new Font("Segoe UI", Font.BOLD, 18));
        txtTotal.setBounds(240, 500, 200, 40);
        txtTotal.setEditable(false);
        add(txtTotal);

        back = new JButton("Back");
        back.setForeground(Color.BLACK);
        back.setBackground(Color.WHITE);
        back.setBounds(90, 570, 200, 40);
        back.setFont(new Font("Tahoma", Font.PLAIN, 14));
        back.addActionListener(this);
        add(back);

        btnPrint = new JButton("Print Bill");
        btnPrint.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnPrint.setBounds(350, 570, 200, 40);
        btnPrint.addActionListener(this);
        add(btnPrint);

        cartTableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Product Name", "Quantity", "Price"}
        );
        cartTable = new JTable(cartTableModel);
        JScrollPane scrollPane = new JScrollPane(cartTable);
        scrollPane.setBounds(620, 120, 700, 600);
        add(scrollPane);

        setSize(1400, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        populateProductComboBox();
        fetchLatestBillId();
    }

    private void populateProductComboBox() {
        try {
            Conn conn = new Conn();

            String query = "select product_name from products";
            ResultSet rs = conn.s.executeQuery(query);
            while (rs.next()) {
                String productName = rs.getString("product_name");
                productComboBox.addItem(productName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Unable to fetch product names from database");
        }
    }

    private void fetchLatestBillId() {
        try {
            Conn conn = new Conn();
            String query = "SELECT MAX(bill_id) AS max_id FROM bill";
            ResultSet rs = conn.s.executeQuery(query);
            if (rs.next()) {
                billId = rs.getInt("max_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnAddToCart) {
            addToCart();
        } else if (ae.getSource() == back) {
            setVisible(false);
            new User_Dashboard();
        } else if (ae.getSource() == btnPrint) {
            printBill();
        }
    }

    public void addToCart() {
        String productName = (String) productComboBox.getSelectedItem();
        String quantityStr = txtQuantity.getText();
        if (productName.isEmpty() || quantityStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a product and enter quantity.");
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityStr);
            double price = getPriceFromDatabase(productName);
            double productTotal = price * quantity;

            cartTableModel.addRow(new Object[]{productName, quantity, productTotal});

            totalPrice += productTotal;
            txtTotal.setText(String.valueOf(totalPrice));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid quantity.");
        }
    }

    private double getPriceFromDatabase(String productName) {
        try {
            Conn conn = new Conn();

            String query = "select price from products where product_name = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, productName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Unable to fetch product price from database");
        }
        return 0.0;
    }
    
    private String generateBillId() {
    billId++; // Increment billId
    return "bill" + billId;
}



    private void printBill() {
    StringBuilder billContent = new StringBuilder();
    double total = 0;
    
    // Generate bill_id with prefix "bill"
    String billIdWithPrefix = generateBillId();
    
    // Append table no to the bill
    String tableno = ctableno.getSelectedItem();
    billContent.append("\n Table no: ").append(tableno);
    
    billContent.append("\n\nYour Order:\n\n");
    for (int i = 0; i < cartTableModel.getRowCount(); i++) {
        String productName = cartTableModel.getValueAt(i, 0).toString();
        int quantity = (int) cartTableModel.getValueAt(i, 1);
        double price = (double) cartTableModel.getValueAt(i, 2);
        total += price;
        billContent.append(productName).append(": ").append(quantity).append(" x $").append(price).append("\n");
    }
    billContent.append("\nTotal: $").append(total);

    try {
        Conn conn = new Conn();

        String query = "INSERT INTO bill (bill_id, table_no, total_amount, bill_content) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, billIdWithPrefix);
        pstmt.setString(2, tableno);
        pstmt.setDouble(3, total);
        pstmt.setString(4, billContent.toString());
        pstmt.executeUpdate();

        JOptionPane.showMessageDialog(this, "Bill saved successfully to the database!", "Success", JOptionPane.INFORMATION_MESSAGE);
        setVisible(false);
            new User_Dashboard();
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error saving bill to database!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}


    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new Order();
        });
    }
}
