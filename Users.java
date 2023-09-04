import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JEditorPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.table.TableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Users {

	private JFrame frmCrudOperationsSwing;
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtAge;
	private JTextField txtCity;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Users window = new Users();
					window.frmCrudOperationsSwing.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Users() {
		initialize();
		Connect();
		loadData();
	}
	
	//Database Connection
	Connection con=null;
	PreparedStatement pst;
	ResultSet rs;
	private JTable table;
	
	
	public void Connect()
	{
		try {
			String url="jdbc:mysql://localhost:3306/jdbcdemo";
			String userName="root";
			String passWord="Test";
			 con =  DriverManager.getConnection(url,userName,passWord);  
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	// clear all
	public void clear() {
		txtID.setText("");
		txtName.setText("");
		txtAge.setText("");
		txtCity.setText("");
		txtName.requestFocus();
	}
	
	// Load Table
	
	public void loadData() {
		
		try {
			pst=con.prepareStatement("select *from users");
			rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCrudOperationsSwing = new JFrame();
		frmCrudOperationsSwing.setTitle("CRUD Operations  Swing Mysql");
		frmCrudOperationsSwing.setFont(new Font("Dialog", Font.PLAIN, 14));
		frmCrudOperationsSwing.setBounds(100, 100, 885, 440);
		frmCrudOperationsSwing.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCrudOperationsSwing.getContentPane().setLayout(null);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(0, 0, 808, 20);
		frmCrudOperationsSwing.getContentPane().add(editorPane);
		
		JLabel lblNewLabel = new JLabel("User Management System");
		lblNewLabel.setBounds(10, 27, 262, 30);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		frmCrudOperationsSwing.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(20, 65, 374, 274);
		frmCrudOperationsSwing.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Id");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 30, 68, 26);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Name");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(10, 67, 68, 26);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Age");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(10, 104, 68, 26);
		panel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("City");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1.setBounds(10, 141, 68, 26);
		panel.add(lblNewLabel_1_1_1_1);
		
		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(72, 32, 287, 26);
		panel.add(txtID);
		txtID.setColumns(10);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(72, 67, 287, 26);
		panel.add(txtName);
		
		txtAge = new JTextField();
		txtAge.setColumns(10);
		txtAge.setBounds(72, 104, 287, 26);
		panel.add(txtAge);
		
		txtCity = new JTextField();
		txtCity.setColumns(10);
		txtCity.setBounds(72, 143, 287, 26);
		panel.add(txtCity);
		
		//SaveButton Code
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id=txtID.getText();
				String name=txtName.getText();
				String age=txtAge.getText();
				String city=txtCity.getText();
				
				if(name==null || name.isEmpty() || name.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Please Enter Name");
					txtName.requestFocus();
				}
				if(age==null || age.isEmpty() || age.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Please Enter Age");
					txtAge.requestFocus();
				}
				if(city==null || city.isEmpty() || city.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Please Enter City");
					txtCity.requestFocus();
				}
				
				if(txtID.getText().isEmpty()) {
					
					try {
						String qry="insert into users (UNAME,AGE,CITY) VALUES(?,?,?)";
						pst=con.prepareStatement(qry);
						pst.setString(1,name);
						pst.setString(2, age);
						pst.setString(3,city);
					    pst.executeUpdate();
					    JOptionPane.showMessageDialog(null,"Data insert Success");
					    clear();
					    loadData();
						
					}
					catch(SQLException e1)
					{
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSave.setBounds(72, 180, 89, 33);
		panel.add(btnSave);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Delete Details
				String id = txtID.getText();
				if (!txtID.getText().isEmpty()) {
 
					int result = JOptionPane.showConfirmDialog(null, "Sure? You want to Delete?", "Delete",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
 
					if (result == JOptionPane.YES_OPTION) {
						try {
							String sql = "delete from users where ID=?";
							pst = con.prepareStatement(sql);
							pst.setString(1, id);
							pst.executeUpdate();
							JOptionPane.showMessageDialog(null, "Data Deleted Success");
							clear();
							loadData();
 
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelete.setBounds(270, 180, 89, 33);
		panel.add(btnDelete);
		
		// UDATE BUTTON CODE
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=txtID.getText();
				String name=txtName.getText();
				String age=txtAge.getText();
				String city=txtCity.getText();
				
				if(name==null || name.isEmpty() || name.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Please Enter Name");
					txtName.requestFocus();
				}
				if(age==null || age.isEmpty() || age.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Please Enter Age");
					txtAge.requestFocus();
				}
				if(city==null || city.isEmpty() || city.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Please Enter City");
					txtCity.requestFocus();
				}
				
				if(!txtID.getText().isEmpty()) {
					
					try {
						String qry="update users set UNAME=?,AGE=?,CITY=? where ID=?";
						pst=con.prepareStatement(qry);
						pst.setString(1,name);
						pst.setString(2, age);
						pst.setString(3,city);
						pst.setString(4, id);
					    pst.executeUpdate();
					    JOptionPane.showMessageDialog(null,"Data Update Success");
					    clear();
					    loadData();
						
					}
					catch(SQLException e1)
					{
						e1.printStackTrace();
					}
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdate.setBounds(171, 180, 89, 33);
		panel.add(btnUpdate);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(404, 66, 455, 273);
		frmCrudOperationsSwing.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				TableModel model = table.getModel();
				// ID NAME AGE CITY
				txtID.setText(model.getValueAt(index, 0).toString());
				txtName.setText(model.getValueAt(index, 1).toString());
				txtAge.setText(model.getValueAt(index, 2).toString());
				txtCity.setText(model.getValueAt(index, 3).toString());
			}
		});
		scrollPane.setViewportView(table);
	}
}
