package UpSystem.views;
import java.util.ArrayList;

import java.util.Calendar;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLayeredPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.JAXBException;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import UpSystem.model.Employee;
import UpSystem.model.model_UpSystem;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import net.miginfocom.swing.MigLayout;

public class Main_Screen extends JFrame {
	public ArrayList<JButton> wcButtons = new ArrayList<JButton>();
	JPanel mainContentPane;
	public JLabel lblPersonUpName;
	
	public JTextField txtOnDeck1;
	public JTextField txtOnDeck2;
	public JTextField txtOnDeck3;
	
	public JButton btnAddUser;
	public JButton btnTakeCustomer;
	public JButton btnNameWithCustomer;
	public JButton btnCheckLog;
	public JButton btnLogIn;
	public JButton btnLogOut;
	public JButton btnRemoveUser;
	public JButton btnChangeTime;
	public JButton newButton;
	private JLayeredPane withCustomerPanel;
	public JLayeredPane personUpPane;
	public JLayeredPane timePane;
	public JTextField timeTextField;
	
	public model_UpSystem theModel;
	
	/**
	 * Creates the frame.
	 */
	public Main_Screen() {
		
		initComponents();
	}
	
	// Initializes model
	public void addModel(model_UpSystem theModel) {
		this.theModel = theModel;
	}

