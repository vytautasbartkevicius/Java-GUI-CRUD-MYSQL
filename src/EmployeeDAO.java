import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class EmployeeDAO {
    private JPanel Main;
    private JTextField txtname;
    private JTextField txtsalary;
    private JTextField textmobile;
    private JButton saveButton;
    private JTable table1;

    private JButton deleteButton;
    private JButton searchButton;
    private JScrollPane table_1;
    private JTextField txtid;

    Create create = new Create();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Employee");
        frame.setContentPane(new EmployeeDAO().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    Connection con;
    PreparedStatement pst;

    public void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/company", "root","");

        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }



    public EmployeeDAO() {

        Employee employee = new Employee();
        employee.getName();
        connect();

        saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                create.pack();
                create.setVisible(true);

            }

        });


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(txtid.getText().equals("")) {
                    table_load();
                }
                else{
                    try
                    {
                        String empid = txtid.getText();
                        //pst = con.prepareStatement("select id ,name,salary,mobile from employee where id = ?");
                        pst = con.prepareStatement("select id ,name,salary,mobile FROM employee WHERE name LIKE ?");
                        pst.setString(1, empid + "%");
                        ResultSet rs = pst.executeQuery();
                        table1.setModel(DbUtils.resultSetToTableModel(rs));
                    }
                    catch (SQLException a)
                    {
                        a.printStackTrace();
                    }
                }}
        });

    }

    private void table_load() {
        try
        {
            pst = con.prepareStatement("select * from employee");
            ResultSet rs = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}

