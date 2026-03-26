package bank.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Withdrawl extends JFrame implements ActionListener {
    JTextField t1;
    JButton b1, b2;
    String pin;

    Withdrawl(String pin) {
        this.pin = pin;
        setTitle("Withdraw Money");

        JLabel l1 = new JLabel("Enter Amount to Withdraw");
        l1.setFont(new Font("System", Font.BOLD, 14));
        l1.setForeground(Color.BLACK);

        t1 = new JTextField();

        b1 = new JButton("Withdraw");
        b2 = new JButton("Back");

        setLayout(null);
        l1.setBounds(60, 60, 300, 30);
        t1.setBounds(60, 100, 300, 30);
        b1.setBounds(60, 150, 150, 30);
        b2.setBounds(210, 150, 150, 30);

        add(l1);
        add(t1);
        add(b1);
        add(b2);

        b1.addActionListener(this);
        b2.addActionListener(this);

        setSize(400, 300);
        setLocation(500, 300);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {

            // FIX: handle back button
            if (e.getSource() == b2) {
                setVisible(false);
                new Transactions(pin).setVisible(true);
                return;
            }

            String amount = t1.getText();

            // FIX: validation
            if (amount.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter amount");
                return;
            }

            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from bank where pin = '" + this.pin + "'");
            int balance = 0;

            while(rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }

            if (balance < Integer.parseInt(amount)) {
                JOptionPane.showMessageDialog(null, "Insufficient Balance");
                return;
            }

            Date date = new Date(System.currentTimeMillis());
            conn.s.executeUpdate("insert into bank values('" + pin + "', '" + date + "', 'Withdrawal', '" + amount + "')");
            JOptionPane.showMessageDialog(null, "Rs. " + amount + " Withdrawn Successfully");
            setVisible(false);
            new Transactions(pin).setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Withdrawl("").setVisible(true);
    }
}