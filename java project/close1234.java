import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.sql.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.JButton;
import javax.swing.JPanel;

class managment extends JFrame implements ActionListener {

    private JButton button_start;

    public managment() {
        button_start = new JButton("Start");
        setLayout(new GridBagLayout());
        button_start.setPreferredSize(new Dimension(300, 100));
        add(button_start);
        button_start.addActionListener(this);

    }

    public void actionPerformed(ActionEvent ae) {
        admin_client s1 = new admin_client();
        s1.setTitle("Home Page");
        s1.setSize(300, 400);
        s1.setVisible(true);
    }

    public static void main(String args[]) {
        managment s1 = new managment();
        s1.setTitle("Hospital Management Project");
        s1.setSize(400, 200);
        s1.setVisible(true);
    }
}

class admin_client extends JFrame implements ActionListener {
    JButton admin;
    JButton user;
    JButton staff;

    public admin_client() {

        admin = new JButton("Admin");
        user = new JButton("user");
        staff = new JButton("staff");

        setLayout(new FlowLayout(FlowLayout.CENTER));

        Dimension buttonSize = new Dimension(250, 80);
        admin.setPreferredSize(buttonSize);
        user.setPreferredSize(buttonSize);
        staff.setPreferredSize(buttonSize);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(5, 5, 5, 5);

        add(admin, gbc);
        add(user, gbc);
        add(staff, gbc);

        admin.addActionListener(this);
        user.addActionListener(this);
        staff.addActionListener(this);

    }

    public void actionPerformed(ActionEvent ae) {
        String str = ae.getActionCommand();

        if (str.equals("Admin")) {
            inside_Admin s1 = new inside_Admin();
            s1.setTitle("Admin Panel");
            s1.setSize(300, 400);
            s1.setVisible(true);
        } else if (str.equals("user")) {
            inside_login1 s1 = new inside_login1();
            s1.setTitle("User Panel");
            s1.setSize(300, 300);
            s1.setVisible(true);
        } else if (str.equals("staff")) {
            inside_staff s1 = new inside_staff();
            s1.setTitle("Staff Panel");
            s1.setSize(300, 350);
            s1.setVisible(true);
        }
    }
}

class inside_login1 extends JFrame implements ActionListener {
    JButton login, signup;

    public inside_login1() {
        login = new JButton("login");
        signup = new JButton("sign up");
        setLayout(new GridBagLayout());
        login.setPreferredSize(new Dimension(250, 100));
        signup.setPreferredSize(new Dimension(250, 100));
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(5, 5, 5, 5);

        panel.add(login, gbc);
        panel.add(signup, gbc);
        add(panel);
        login.addActionListener(this);
        signup.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        String str = ae.getActionCommand();

        if (str.equals("login")) {
            inside_userlogin s1 = new inside_userlogin();
            s1.setTitle("Login Panel");
            s1.setSize(300, 350);
            s1.setVisible(true);
        } else {
            form12 s1 = new form12();
            s1.setTitle("Sign up");
            s1.setSize(400, 300);
            s1.setVisible(true);
        }
    }
}

