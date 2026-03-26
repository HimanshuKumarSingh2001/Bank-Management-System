package bank.management.system;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;

public class MiniStatement extends JFrame implements ActionListener {
    JButton b1;
    String pin;

    MiniStatement(String pin) {
        this.pin = pin;

        setTitle("Mini Statement");

        b1 = new JButton("View Mini Statement");

        setLayout(null);

        b1.setBounds(100, 100, 200, 50);
        add(b1);

        b1.addActionListener(this);

        setSize(400, 300);
        setLocation(500, 300);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            Conn conn = new Conn();
            String query = "SELECT * FROM bank WHERE pin = '" + this.pin + "' ORDER BY date DESC LIMIT 10";
            ResultSet rs = conn.s.executeQuery(query);
            StringBuilder statement = new StringBuilder();

            while (rs.next()) {
                statement.append("Date: ").append(rs.getString("date"))
                        .append(" | Type: ").append(rs.getString("type"))
                        .append(" | Amount: ").append(rs.getString("amount"))
                        .append("\n");
            }

            // FIX: check empty
            if (statement.length() == 0) {
                JOptionPane.showMessageDialog(null, "No transactions found");
            } else {
                JOptionPane.showMessageDialog(null, statement.toString());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MiniStatement("").setVisible(true);
    }
}