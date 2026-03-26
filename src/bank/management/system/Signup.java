package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Signup extends JFrame implements ActionListener {
    long random;
    JTextField nameField, fnameField, emailField;
    JButton next;
    JRadioButton male, female, other;
    JComboBox<String> day, month, year;

    Signup() {
        setTitle("Sign Up - Page 1");

        Random ran = new Random();
        random = Math.abs((ran.nextLong() % 9000L) + 1000L);

        JLabel formno = new JLabel("APPLICATION FORM NO. " + random);
        formno.setFont(new Font("Raleway", Font.BOLD, 20));
        formno.setBounds(100, 20, 400, 30);
        add(formno);

        JLabel name = new JLabel("Name:");
        name.setBounds(100, 70, 100, 30);
        add(name);

        nameField = new JTextField();
        nameField.setBounds(200, 70, 200, 30);
        add(nameField);

        JLabel fname = new JLabel("Father's Name:");
        fname.setBounds(100, 110, 100, 30);
        add(fname);

        fnameField = new JTextField();
        fnameField.setBounds(200, 110, 200, 30);
        add(fnameField);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(100, 150, 100, 30);
        add(genderLabel);

        male = new JRadioButton("Male");
        male.setBounds(200, 150, 60, 30);
        female = new JRadioButton("Female");
        female.setBounds(270, 150, 70, 30);
        other = new JRadioButton("Other");
        other.setBounds(350, 150, 70, 30);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        genderGroup.add(other);

        add(male);
        add(female);
        add(other);

        JLabel dob = new JLabel("Date of Birth:");
        dob.setBounds(100, 190, 100, 30);
        add(dob);

        String[] days = new String[31];
        for (int i = 1; i <= 31; i++)
            days[i - 1] = String.valueOf(i);
        day = new JComboBox<>(days);
        day.setBounds(200, 190, 60, 30);
        add(day);

        String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        month = new JComboBox<>(months);
        month.setBounds(270, 190, 70, 30);
        add(month);

        // ✅ FIXED HERE
        String[] years = new String[71];
        for (int i = 1950; i <= 2020; i++)
            years[i - 1950] = String.valueOf(i);
        year = new JComboBox<>(years);
        year.setBounds(350, 190, 80, 30);
        add(year);

        JLabel email = new JLabel("Email:");
        email.setBounds(100, 230, 100, 30);
        add(email);

        emailField = new JTextField();
        emailField.setBounds(200, 230, 200, 30);
        add(emailField);

        next = new JButton("Next");
        next.setBounds(300, 280, 100, 30);
        next.addActionListener(this);
        add(next);

        setLayout(null);
        setSize(500, 400);
        setLocation(500, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String formno = "" + random;
        String name = nameField.getText();
        String fname = fnameField.getText();

        String gender = null;
        if (male.isSelected()) gender = "Male";
        else if (female.isSelected()) gender = "Female";
        else if (other.isSelected()) gender = "Other";

        String dob = day.getSelectedItem() + "-" + month.getSelectedItem() + "-" + year.getSelectedItem();
        String email = emailField.getText();

        try {
            if (name.equals("")) {
                JOptionPane.showMessageDialog(null, "Name is Required");
            } else if (gender == null) {
                JOptionPane.showMessageDialog(null, "Please select Gender");
            } else {
                Conn conn = new Conn();
                String query = "insert into signup values('" + formno + "', '" + name + "', '" + fname + "', '" + gender + "', '" + dob + "', '" + email + "')";
                conn.s.executeUpdate(query);

                setVisible(false);
                new Signup2(formno).setVisible(true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Signup();
    }
}