	////////////////////////////////////////////////////////////////////////////////
	//     Contains all the code for creating and initializing components for GUI //
	////////////////////////////////////////////////////////////////////////////////
	private void initComponents() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main_Screen.class.getResource("/UpSystem/resources/icon_money_48px.png")));
		setTitle("Up System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		mainContentPane = new JPanel();
		mainContentPane.setToolTipText("Person currently up");
		mainContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setMinimumSize(new Dimension(600, 400));
		setMaximumSize(new Dimension(600, 400));
		
		this.setSize(getMaximumSize());
		this.setLocationRelativeTo(null);
		
		setContentPane(mainContentPane);
		
		personUpPane = new JLayeredPane();
		personUpPane.setBorder(new LineBorder(new Color(51, 204, 0), 3, true));
		
		timePane = new JLayeredPane();
		timePane.setName("Bkla\r\n");
		timePane.setBorder(new LineBorder(new Color(51, 0, 255), 2, true));
		
		withCustomerPanel = new JLayeredPane();
		withCustomerPanel.setBorder(new LineBorder(new Color(255, 153, 51), 3));
		
		JLayeredPane onDeckPanel = new JLayeredPane();
		onDeckPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "On Deck", TitledBorder.CENTER, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
		
		JLabel lblWithCustomers = new JLabel("Employees With Customers");
		lblWithCustomers.setPreferredSize(new Dimension(550, 14));
		lblWithCustomers.setHorizontalTextPosition(SwingConstants.CENTER);
		lblWithCustomers.setHorizontalAlignment(SwingConstants.CENTER);
		lblWithCustomers.setFont(new Font("Tahoma", Font.BOLD, 16));
		withCustomerPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblWithCustomers, btnNameWithCustomer}));
		
		GroupLayout gl_mainContentPane = new GroupLayout(mainContentPane);
		gl_mainContentPane.setHorizontalGroup(
			gl_mainContentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_mainContentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_mainContentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(withCustomerPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_mainContentPane.createSequentialGroup()
							.addComponent(personUpPane, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(onDeckPanel, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
						.addComponent(lblWithCustomers, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.addComponent(timePane, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_mainContentPane.setVerticalGroup(
			gl_mainContentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_mainContentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(timePane, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_mainContentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(onDeckPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(personUpPane, GroupLayout.PREFERRED_SIZE, 147, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblWithCustomers, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(withCustomerPanel, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE))
		);
		
		JLabel lblTimeLeft = new JLabel("Time Left");
		lblTimeLeft.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTimeLeft.setForeground(Color.BLUE);
		lblTimeLeft.setBounds(242, 11, 73, 14);
		timePane.add(lblTimeLeft);
		
		timeTextField = new JTextField();
		
		timeTextField.setEnabled(true);
		timeTextField.setFont(new Font("Tahoma", Font.BOLD, 32));
		timeTextField.setText("");
		timeTextField.setBounds(226, 30, 99, 49);
		timePane.add(timeTextField);
		timeTextField.setColumns(10);
		
		btnAddUser = new JButton("");
		btnAddUser.setBounds(10, 11, 38, 33);
		timePane.add(btnAddUser);
		btnAddUser.setIcon(new ImageIcon(Main_Screen.class.getResource("/UpSystem/resources/add_user_icon_24.png")));
		
		btnRemoveUser = new JButton("");

		btnRemoveUser.setIcon(new ImageIcon(Main_Screen.class.getResource("/UpSystem/resources/remove_user_icon_24.png")));
		btnRemoveUser.setBounds(58, 11, 38, 33);
		timePane.add(btnRemoveUser);
		
		btnChangeTime = new JButton("");
		btnChangeTime.setIcon(new ImageIcon(Main_Screen.class.getResource("/UpSystem/resources/cog_settings_24px.png")));
		btnChangeTime.setBounds(506, 11, 38, 33);
		timePane.add(btnChangeTime);
		withCustomerPanel.setLayout(new MigLayout("", "[]", "[][][]"));
		
		txtOnDeck1 = new JTextField();
		txtOnDeck1.setEditable(false);
		txtOnDeck1.setColumns(10);
		
		txtOnDeck2 = new JTextField();
		txtOnDeck2.setEditable(false);
		txtOnDeck2.setColumns(10);
		
		txtOnDeck3 = new JTextField();
		txtOnDeck3.setEditable(false);
		txtOnDeck3.setColumns(10);
		
		JLabel lblOnDeck1 = new JLabel("1");
		
		JLabel lblOnDeck2 = new JLabel("2");
		
		JLabel lblOnDeck3 = new JLabel("3");
		
		btnCheckLog = new JButton("Log");

		btnCheckLog.setIcon(new ImageIcon(Main_Screen.class.getResource("/UpSystem/resources/icon_log_32px.png")));
		
		GroupLayout gl_onDeckPanel = new GroupLayout(onDeckPanel);
		gl_onDeckPanel.setHorizontalGroup(
			gl_onDeckPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_onDeckPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_onDeckPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnCheckLog, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
						.addGroup(gl_onDeckPanel.createSequentialGroup()
							.addGroup(gl_onDeckPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblOnDeck2, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblOnDeck3)
								.addComponent(lblOnDeck1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_onDeckPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtOnDeck3, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
								.addComponent(txtOnDeck2, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
								.addComponent(txtOnDeck1, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_onDeckPanel.setVerticalGroup(
			gl_onDeckPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_onDeckPanel.createSequentialGroup()
					.addGroup(gl_onDeckPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOnDeck1)
						.addComponent(txtOnDeck1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_onDeckPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtOnDeck2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOnDeck2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_onDeckPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtOnDeck3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOnDeck3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCheckLog, GroupLayout.PREFERRED_SIZE, 34, Short.MAX_VALUE)
					.addContainerGap())
		);
		onDeckPanel.setLayout(gl_onDeckPanel);
		
		JLabel lblPersonUp = new JLabel("Person Up");
		lblPersonUp.setToolTipText("");
		lblPersonUp.setHorizontalAlignment(SwingConstants.CENTER);
		lblPersonUp.setEnabled(false);
		
		lblPersonUpName = new JLabel("(name)");
		lblPersonUpName.setToolTipText("Person up right now");
		lblPersonUpName.setHorizontalAlignment(SwingConstants.CENTER);
		lblPersonUpName.setEnabled(false);
		
		lblPersonUpName.setFont(new Font("Cooper Black", Font.ITALIC, 29));
		
		btnTakeCustomer = new JButton("Take Customer");
		btnTakeCustomer.setBackground(new Color(204, 255, 204));
		
		btnTakeCustomer.setIcon(new ImageIcon(Main_Screen.class.getResource("/UpSystem/resources/icon_take)customer_32px.png")));
		
		btnLogIn = new JButton("");

		btnLogIn.setIcon(new ImageIcon(Main_Screen.class.getResource("/UpSystem/resources/enter.png")));
		
		btnLogOut = new JButton("");

		btnLogOut.setIcon(new ImageIcon(Main_Screen.class.getResource("/UpSystem/resources/exit.png")));
		GroupLayout gl_personUpPane = new GroupLayout(personUpPane);
		gl_personUpPane.setHorizontalGroup(
			gl_personUpPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_personUpPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_personUpPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_personUpPane.createSequentialGroup()
							.addGroup(gl_personUpPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPersonUp, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
								.addComponent(lblPersonUpName, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
							.addGap(29)
							.addComponent(btnTakeCustomer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(27))
						.addGroup(gl_personUpPane.createSequentialGroup()
							.addComponent(btnLogIn, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 286, Short.MAX_VALUE)
							.addComponent(btnLogOut, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_personUpPane.setVerticalGroup(
			gl_personUpPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_personUpPane.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_personUpPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_personUpPane.createSequentialGroup()
							.addComponent(btnLogOut)
							.addGap(8)
							.addComponent(btnTakeCustomer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(68))
						.addGroup(gl_personUpPane.createSequentialGroup()
							.addComponent(btnLogIn)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblPersonUp, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(6)
							.addComponent(lblPersonUpName, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
							.addGap(44))))
		);
		personUpPane.setLayout(gl_personUpPane);
		mainContentPane.setLayout(gl_mainContentPane);
	
	}
	
	////////////////////////////////////////////////////////////////////////////
	//           Contains all the code for creating events
	///////////////////////////////////////////////////////////////////////////
	public void createEvents(ActionListener listenForButton) {
		
		btnLogIn.addActionListener(listenForButton);
		btnTakeCustomer.addActionListener(listenForButton);
		btnCheckLog.addActionListener(listenForButton);
		btnAddUser.addActionListener(listenForButton);
		btnLogOut.addActionListener(listenForButton);
		btnRemoveUser.addActionListener(listenForButton);
		btnChangeTime.addActionListener(listenForButton);
		timeTextField.addActionListener(listenForButton);
		
	}
	
	// Creates the Button functionality needed for employee to take a Customer
	public void createReturnButton(Employee emp) {
		newButton = new JButton("");
		
		newButton.setMinimumSize(new Dimension(75, 40)); 
		newButton.setText(emp.getName());
		newButton.setMaximumSize(new Dimension(75, 40));  
		wcButtons.add(newButton);

		withCustomerPanel.add(newButton, "cell 0 1"); 
		withCustomerPanel.repaint();
		
		// Add button functionality for taking report, following taking a customer
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean haveUpBool;
				
				Object o = arg0.getSource();
				
				String test3= JOptionPane.showInputDialog("Did you have an up, Y or N: ");
				if (test3 == null || test3.equals(""))
					return;
				
		        String test4= JOptionPane.showInputDialog("Please enter some notes: ");
		        if (test4 == null)
		        	return;
		        	        
		        String name = "";
		        
		        String timeStamp = new SimpleDateFormat("MM_dd_yyyy _ HH mm ss").format(Calendar.getInstance().getTime());
		        
		        String haveUp = test3;
		        String notes = test4;
		        
		       
		        if(haveUp.equals("Y") || haveUp.equals("y")) {
		        	haveUpBool = true;
		        } else {
		        	haveUpBool = false;
		        }  
		       
				System.out.println("CLICKED THE BUTTON!!");
				
				if(o instanceof JButton	) {
					newButton = (JButton)o;
					
					name = newButton.getText();
					wcButtons.remove(newButton);
					withCustomerPanel.remove(newButton);
					withCustomerPanel.repaint();					
				}
				
				 try {
						theModel.addReport(name, timeStamp, haveUpBool, notes);
					} catch (JAXBException e) {
						e.printStackTrace();
					}
			}
		});
	}
	
	// Method repaints the View to account for changes made to data that would impact
	// the GUI
	public void updateViewScreen() {
		withCustomerPanel.repaint();
		personUpPane.repaint();
		timePane.repaint();
	}

}
