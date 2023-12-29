package com.mycompany.prefa;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Prefa implements ActionListener{
    
    JFrame frame;
    JLabel lbl_title;
    JPanel pnl_title, pnl_button;
    JTable table;
    JScrollPane jsp;
    JButton btn;
    DefaultTableModel model;
    //SQL
    Connection conn;
    String db_url = "jdbc:mysql://localhost/prefaipt";
    String db_username = "root";
    String db_password = "";
    PreparedStatement stmt;
    ResultSet rs;
    
    public void fetch_data(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(db_url, db_username, db_password );
            String sql = "SELECT * FROM users";
            
            stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery();
            
            if(rs.next()){
                rs.beforeFirst();
                
                int columnCount = rs.getMetaData().getColumnCount();
                for(int i = 1; i <= columnCount; i++){
                    model.addColumn(rs.getMetaData().getColumnName(i));
                }
                
                while(rs.next()){
                    Object[] row = new Object[columnCount];
                    for (int i = 1; i <= columnCount; i++){
                        row[i - 1] = rs.getObject(i);
                    }
                    model.addRow(row);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "No data available");
            }
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Error Loading Driver" + ex);
        }
    }
    
    Prefa(){
        
        frame = new JFrame("Retrieve All Data");
        frame.setLayout(new BorderLayout());
        
        // Model
        model = new DefaultTableModel();
        table = new JTable(model);
        fetch_data();
        
        // North
        lbl_title = new JLabel("Retrieve All Data");
        pnl_title = new JPanel();
        pnl_title.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnl_title.add(lbl_title);
        
        // Button
        btn = new JButton("Update");
        btn.addActionListener(this);
        pnl_button = new JPanel();
        pnl_button.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pnl_button.add(btn);
        
        //Frame
        frame.add(pnl_title, BorderLayout.NORTH);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(pnl_button, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    

    public static void main(String[] args) {
        Prefa obj = new Prefa();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updatedata app = new updatedata();
    }
}