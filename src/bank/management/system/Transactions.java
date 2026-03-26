package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Transactions extends JFrame implements ActionListener {
    JButton b1, b2, b3, b4, b5, b6, b7;
    String pin;

    Transactions(String pin) {
        this.pin = pin;

        setTitle("ATM Transactions");

        b1 = new JButton("Deposit");
        b2 = new JButton("Withdraw");
        b3 = new JButton("Fast Cash");
        b4 = new JButton("Mini Statement");
        b5 = new JButton("Pin Change");
        b6 = new JButton("Balance Enquiry");
        b7 = new JButton("Exit");

        setLayout(new GridLayout(8, 1, 10, 10));



        add(new JLabel("ATM MENU", JLabel.CENTER));

        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        add(b6);
        add(b7);

        Font f = new Font("Arial", Font.BOLD, 16);

        b1.setFont(f);
        b2.setFont(f);
        b3.setFont(f);
        b4.setFont(f);
        b5.setFont(f);
        b6.setFont(f);
        b7.setFont(f);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);

        setSize(400, 500);
        setLocationRelativeTo(null);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            setVisible(false);
            new Deposit(pin).setVisible(true);

        } else if (e.getSource() == b2) {
            setVisible(false);
            new Withdrawl(pin).setVisible(true);

        } else if (e.getSource() == b3) {
            setVisible(false);
            new FastCash(pin).setVisible(true);

        } else if (e.getSource() == b4) {
            setVisible(false);
            new MiniStatement(pin).setVisible(true);

        } else if (e.getSource() == b5) {
            setVisible(false);
            new PinChange(pin).setVisible(true);

        } else if (e.getSource() == b6) {
            setVisible(false);
            new BalanceEnquiry(pin).setVisible(true);

        } else if (e.getSource() == b7) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Transactions("").setVisible(true);
    }
}