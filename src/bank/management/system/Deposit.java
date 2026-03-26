package bank.management.system;

import java.sql.Date;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Deposit extends JFrame implements ActionListener {
    JTextField t1;
    JButton b1, b2;
    String pin;

    Deposit(String pin) {
        this.pin = pin;

        setTitle("Deposit Money");

        JLabel l1 = new JLabel("Enter Amount to Deposit");
        l1.setFont(new Font("System", Font.BOLD, 14));
        l1.setForeground(Color.BLACK);

        t1 = new JTextField();

        b1 = new JButton("Deposit");
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
        if (e.getSource() == b1) {
            try {
                String amount = t1.getText();

                if (amount.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter amount");
                    return;
                }

                Date date = new Date(System.currentTimeMillis());
                Conn conn = new Conn();
                conn.s.executeUpdate("insert into bank values('" + pin + "', '" + date + "', 'Deposit', '" + amount + "')");

                JOptionPane.showMessageDialog(null, "Rs. " + amount + " Deposited Successfully");
                setVisible(false);
                new Transactions(pin).setVisible(true);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == b2) {
            setVisible(false);
            new Transactions(pin).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Deposit("").setVisible(true);
    }
}