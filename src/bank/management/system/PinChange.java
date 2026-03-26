package bank.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class PinChange extends JFrame implements ActionListener {
    JLabel l1, l2;
    JPasswordField t1, t2;
    JButton b1, b2;
    String pin;

    PinChange(String pin) {
        this.pin = pin;
        setTitle("Change Pin");

        l1 = new JLabel("Enter New Pin");
        l2 = new JLabel("Re-enter New Pin");

        t1 = new JPasswordField();
        t2 = new JPasswordField();

        b1 = new JButton("Change Pin");
        b2 = new JButton("Back");

        l1.setFont(new Font("System", Font.BOLD, 14));
        l2.setFont(new Font("System", Font.BOLD, 14));

        l1.setForeground(Color.BLACK);
        l2.setForeground(Color.BLACK);

        setLayout(null);

        l1.setBounds(60, 60, 200, 30);
        l2.setBounds(60, 100, 200, 30);
        t1.setBounds(200, 60, 150, 30);
        t2.setBounds(200, 100, 150, 30);
        b1.setBounds(60, 150, 150, 30);
        b2.setBounds(210, 150, 150, 30);

        add(l1);
        add(l2);
        add(t1);
        add(t2);
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

            String newPin = new String(t1.getPassword()); // fixed
            String reEnterPin = new String(t2.getPassword()); // fixed

            if (newPin.equals(reEnterPin)) {
                Conn conn = new Conn();
                conn.s.executeUpdate("update account set pin = '" + newPin + "' where pin = '" + this.pin + "'");
                JOptionPane.showMessageDialog(null, "Pin Changed Successfully");
                setVisible(false);
                new Transactions(newPin).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Pin Mismatch. Try Again.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new PinChange("").setVisible(true);
    }
}