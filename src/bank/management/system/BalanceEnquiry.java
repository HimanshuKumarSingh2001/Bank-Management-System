package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BalanceEnquiry extends JFrame implements ActionListener {

    JButton back;
    String pin;

    BalanceEnquiry(String pin) {
        this.pin = pin;

        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel text = new JLabel();
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(60, 40, 400, 30);
        add(text);

        int balance = 0;

        try {
            Conn c = new Conn();
            PreparedStatement ps = c.c.prepareStatement("select * from bank where pin = ?");
            ps.setString(1, pin);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String type = rs.getString("type");
                int amount = Integer.parseInt(rs.getString("amount"));
                balance += type.equals("Deposit") ? amount : -amount;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        text.setText("Your current Account Balance is Rs " + balance);

        back = new JButton("Back");
        back.setBounds(200, 100, 150, 30);
        back.addActionListener(this);
        add(back);

        setSize(500, 300);
        setLocation(500, 250);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Transactions(pin).setVisible(true);
    }
}