package goa.cafe.management;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;

public class ViewBill extends JFrame {

    private DefaultTableModel billTableModel;
    private JTable billTable;

    public ViewBill() {
        setTitle("View Bills(Double Click on particular row of bill to view the content of bill)");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);

        // Bill table
        billTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make the table cells non-editable
            }
        };
        billTableModel.addColumn("Bill ID");
        billTableModel.addColumn("Table Number");
        billTableModel.addColumn("Total Amount");
        billTableModel.addColumn("Date Created");

        billTable = new JTable(billTableModel);
        billTable.setRowHeight(30); // Set row height for better visibility

        JScrollPane scrollPane = new JScrollPane(billTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Add list selection listener to the table
        billTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = billTable.getSelectedRow();
                    if (selectedRow != -1) {
                        String billID = billTableModel.getValueAt(selectedRow, 0).toString();
                        viewBillContent(billID);
                    }
                }
            }
        });

        // Populate bill table
        populateBillTable();

        // Back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            dispose(); // Close the current frame
            new User_Dashboard(); // Open the new dashboard
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void populateBillTable() {
        try {
            Conn conn = new Conn();
            String query = "SELECT * FROM bill";
            ResultSet rs = conn.s.executeQuery(query);
            while (rs.next()) {
                String billID = rs.getString("bill_id");
                String tableNo = rs.getString("table_no");
                double totalAmount = rs.getDouble("total_amount");
                Timestamp createdAt = rs.getTimestamp("created_at");
                String dateCreated = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createdAt);
                billTableModel.addRow(new Object[]{billID, tableNo, totalAmount, dateCreated});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: Unable to fetch bills from database");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void viewBillContent(String billID) {
        try {
            Conn conn = new Conn();
            String query = "SELECT bill_content FROM bill WHERE bill_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, billID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String billContent = rs.getString("bill_content");
                // Display bill content in a JTextArea
                JTextArea textArea = new JTextArea(billContent);
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(600, 400));
                JOptionPane.showMessageDialog(this, scrollPane, "Bill Content: " + billID, JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Bill not found: " + billID);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error viewing bill content: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new ViewBill();
    }
}
