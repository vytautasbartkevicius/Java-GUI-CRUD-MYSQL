import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Create extends JDialog {
    private JPanel contentPane;
    private JTextField txtname;
    private JTextField txtsalary;
    private JTextField txtmobile;
    private JButton createButton;
    private JButton buttonOK;
    private JButton buttonCancel;

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



    public Create() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);


        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connect();
                Employee employee = new Employee();
                employee.getName();
                String name = employee.getName();
                name = txtname.getText();


                String salary = employee.getSalary();
                salary = txtsalary.getText();


                String mobile = employee.getMobile();
                mobile = txtmobile.getText();
                try {
                    pst = con.prepareStatement("insert into employee(name,salary,mobile)values(?,?,?)");



                    pst.setString(1, name);
                    pst.setString(2, salary);
                    pst.setString(3, mobile);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Added");

                    txtname.setText("");
                    txtsalary.setText("");
                    txtmobile.setText("");
                    txtname.requestFocus();
                }

                catch (SQLException e1)
                {

                    e1.printStackTrace();
                }
                dispose();
            }



        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        Create dialog = new Create();

        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
