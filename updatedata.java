package com.mycompany.prefa;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

public class updatedata implements ActionListener{
    
    JFrame frame;
    JLabel lbl_title, lbl_id;
    JPanel pnl_title, pnl_center, pnl_center1, pnl_south, pnl_inside, pnl_inside2;
    JButton btn, btn_get;
    JTextField tf1;
    
    // Declare pnl_inside(South)
    JLabel lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8, lbl9, lbl10;
    JTextField tf2, tf3, tf4,tf5,tf6,tf7,tf8,tf9,tf11;
    JPanel pnl1, pnl2, pnl3, pnl4, pnl5, pnl6, pnl7, pnl8, pnl9, pnl10;
    JTextArea txt1;
    JComboBox<String> comboBox;
    
    // Declare pnl_inside2
    JButton btn_cancel, btn_update;
    
    //SQL
    Connection conn;
    String db_url = "jdbc:mysql://localhost/prefaipt";
    String db_username = "root";
    String db_password = "";
    PreparedStatement stmt;
    ResultSet rs;
    
    updatedata(){
        
        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        
        //North
        pnl_title = new JPanel();
        pnl_title.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        lbl_title = new JLabel("Retrieve A Data");
        pnl_title.add(lbl_title);
        
        //Center
        pnl_center = new JPanel();
        pnl_center.setLayout(new BoxLayout(pnl_center, BoxLayout.Y_AXIS));
        // Button
        btn = new JButton("Back");
        btn.addActionListener(this);
        
        pnl_center1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        lbl_id = new JLabel("Enter Car Plate: ");
        
        btn_get = new JButton("Get");
        btn_get.addActionListener(this);
        
        tf1 = new JTextField(20);
        pnl_center1.add(lbl_id);
        pnl_center1.add(tf1);
        pnl_center1.add(btn);
        pnl_center1.add(btn_get);
        
        pnl_center.add(pnl_center1);
        
        
        
        // South side
        pnl_south = new JPanel();
        pnl_south.setLayout(new BoxLayout(pnl_south, BoxLayout.Y_AXIS));
        pnl_south.setVisible(false);
        
        pnl_inside = new JPanel();
        pnl_inside.setLayout(new BoxLayout(pnl_inside, BoxLayout.Y_AXIS));
        
        lbl1 = new JLabel("Car Plate: ");
        lbl1.setPreferredSize(new Dimension(150, 15));
        
        lbl2 = new JLabel("Car Make: ");
        lbl2.setPreferredSize(new Dimension(150, 15));
        
        lbl3 = new JLabel("Car Model: ");
        lbl3.setPreferredSize(new Dimension(150, 15));
        
        lbl4 = new JLabel("Current Mileage: ");
        lbl4.setPreferredSize(new Dimension(150, 15));
        
        lbl5 = new JLabel("Service Date: ");
        lbl5.setPreferredSize(new Dimension(150, 15));
        
        lbl6 = new JLabel("Next Service Mileage: ");
        lbl6.setPreferredSize(new Dimension(150, 15));
        
        lbl7 = new JLabel("Next Service Date: ");
        lbl7.setPreferredSize(new Dimension(150, 15));
        
        lbl8 = new JLabel("Customer Name: ");
        lbl8.setPreferredSize(new Dimension(150, 15));
        
        lbl9 = new JLabel("Customer Contact No: ");
        lbl9.setPreferredSize(new Dimension(150, 15));
        
        lbl10 = new JLabel("Remarks: ");
        lbl10.setPreferredSize(new Dimension(150, 15));
        
        tf2 = new JTextField(20);
        String[] comboBoxItems = {"Perodua", "Proton", "Toyota", "Honda", "Mitsubishi"};
        comboBox = new JComboBox<String>(comboBoxItems);
        comboBox.setEditable(true);
        tf4 = new JTextField(20);
        tf5 = new JTextField(20);
        tf6 = new JTextField(20);
        tf7 = new JTextField(20);
        tf8 = new JTextField(20);
        tf9 = new JTextField(20);
        txt1 = new JTextArea();
        txt1.setPreferredSize(new Dimension(200,200));
        tf11 = new JTextField(20);
        
        pnl1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnl2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnl3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnl4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnl5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnl6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnl7 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnl8 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnl9 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnl10 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        pnl1.add(lbl1);
        pnl1.add(tf2);
        
        pnl2.add(lbl2);
        pnl2.add(comboBox);
        
        pnl3.add(lbl3);
        pnl3.add(tf4);
        
        pnl4.add(lbl4);
        pnl4.add(tf11);
        
        pnl5.add(lbl5);
        pnl5.add(tf5);
        
        pnl6.add(lbl6);
        pnl6.add(tf6);
        
        pnl7.add(lbl7);
        pnl7.add(tf7);
        
        pnl8.add(lbl8);
        pnl8.add(tf8);
        
        pnl9.add(lbl9);
        pnl9.add(tf9);
        
        pnl10.add(lbl10);
        pnl10.add(txt1);
        
        
        pnl_inside.add(pnl1);
        pnl_inside.add(pnl2);
        pnl_inside.add(pnl3);
        pnl_inside.add(pnl4);
        pnl_inside.add(pnl5);
        pnl_inside.add(pnl6);
        pnl_inside.add(pnl7);
        pnl_inside.add(pnl8);
        pnl_inside.add(pnl9);
        pnl_inside.add(pnl10);
        
        pnl_south.add(pnl_inside);
        
        // Panel south 2
        pnl_inside2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        btn_cancel = new JButton("Cancel");
        btn_cancel.addActionListener(this);
        
        btn_update = new JButton("Update");
        btn_update.addActionListener(this);
        
        pnl_inside2.add(btn_cancel);
        pnl_inside2.add(btn_update);
        
        pnl_south.add(pnl_inside2);
        
        //Frame
        frame.add(pnl_title, BorderLayout.NORTH);
        frame.add(pnl_center, BorderLayout.CENTER);
        frame.add(pnl_south, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btn_get){
            pnl_south.setVisible(true);
            pnl_center.setVisible(false);
            frame.pack();
            
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(db_url, db_username, db_password );
                String sql = "SELECT * FROM users WHERE car_plate = ?";
                
                stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, 
                        ResultSet.CONCUR_UPDATABLE);
                stmt.setString(1, tf1.getText());
                rs = stmt.executeQuery();
                
                if (rs.next()){
                    rs.beforeFirst();
                    while(rs.next()){
                        tf2.setText(rs.getString("car_plate"));
                        comboBox.setSelectedItem(rs.getString("car_make"));
                        tf4.setText(rs.getString("car_model"));
                        tf11.setText(rs.getString("mileage"));
                        tf5.setText(rs.getString("service_date"));
                        tf6.setText(rs.getString("service_mileage"));
                        tf7.setText(rs.getString("next_service"));
                        tf8.setText(rs.getString("cust_name"));
                        tf9.setText(rs.getString("contact"));
                        txt1.setText(rs.getString("remarks"));
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"No data found");
                    tf1.setText("");
                }
                
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Error occured" +ex);
            }
            
        }
        if (e.getSource() == btn_cancel){
            pnl_center.setVisible(true);
            pnl_south.setVisible(false);
            tf1.setText("");
            frame.pack();
        }
        
        if(e.getSource() == btn){
            Prefa app = new Prefa();
            frame.dispose();
        }
        
        if(e.getSource() == btn_update){
            update();
        }
    }
    
    public void update() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(db_url, db_username, db_password);
            String sql = "UPDATE users SET car_make = ?, car_model = ?, mileage = ?, service_date = ?, "
                    + "service_mileage = ?, next_service = ?, cust_name = ?, contact = ?, remarks = ? "
                    + "WHERE car_plate = ?";
            stmt = conn.prepareStatement(sql);

            // Set values
            stmt.setString(1, comboBox.getSelectedItem().toString());
            stmt.setString(2, tf4.getText());
            stmt.setString(3, tf11.getText());
            stmt.setString(4, tf5.getText());
            stmt.setString(5, tf6.getText());
            stmt.setString(6, tf7.getText());
            stmt.setString(7, tf8.getText());
            stmt.setString(8, tf9.getText());
            stmt.setString(9, txt1.getText());
            stmt.setString(10, tf2.getText());

            int status = stmt.executeUpdate();

            if (status > 0) {
                JOptionPane.showMessageDialog(null, "Update success");
            } else {
                JOptionPane.showMessageDialog(null, "Update failed");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error occurred: " + ex);
        }
    }
}