class form12 extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4, l5;
    JButton signupJButton;
    JTextField t1, t2, t3, t4, t5;
    Connection con;
    Frame successFrame;

    public form12() {
        l5 = new JLabel("mobile number");
        l4 = new JLabel("email");
        l1 = new JLabel("Username:");
        l2 = new JLabel("Password:");
        l3 = new JLabel("Confirm Password:");
        t4 = new JTextField("", 14);
        t5 = new JTextField("", 14);
        t1 = new JTextField(14);
        t2 = new JTextField(14);
        t3 = new JTextField(14);
        signupJButton = new JButton("Sign Up");
        JPanel panel = new JPanel(new GridBagLayout());
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

        // Add components with GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(l1, gbc);
        gbc.gridx = 1;
        panel.add(t1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(l2, gbc);
        gbc.gridx = 1;
        panel.add(t2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(l3, gbc);
        gbc.gridx = 1;
        panel.add(t3, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(l5, gbc);
        gbc.gridx = 1;
        panel.add(t4, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(l4, gbc);
        gbc.gridx = 1;
        panel.add(t5, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2; // Span across 2 columns
        panel.add(signupJButton, gbc);
        add(panel);
        signupJButton.addActionListener(this);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/name_password"; // Replace 'name_password' with your database name
            String username = "root"; // Replace with your MySQL username
            String password = "Admin128@#"; // Replace with your MySQL password
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();

        if (action.equals("Sign Up")) {
            String username = t1.getText();
            String password = t2.getText();
            String confirmPassword = t3.getText();
            String mobileNumber = t4.getText();
            String email = t5.getText();

            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || mobileNumber.isEmpty()
                    || email.isEmpty()) {
                // Check if any of the fields are empty
                JOptionPane.showMessageDialog(this, "Please fill in all fields", "Validation Error",
                        JOptionPane.ERROR_MESSAGE);
            } else if (!password.equals(confirmPassword)) {
                // Check if password and confirm password match
                JOptionPane.showMessageDialog(this, "Passwords do not match", "Validation Error",
                        JOptionPane.ERROR_MESSAGE);
            } else if (checkExistingRecord(username, mobileNumber, email)) {
                // Check if username, mobile number, or email already exists in the database
                JOptionPane.showMessageDialog(this, "Username, mobile number, or email already exists",
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                // Insert the user data into the database
                String insertQuery = "INSERT INTO users (username, password, mobile_number, email) VALUES (?, ?, ?, ?)";
                try (PreparedStatement ps = con.prepareStatement(insertQuery)) {
                    ps.setString(1, username);
                    ps.setString(2, password);
                    ps.setString(3, mobileNumber);
                    ps.setString(4, email);
                    int rowsInserted = ps.executeUpdate();

                    if (rowsInserted > 0) {
                        // Display a success message in a new frame
                        after_user12 userFrame = new after_user12();
                        userFrame.setTitle("After User Login");
                        userFrame.setSize(800, 600);
                        userFrame.setVisible(true);
                    } else {
                        System.out.println("User insertion failed.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    // Add this method to check if username, mobile number, or email already exists
    private boolean checkExistingRecord(String username, String mobileNumber, String email) {
        try {
            String query = "SELECT * FROM users WHERE username = ? OR mobile_number = ? OR email = ?";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, username);
                ps.setString(2, mobileNumber);
                ps.setString(3, email);
                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next(); // If a record is found, return true
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // If an error occurs or no record is found, return false
    }

}

class inside_Admin extends JFrame implements ActionListener {
    Label l1, l2;
    JButton b1;
    TextField t1, t2;

    public inside_Admin() {
        l1 = new Label("username");
        l2 = new Label("password");
        t1 = new TextField(14);
        t2 = new TextField(14);
        b1 = new JButton("submit");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(l1, gbc);

        gbc.gridx = 1;
        add(t1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(l2, gbc);

        gbc.gridx = 1;
        add(t2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Span across 2 columns
        add(b1, gbc);

        b1.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        String str = ae.getActionCommand();

        if (str.equals("submit")) {
            String username = t1.getText();
            String password = t2.getText();

            if (username.equals("admin") && password.equals("abcde")) {
                after_login s1 = new after_login();
                s1.setTitle("Adminlogin panel");
                s1.setSize(300, 300);
                s1.setVisible(true);
            } else {
                // Show an error message using a new Frame
                Frame errorFrame = new Frame("Login Error");
                errorFrame.setSize(300, 100);
                errorFrame.setLayout(null);

                Label errorMsg = new Label("Invalid username or password!");

                errorMsg.setBounds(50, 20, 200, 30);
                errorFrame.add(errorMsg);

                errorFrame.setVisible(true);
                errorFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent we) {
                        // Close the error frame when the close Jbutton is clicked
                        errorFrame.dispose();
                    }
                });
            }
        }
    }
}

class inside_userlogin extends JFrame implements ActionListener {
    Label l1, l2;
    JButton b1, b2;
    TextField t1, t2;
    Connection con;

    public inside_userlogin() {
        l1 = new Label("username");
        l2 = new Label("password");
        t1 = new TextField(14);
        t2 = new TextField(14);
        t2.setEchoChar('*'); // Set password field to show * instead of actual characters
        b1 = new JButton("submit");
        b2 = new JButton("Sign up");
        // Create a panel to hold the components with GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(l1, gbc);

        gbc.gridy = 1;
        panel.add(l2, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span 2 columns
        panel.add(t1, gbc);

        gbc.gridy = 1;
        panel.add(t2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Reset gridwidth to 2
        panel.add(b1, gbc);

        gbc.gridy = 3;
        panel.add(b2, gbc);

        // Add the panel to the frame
        add(panel);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/name_password"; // Replace 'name_password' with your database
            String username = "root"; // Replace with your MySQL username
            String password = "Admin128@#"; // Replace with your MySQL password
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        b1.addActionListener(this);
        b2.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            String username = t1.getText();
            String password = t2.getText();
            // Check the database for a matching user
            String sql = "SELECT * FROM users WHERE BINARY username = ? AND BINARY password = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, username);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    // If a matching user is found, open a new window
                    after_user12 s1 = new after_user12();
                    s1.setSize(300, 300);
                    s1.setVisible(true);

                    // Close the current window (if needed)
                    dispose();
                } else {
                    // If no matching user is found, show an error message using a new frame
                    Frame errorFrame = new Frame("Login Error");
                    errorFrame.setSize(300, 100);
                    errorFrame.setLayout(null);

                    Label errorMsg = new Label("Invalid username or password!");
                    errorMsg.setBounds(50, 20, 200, 30);
                    errorFrame.add(errorMsg);
                    // Add a WindowListener to the error frame to handle the window closing event
                    errorFrame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent we) {
                            // Close the error frame when the close Jbutton is clicked
                            errorFrame.dispose();
                        }
                    });

                    errorFrame.setVisible(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == b2) {
            form12 s1 = new form12();
            s1.setTitle("Sign up");
            s1.setSize(400, 300);
            s1.setVisible(true);
        }
    }
}

class after_user12 extends JFrame implements ActionListener {
    JButton set_appointment;

    public after_user12() {
        set_appointment = new JButton("Set Appointment");

        setLayout(new GridBagLayout());
        set_appointment.setPreferredSize(new Dimension(300, 100));
        add(set_appointment);

        add(set_appointment);

        set_appointment.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == set_appointment) {
            // Create a new JFrame when the button is clicked
            set_appointment s1 = new set_appointment();
            s1.setTitle("Set Appointment");

            s1.setVisible(true);
        }
    }
}

class set_appointment extends JFrame implements ActionListener {
    JButton submitButton, cancelButton;
    JTextField nameField, ageField;
    JLabel nameLabel, ageLabel, doctorLabel, timeLabel;
    JComboBox<String> doctorDropdown;
    JList<String> timeSlots;
    Connection con;
    JDateChooser dateChooser;
    Date currentDate = new Date();

    public set_appointment() {
        setTitle("Set Appointment");
        setSize(400, 400);

        setLayout(new GridLayout(6, 2)); // Adjust the grid layout to accommodate the dateChooser

        // Create labels
        nameLabel = new JLabel("Name:");
        ageLabel = new JLabel("Age:");
        doctorLabel = new JLabel("Doctor:");
        timeLabel = new JLabel("Time Slot:");

        // Create text fields
        nameField = new JTextField(20);
        ageField = new JTextField(5);

        // Create buttons
        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");

        // Create doctor dropdown menu
        String[] doctors = { "Dr. Smith", "Dr. Johnson", "Dr. Brown" };
        doctorDropdown = new JComboBox<>(doctors);

        // Create time slots list
        String[] timeSlotOptions = { "9:00 AM - 10:00 AM", "10:00 AM - 11:00 AM", "11:00 AM - 12:00 PM" };
        timeSlots = new JList<>(timeSlotOptions);

        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd"); // You can set the desired date format
        JLabel dateLabel = new JLabel("Date:");

        // Add action listeners
        submitButton.addActionListener(this);
        cancelButton.addActionListener(this);

        add(nameLabel);
        add(nameField);
        add(ageLabel);
        add(ageField);
        add(doctorLabel);
        add(doctorDropdown);
        add(timeLabel);
        add(timeSlots);
        add(dateLabel);
        add(dateChooser);

        add(submitButton);
        add(cancelButton);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/doctersappointment"; // Replace 'name_password' with your database
            String username = "root"; // Replace with your MySQL username
            String password = "Admin128@#"; // Replace with your MySQL password
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            // Disable the submit button to prevent multiple submissions
            submitButton.setEnabled(false);

            String name = nameField.getText();
            String ageText = ageField.getText();
            String selectedDoctor = (String) doctorDropdown.getSelectedItem();
            String selectedTimeSlot = timeSlots.getSelectedValue();
            Date selectedDate = dateChooser.getDate();
            if (selectedDate.before(currentDate)) {
                showErrorMessage("Please select a future date.");
                submitButton.setEnabled(true); // Re-enable the submit button
                return; // Abort submission if the date is in the past
            }

            if (name.isEmpty() || ageText.isEmpty() || selectedDoctor == null || selectedTimeSlot == null
                    || selectedDate == null) {
                showErrorMessage("Please fill in all required fields");
                submitButton.setEnabled(true); // Re-enable the submit button
            } else {
                try {
                    int age = Integer.parseInt(ageText);

                    if (!isAppointmentLimitReached(selectedDoctor, selectedDate, selectedTimeSlot)) {
                        String insertQuery = "INSERT INTO appointments (name, age, doctor, time_slot, appointment_date) VALUES (?, ?, ?, ?, ?)";
                        try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
                            preparedStatement.setString(1, name);
                            preparedStatement.setInt(2, age);
                            preparedStatement.setString(3, selectedDoctor);
                            preparedStatement.setString(4, selectedTimeSlot);

                            java.sql.Date sqlDate = new java.sql.Date(selectedDate.getTime());
                            preparedStatement.setDate(5, sqlDate);

                            int rowsAffected = preparedStatement.executeUpdate();

                            if (rowsAffected > 0) {
                                showSuccessMessage("Appointment set successfully.");
                            } else {
                                showErrorMessage("Failed to set the appointment.");
                            }
                        } finally {
                            submitButton.setEnabled(true); // Re-enable the submit button
                        }
                    } else {
                        showAppointmentLimitReachedMessage();
                        submitButton.setEnabled(true); // Re-enable the submit button
                    }
                } catch (NumberFormatException ex) {
                    showErrorMessage("Please enter a valid age.");
                    submitButton.setEnabled(true); // Re-enable the submit button
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    submitButton.setEnabled(true); // Re-enable the submit button
                }
            }
        } else if (e.getSource() == cancelButton) {
            System.out.println("Cancel button clicked");
        }
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showAppointmentLimitReachedMessage() {
        JOptionPane.showMessageDialog(this, "Appointment limit for this slot is reached.", "Appointment Limit Reached",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private boolean isAppointmentLimitReached(String doctor, Date date, String timeSlot) {
        // Check the number of existing appointments for the same doctor, date, and time
        // slot
        try {
            String query = "SELECT COUNT(*) FROM appointments WHERE doctor = ? AND appointment_date = ? AND time_slot = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, doctor);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                preparedStatement.setDate(2, sqlDate);
                preparedStatement.setString(3, timeSlot);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int appointmentCount = resultSet.getInt(1);
                        return appointmentCount >= 5; // Return true if the limit is reached (5 appointments)
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}

class inside_staff extends JFrame implements ActionListener {
    Label l1, l2;
    JButton b1;
    TextField t1, t2;
    Connection con;

    public inside_staff() {
        l1 = new Label("username");
        l2 = new Label("password");
        t1 = new TextField(14);
        t2 = new TextField(14);
        t2.setEchoChar('*'); // Set password field to show * instead of actual characters
        b1 = new JButton("submit");
        setLayout(null);
        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(b1);

        l1.setBounds(50, 50, 200, 30);
        t1.setBounds(50, 100, 200, 30);
        l2.setBounds(50, 150, 200, 30);
        t2.setBounds(50, 200, 200, 30);
        b1.setBounds(50, 250, 200, 50);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/staff"; // Replace 'name_password' with your database
                                                              // name
            String username = "root"; // Replace with your MySQL username
            String password = "Admin128@#"; // Replace with your MySQL password
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        b1.addActionListener(this); // Register the ActionListener for the Jbutton
    }

    public void actionPerformed(ActionEvent ae) {
        String username = t1.getText();
        String password = t2.getText();

        // Check the database for a matching user
        String sql = "SELECT * FROM staff WHERE  username = ? AND  password = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Matching user found.");
                // If a matching user is found, open a new window
                inside_loginstaff s1 = new inside_loginstaff();
                s1.setSize(300, 300);
                s1.setVisible(true);
                // Close the current window (if needed)
                dispose();
            } else {
                    JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class inside_loginstaff extends JFrame implements ActionListener {
    JButton doctors;
    JButton nurses;
    JButton office;
    JButton Attendence;

    public inside_loginstaff() {
        office = new JButton("Office");
        doctors = new JButton("Doctors");
        nurses = new JButton("Nurses");
        Attendence = new JButton("Attendence");

        Dimension buttonSize = new Dimension(250, 80);
        office.setPreferredSize(buttonSize);
        doctors.setPreferredSize(buttonSize);
        nurses.setPreferredSize(buttonSize);
        Attendence.setPreferredSize(buttonSize);
        setLayout(new FlowLayout(FlowLayout.CENTER));

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(5, 5, 5, 5);

        add(office, gbc);
        add(doctors, gbc);
        add(nurses, gbc);
        add(Attendence, gbc);

        office.addActionListener(this);
        doctors.addActionListener(this);
        nurses.addActionListener(this);
        Attendence.addActionListener(this);

    }

    public void actionPerformed(ActionEvent ae) {
        String str = ae.getActionCommand();

        if (str.equals("Doctors")) {
            inside_doctors s1 = new inside_doctors();
            s1.setTitle("Docter's Appointment");
            s1.setSize(300, 300);
            s1.setVisible(true);
        } else if (str.equals("Office")) {
            inside_office s1 = new inside_office();
            s1.setTitle("Office Panel");
            s1.setSize(300, 300);
            s1.setVisible(true);
        } else if (str.equals("Nurses")) {
            inside_nurses s1 = new inside_nurses();
            s1.setTitle("nurse panel");
            s1.setSize(300, 300);
            s1.setVisible(true);
        } else if (str.equals("Attendence")) {
            AttendanceMark s1 = new AttendanceMark();

            s1.setVisible(true);
        }
    }
}

class AttendanceMark extends JFrame implements ActionListener {
    private DefaultListModel<String> model = new DefaultListModel<>();
    private JList<String> attendanceList = new JList<>(model);
    private JTextField nameField = new JTextField(20);
    private JButton markAttendanceButton = new JButton("Mark Attendance");
    private JButton generateReportButton = new JButton("Generate Report");

    private Connection conn;

    public AttendanceMark() {
        setTitle("Attendance System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);

        // Initialize the database connection
        initializeDatabase();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter Name: "));
        inputPanel.add(nameField);
        inputPanel.add(markAttendanceButton);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(attendanceList), BorderLayout.CENTER);

        markAttendanceButton.addActionListener(this);

        add(mainPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(generateReportButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        generateReportButton.addActionListener(this);

    }

    private void initializeDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/doctersappointment";
            String username = "root";
            String password = "Admin128@#";
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == markAttendanceButton) {
            String name = nameField.getText();
            if (!name.isEmpty()) {
                model.addElement(name);
                nameField.setText("");

                // Insert attendance record into the database
                insertAttendanceRecord(name);
            }
        } else if (e.getSource() == generateReportButton) {
            // Generate the PDF report
            generateAttendanceReport();
        }
    }

    private void generateAttendanceReport() {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("attendance_report.pdf"));
            document.open();

            document.add(new Paragraph("Attendance Report"));
            fetchDataFromDatabase(document);
            for (int i = 0; i < model.size(); i++) {
                document.add(new Paragraph(model.getElementAt(i)));
            }
            document.close();
            System.out.println("Attendance report generated as attendance_report.pdf");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void fetchDataFromDatabase(Document document) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/doctersappointment";
            String username = "root";
            String password = "Admin128@#";
            conn = DriverManager.getConnection(url, username, password);

            // Query the database to fetch attendance data
            String query = "SELECT name, date FROM attendance";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();

            // Iterate through the results and add them to the report
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String date = resultSet.getString("date");

                document.add(new Paragraph("Name: " + name));
                document.add(new Paragraph("Date: " + date));
                document.add(new Paragraph("----------------------------------"));
            }

            // Close the result set, statement, and database connection
            resultSet.close();
            stmt.close();
            // conn.close(); // Close the connection if needed

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertAttendanceRecord(String name) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = dateFormat.format(new Date());

            String sql = "INSERT INTO attendance (name, date) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, currentTime);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class inside_office extends JFrame implements ActionListener {
    JButton listDoctors;
    JButton listNurses;
    JButton salary;

    public inside_office() {
        salary = new JButton("Salary");
        listDoctors = new JButton("List Doctors");
        listNurses = new JButton("List Nurses");
        setLayout(new FlowLayout(FlowLayout.CENTER));

        Dimension buttonSize = new Dimension(250, 80);
        listDoctors.setPreferredSize(buttonSize);
        listNurses.setPreferredSize(buttonSize);
        salary.setPreferredSize(buttonSize);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(5, 5, 5, 5);

        add(listDoctors, gbc);
        add(listNurses, gbc);
        add(salary, gbc);

        listDoctors.addActionListener(this);
        listNurses.addActionListener(this);
        salary.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == listDoctors) {

            displayDoctorNamesFrame();
        } else if (ae.getSource() == listNurses) {
            // Handle List Nurses button
            displayListOfNurses();
        } else if (ae.getSource() == salary) {
            // Handle Salary button
        }
    }

    private void displayDoctorNamesFrame() {
        JFrame doctorFrame = new JFrame("Doctor List");
        doctorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/doctersappointment";
            String username = "root";
            String password = "Admin128@#";
            Connection con = DriverManager.getConnection(url, username, password);

            String query = "SELECT name FROM doctors";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            StringBuilder doctorNames = new StringBuilder();
            while (resultSet.next()) {
                String doctorName = resultSet.getString("name");
                doctorNames.append(doctorName).append("\n");
            }

            JTextArea textArea = new JTextArea(doctorNames.toString());
            textArea.setEditable(false);

            JScrollPane scrollPane = new JScrollPane(textArea);
            doctorFrame.add(scrollPane);

            resultSet.close();
            preparedStatement.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        doctorFrame.setSize(400, 400);
        doctorFrame.setVisible(true);
    }

    private void displayListOfNurses() {
        JFrame nurseFrame = new JFrame("Nurse List");
        nurseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        try {
            // Establish a database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/doctersappointment";
            String username = "root";
            String password = "Admin128@#";
            Connection con = DriverManager.getConnection(url, username, password);

            // Query to retrieve nurse names
            String query = "SELECT name FROM nurses";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Display the list of nurses
            StringBuilder nurseNames = new StringBuilder();
            while (resultSet.next()) {
                String nurseName = resultSet.getString("name");
                nurseNames.append(nurseName).append("\n");
            }

            JTextArea textArea = new JTextArea(nurseNames.toString());
            textArea.setEditable(false);

            JScrollPane scrollPane = new JScrollPane(textArea);
            nurseFrame.add(scrollPane);

            // Close resources (connection, statement, result set)
            resultSet.close();
            preparedStatement.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        nurseFrame.setSize(400, 400);
        nurseFrame.setVisible(true);
    }
}

class inside_doctors extends JFrame implements ActionListener {
    JButton listDoctors;
    JButton adddocter;
    JButton deletedocter;
    Connection con;

    public inside_doctors() {
        listDoctors = new JButton("List of Appointments");
        adddocter = new JButton("Add new docter");
        deletedocter = new JButton("Delete new docter");
        setLayout(new FlowLayout(FlowLayout.CENTER));

        add(listDoctors);
        add(adddocter);
        add(deletedocter);

        listDoctors.addActionListener(this);
        adddocter.addActionListener(this);
        deletedocter.addActionListener(this);
        try {
            String url = "jdbc:mysql://localhost:3306/doctersappointment"; // Replace 'name_password' with your database
                                                                           // name
            String username = "root"; // Replace with your MySQL username
            String password = "Admin128@#"; // Replace with your MySQL password
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == listDoctors) {
            // Prompt the doctor for their name
            String doctorName = JOptionPane.showInputDialog(this, "Enter your name:");

            if (doctorName != null && !doctorName.isEmpty()) {
                // Display appointments for the entered doctor's name
                displayAppointmentsForDoctor(doctorName);
            }
        } else if (ae.getSource() == adddocter) {
            // Open a new frame for adding a new doctor
            openDoctorRegistrationFrame();
        } else if (ae.getSource() == deletedocter) {
            // Prompt the doctor for their name to delete
            String doctorName = JOptionPane.showInputDialog(this, "Enter the doctor's name to delete:");

            if (doctorName != null && !doctorName.isEmpty()) {
                if (deleteDoctor(doctorName)) {
                    JOptionPane.showMessageDialog(this, "Doctor " + doctorName + " deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(this, "Doctor " + doctorName + " not found or deletion failed.");
                }
            }
        }
    }

    private boolean deleteDoctor(String doctorName) {
        String deleteQuery = "DELETE FROM doctors WHERE name = ?";

        try {
            // Get the doctor's data before deleting
            String selectQuery = "SELECT * FROM doctors WHERE name = ?";
            ResultSet resultSet = null;

            try (PreparedStatement selectStatement = con.prepareStatement(selectQuery)) {
                selectStatement.setString(1, doctorName);
                resultSet = selectStatement.executeQuery();

                if (resultSet.next()) {
                    // Doctor data found, now move it to the history table
                    String historyInsertQuery = "INSERT INTO history (name, speciality, mobile_number, email, aadhar_number) VALUES (?, ?, ?, ?, ?)";

                    try (PreparedStatement historyInsertStatement = con.prepareStatement(historyInsertQuery)) {
                        historyInsertStatement.setString(1, resultSet.getString("name"));
                        historyInsertStatement.setString(2, resultSet.getString("speciality"));
                        historyInsertStatement.setString(3, resultSet.getString("mobile_number"));
                        historyInsertStatement.setString(4, resultSet.getString("email"));
                        historyInsertStatement.setString(5, resultSet.getString("aadhar_number"));

                        historyInsertStatement.executeUpdate();
                    }
                }
            } finally {
                if (resultSet != null) {
                    resultSet.close();
                }
            }

            try (PreparedStatement preparedStatement = con.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, doctorName);
                int rowsAffected = preparedStatement.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private void openDoctorRegistrationFrame() {
        JFrame registrationFrame = new JFrame("Add New Doctor");
        registrationFrame.setLayout(new GridLayout(7, 2)); // Adjust rows as needed

        // Create components for doctor registration
        JLabel nameLabel = new JLabel("Doctor Name:");
        JTextField nameField = new JTextField(20);

        JLabel specialityLabel = new JLabel("Speciality:");
        JTextField specialityField = new JTextField(20);

        JLabel mobileLabel = new JLabel("Mobile Number:");
        JTextField mobileField = new JTextField(20);

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(20);

        JLabel educationLabel = new JLabel("Education:");
        JTextField educationField = new JTextField(20);

        JLabel aadharLabel = new JLabel("Aadhar Card:");
        JTextField aadharField = new JTextField(20);

        JButton registerButton = new JButton("Register");

        // Add components to the frame
        registrationFrame.add(nameLabel);
        registrationFrame.add(nameField);
        registrationFrame.add(specialityLabel);
        registrationFrame.add(specialityField);
        registrationFrame.add(mobileLabel);
        registrationFrame.add(mobileField);
        registrationFrame.add(emailLabel);
        registrationFrame.add(emailField);
        registrationFrame.add(educationLabel);
        registrationFrame.add(educationField);
        registrationFrame.add(aadharLabel);
        registrationFrame.add(aadharField);
        registrationFrame.add(registerButton);

        registerButton.addActionListener(e -> {
            // Get the entered values
            String doctorName = nameField.getText();
            String speciality = specialityField.getText();
            String mobile = mobileField.getText();
            String email = emailField.getText();
            String education = educationField.getText();
            String aadhar = aadharField.getText();

            if (doctorName.isEmpty() || speciality.isEmpty() || mobile.isEmpty() || email.isEmpty()
                    || education.isEmpty() || aadhar.isEmpty()) {
                // Display an error message if any of the fields are empty
                JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Perform the doctor registration process
                registerDoctor(doctorName, speciality, mobile, email, education, aadhar);
                registrationFrame.dispose();
            }
        });

        registrationFrame.setSize(600, 400); // Adjust the size as needed
        registrationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this frame when closed
        registrationFrame.setVisible(true);
    }

    private void registerDoctor(String doctorName, String speciality, String mobile, String email, String education,
            String aadhar) {
        try {
            String query = "INSERT INTO doctors (name, speciality, mobile_number, email, education, aadhar_number) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, doctorName);
            preparedStatement.setString(2, speciality);
            preparedStatement.setString(3, mobile);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, education);
            preparedStatement.setString(6, aadhar);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Doctor registration successful!");
            } else {
                JOptionPane.showMessageDialog(this, "Doctor registration failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displayAppointmentsForDoctor(String doctorName) {
        try {
            String query = "SELECT * FROM appointments WHERE doctor = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, doctorName);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Create a new frame to display the appointments
            JFrame appointmentFrame = new JFrame("Appointments for " + doctorName);
            appointmentFrame.setLayout(new BorderLayout());

            // Create a JTextArea to display the appointment information
            JTextArea appointmentTextArea = new JTextArea(20, 50);
            appointmentTextArea.setEditable(false);

            // Process the results and append appointment data to the JTextArea
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String timeSlot = resultSet.getString("time_slot");
                java.sql.Date appointmentDate = resultSet.getDate("appointment_date");

                // Append appointment data to the JTextArea
                appointmentTextArea.append("Name: " + name + "\n");
                appointmentTextArea.append("Age: " + age + "\n");
                appointmentTextArea.append("Time Slot: " + timeSlot + "\n");
                appointmentTextArea.append("Appointment Date: " + appointmentDate + "\n");
                appointmentTextArea.append("------------------------------\n");
            }

            // Add the JTextArea to the frame
            appointmentFrame.add(new JScrollPane(appointmentTextArea), BorderLayout.CENTER);

            // Set the size and make the frame visible
            appointmentFrame.setSize(600, 400);
            appointmentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this frame when closed
            appointmentFrame.setVisible(true);

            // Close database resources
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class inside_nurses extends JFrame implements ActionListener {
    JButton listNurses;
    JButton addNurses;
    JButton DeleteNurses;
    Connection con;

    public inside_nurses() {
        listNurses = new JButton("List of Doctors's Appointments");
        addNurses = new JButton("Add new Nurses");
        DeleteNurses = new JButton("Delete new Nurses");
        setLayout(new FlowLayout(FlowLayout.CENTER));

        add(listNurses);
        add(addNurses);
        add(DeleteNurses);

        listNurses.addActionListener(this);
        DeleteNurses.addActionListener(this);
        addNurses.addActionListener(this);
        try {
            String url = "jdbc:mysql://localhost:3306/doctersappointment"; // Replace 'name_password' with your database
                                                                           // name
            String username = "root"; // Replace with your MySQL username
            String password = "Admin128@#"; // Replace with your MySQL password
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == listNurses) {
            // Prompt the doctor for their name
            String doctorName = JOptionPane.showInputDialog(this, "Enter your name:");

            if (doctorName != null && !doctorName.isEmpty()) {
                // Display appointments for the entered doctor's name
                displayAppointmentsForDoctor(doctorName);
            }
        } else if (ae.getSource() == addNurses) {
            // Open a new frame for adding a new doctor
            openNurseRegistrationFrame();
        } else if (ae.getSource() == DeleteNurses) {
            String nurseName = JOptionPane.showInputDialog(this, "Enter the nurse's name to delete:");

            if (nurseName != null && !nurseName.isEmpty()) {
                if (deleteNurse(nurseName)) {
                    JOptionPane.showMessageDialog(this, "Nurse " + nurseName + " deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(this, "Nurse " + nurseName + " not found or deletion failed.");
                }
            }
        }
    }

    private void displayAppointmentsForDoctor(String doctorName) {
        try {
            String query = "SELECT * FROM appointments WHERE doctor = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, doctorName);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Create a new frame to display the appointments
            JFrame appointmentFrame = new JFrame("Appointments for " + doctorName);
            appointmentFrame.setLayout(new BorderLayout());

            // Create a JTextArea to display the appointment information
            JTextArea appointmentTextArea = new JTextArea(20, 50);
            appointmentTextArea.setEditable(false);

            // Process the results and append appointment data to the JTextArea
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String timeSlot = resultSet.getString("time_slot");
                java.sql.Date appointmentDate = resultSet.getDate("appointment_date");

                // Append appointment data to the JTextArea
                appointmentTextArea.append("Name: " + name + "\n");
                appointmentTextArea.append("Age: " + age + "\n");
                appointmentTextArea.append("Time Slot: " + timeSlot + "\n");
                appointmentTextArea.append("Appointment Date: " + appointmentDate + "\n");
                appointmentTextArea.append("------------------------------\n");
            }

            // Add the JTextArea to the frame
            appointmentFrame.add(new JScrollPane(appointmentTextArea), BorderLayout.CENTER);

            // Set the size and make the frame visible
            appointmentFrame.setSize(600, 400);
            appointmentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this frame when closed
            appointmentFrame.setVisible(true);

            // Close database resources
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean deleteNurse(String nurseName) {
        String deleteQuery = "DELETE FROM nurses WHERE name = ?";

        try {
            // Get the nurse's data before deleting
            String selectQuery = "SELECT * FROM nurses WHERE name = ?";
            ResultSet resultSet = null;

            try (PreparedStatement selectStatement = con.prepareStatement(selectQuery)) {
                selectStatement.setString(1, nurseName);
                resultSet = selectStatement.executeQuery();

                if (resultSet.next()) {
                    // Nurse data found, now move it to the history table
                    String historyInsertQuery = "INSERT INTO nurse_history (name, department, mobile_number, email, aadhar_number) VALUES (?, ?, ?, ?, ?)";

                    try (PreparedStatement historyInsertStatement = con.prepareStatement(historyInsertQuery)) {
                        historyInsertStatement.setString(1, resultSet.getString("name"));
                        historyInsertStatement.setString(2, resultSet.getString("department"));
                        historyInsertStatement.setString(3, resultSet.getString("mobile_number"));
                        historyInsertStatement.setString(4, resultSet.getString("email"));
                        historyInsertStatement.setString(5, resultSet.getString("aadhar_number"));

                        historyInsertStatement.executeUpdate();
                    }
                }
            } finally {
                if (resultSet != null) {
                    resultSet.close();
                }
            }

            // Delete the nurse data from the main table
            try (PreparedStatement preparedStatement = con.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, nurseName);
                int rowsAffected = preparedStatement.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private void openNurseRegistrationFrame() {
        JFrame registrationFrame = new JFrame("Add New Nurse");
        registrationFrame.setLayout(new GridLayout(6, 2));

        // Create components for nurse registration
        JLabel nameLabel = new JLabel("Nurse Name:");
        JTextField nameField = new JTextField(20);

        JLabel departmentLabel = new JLabel("Department:");
        JTextField departmentField = new JTextField(20);

        JLabel mobileLabel = new JLabel("Mobile Number:");
        JTextField mobileField = new JTextField(20);

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(20);

        JLabel aadharLabel = new JLabel("Aadhar Card Number:");
        JTextField aadharField = new JTextField(20);

        JButton registerButton = new JButton("Register");

        // Add components to the frame
        registrationFrame.add(nameLabel);
        registrationFrame.add(nameField);
        registrationFrame.add(departmentLabel);
        registrationFrame.add(departmentField);
        registrationFrame.add(mobileLabel);
        registrationFrame.add(mobileField);
        registrationFrame.add(emailLabel);
        registrationFrame.add(emailField);
        registrationFrame.add(aadharLabel);
        registrationFrame.add(aadharField);
        registrationFrame.add(registerButton);

        registerButton.addActionListener(e -> {
            // Get the entered values
            String nurseName = nameField.getText();
            String department = departmentField.getText();
            String mobileNumber = mobileField.getText();
            String email = emailField.getText();
            String aadharNumber = aadharField.getText();

            if (nurseName.isEmpty() || department.isEmpty() || mobileNumber.isEmpty() || email.isEmpty()
                    || aadharNumber.isEmpty()) {
                // Display an error message if any of the fields are empty
                JOptionPane.showMessageDialog(this, "All fields are required.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                // Perform the nurse registration process
                registerNurse(nurseName, department, mobileNumber, email, aadharNumber);
                registrationFrame.dispose();
            }
        });

        registrationFrame.setSize(600, 300);
        registrationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this frame when closed
        registrationFrame.setVisible(true);
    }

    private void registerNurse(String nurseName, String department, String mobileNumber, String email,
            String aadharNumber) {
        try {
            String query = "INSERT INTO nurses (name, department, mobile_number, email, aadhar_number) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, nurseName);
            preparedStatement.setString(2, department);
            preparedStatement.setString(3, mobileNumber);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, aadharNumber);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Nurse registration successful!");
            } else {
                JOptionPane.showMessageDialog(this, "Nurse registration failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class after_login extends JFrame implements ActionListener {
    JButton grant_permissions;
    JButton staff_username;

    public after_login() {
        grant_permissions = new JButton("grant_permissions");

        staff_username = new JButton("staff_username");

        setLayout(new GridBagLayout());
        grant_permissions.setPreferredSize(new Dimension(250, 100));
        staff_username.setPreferredSize(new Dimension(250, 100));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(5, 5, 5, 5);

        add(grant_permissions, gbc);
        add(staff_username, gbc);

        grant_permissions.addActionListener(this);
        staff_username.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        String str = ae.getActionCommand();

        if (str.equals("grant_permissions")) {
            inside_grantpermission s1 = new inside_grantpermission();
            s1.setSize(300, 300);
            s1.setVisible(true);
        } else if (str.equals("staff_username")) {
            inside_staffusername s1 = new inside_staffusername();
            s1.setTitle("Staff Id And Password");
            s1.setSize(600, 450);
            s1.setVisible(true);
        }
    }
}

class inside_grantpermission extends JFrame implements ActionListener {
    JButton permission;

    public inside_grantpermission() {
        permission = new JButton("permission");
        setLayout(new GridBagLayout());
        permission.setPreferredSize(new Dimension(250, 100));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(5, 5, 5, 5);

        add(permission, gbc);

        add(permission);

        permission.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

}

class inside_staffusername extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4, l5, l6;
    JButton signupJButton;
    JTextField t1, t2, t3, t4, t5, t6;
    Connection con;
    Frame successFrame;

    public inside_staffusername() {
        l5 = new JLabel("mobile number");
        l4 = new JLabel("email");
        l1 = new JLabel("Username:");
        l2 = new JLabel("Password:");
        l3 = new JLabel("Confirm Password:");
        l6 = new JLabel("post of staff");
        t6 = new JTextField(14);
        t4 = new JTextField("", 14);
        t5 = new JTextField("", 14);
        t1 = new JTextField(14);
        t2 = new JPasswordField(14);
        t3 = new JPasswordField(14);
        signupJButton = new JButton("Sign Up");

        setLayout(null);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(l1, gbc);

        gbc.gridx = 1;
        add(t1, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(l2, gbc);

        gbc.gridx = 1;
        add(t2, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(l3, gbc);

        gbc.gridx = 1;
        add(t3, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(l5, gbc);

        gbc.gridx = 1;
        add(t4, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(l4, gbc);

        gbc.gridx = 1;
        add(t5, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(l6, gbc);

        gbc.gridx = 1;
        add(t6, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2; // Span 2 columns
        add(signupJButton, gbc);

        signupJButton.addActionListener(this);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/name_password"; // Replace 'name_password' with your database name
            String username = "root"; // Replace with your MySQL username
            String password = "Admin128@#"; // Replace with your MySQL password
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signupJButton) {
            String username = t1.getText();
            String password = new String(((JPasswordField) t2).getPassword());
            String confirmPassword = new String(((JPasswordField) t3).getPassword());
            String mobileNumber = t4.getText();
            String email = t5.getText();
            String post = t6.getText();

            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || mobileNumber.isEmpty()
                    || email.isEmpty() || post.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Data is valid, insert into the database
                insertData(username, password, mobileNumber, email, post);

                // Show a success message
                JOptionPane.showMessageDialog(this, "Registration successful!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void insertData(String username, String password, String mobileNumber, String email, String post) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbcUrl = "jdbc:mysql://localhost:3306/staff"; // Replace 'staff' with your database name
            String jdbcUsername = "root"; // Replace with your MySQL username
            String jdbcPassword = "Admin128@#"; // Replace with your MySQL password
            con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);

            String sql = "INSERT INTO staff (username, password, mobilenumber, email, post) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, mobileNumber);
            preparedStatement.setString(5, post);
            preparedStatement.setString(4, email);

            int rowsAffected = preparedStatement.executeUpdate();

            preparedStatement.close();
            con.close();

            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully.");
            } else {
                System.out.println("No rows were affected. Data not inserted.");
            }

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while registering.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
