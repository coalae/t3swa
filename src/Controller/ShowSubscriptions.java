package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import Logic.FastBillConnection;
import Logic.IConnection;
import Logic.MockupConnection;
import Model.Customer;
import Model.Subscription;
import ProxyPattern.ProxyCustomerData;
import net.miginfocom.swing.MigLayout;

/**
 * Die Klasse ShowSubscriptions zeigt die Subscriptions von Customers an.
 * @author Martin Regenfelder
 *
 */
public class ShowSubscriptions extends JFrame {
	/**
	 * Instanzvariablen
	 */
	private static final long serialVersionUID = 1L;
	JButton btnBackToMain;
	JButton subscriptions;
	String mode;
	ArrayList<Customer> customerList;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel model = null;
	IConnection con;

	/**
	 * Create application.
	 */
	public ShowSubscriptions(String mode, ArrayList<Customer> customerList) {
		this.mode = mode;
		this.customerList = customerList;
		this.setVisible(true);
		initialize();
	}

	/**
	 * Initialize contents of frame.
	 */
	private void initialize() {

		// setBounds(100, 100, 450, 300);
		setBounds(200, 200, 650, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[grow][]", "[][][grow]"));

		JLabel lblCustomerList = new JLabel("List of all Subscriptions");
		getContentPane().add(lblCustomerList, "cell 0 0,grow");

		btnBackToMain = new JButton("Back");
		btnBackToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				setVisible(false);
				new MainMenu();
			}
		});

		subscriptions = new JButton("show Subscription(s)");
		
		/**
		 * Bei Klick auf den Button "show Subscription(s)" wird   
		 * auf das Fenster ListOfSubscriptions.java weitergeschaltet.
		 * @param ActionEvent e
		 */
		subscriptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rowIndex = table.getSelectedRow();
				if (rowIndex == -1) {
					JOptionPane.showMessageDialog(table, "Error, keine Zelle ausgewählt!", "Fehler bei der Auswahl!",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				ArrayList<Subscription> subscriptionList = con.getSubscriptionsOfCustomer(customerList.get(rowIndex));
				if (subscriptionList.size() == 0) {
					JOptionPane.showMessageDialog(table, "Error, der Kunde hat keine Subscriptions!", "Fehler bei der Auswahl!",
							JOptionPane.ERROR_MESSAGE);
					return;
				}else{
					dispose();
			      	setVisible(false);
			      	new ListOfSubscriptions(mode, subscriptionList);
				}
				
			}

		});

		getContentPane().add(btnBackToMain, "cell 1 0,grow");
		getContentPane().add(subscriptions, "cell 1 1,grow");

		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "cell 0 2,grow");

		// lade Daten mittels ProxyCustomerData, falls Customer Datenliste (aus
		// dem File) noch keine Daten hat
		if (this.mode.equals("test") && customerList.size() == 0) {
			ProxyCustomerData customerData = new ProxyCustomerData();
			this.customerList = customerData.loadCustomerData();
		}

		if (this.mode.equals("test")) {
			this.con = new MockupConnection();
		} else {
			this.con = new FastBillConnection();
		}
		
		
		// Inhalte fuer JTable vorbereiten
		String title[] = { "Customer Nr.", "Type", "Organization", "Last Name", "Number of subscriptions" };
		String data[][] = new String[customerList.size()][5];

		for (int row = 0; row < customerList.size(); row++) {
			int customerNr = customerList.get(row).getId();
			ArrayList<Subscription> subscriptionList = this.con.getSubscriptionsOfCustomer(customerList.get(row));
			data[row][0] = String.valueOf(customerNr);
			data[row][1] = customerList.get(row).getCustomerType();
			data[row][2] = customerList.get(row).getOrganization();
			data[row][3] = customerList.get(row).getLastname();
			data[row][4] = Integer.toString(subscriptionList.size());
		}
		setTheDataInTheTable(data, title);
	}

	/**
	 * Mittels setTheDataInTheTable koennen die Dateninhalte (unter Verwendung
	 * der customerList) und die Spaltentitel im JTable gesetzt werden.
	 */
	public void setTheDataInTheTable(Object data[][], Object title[]) {

		model = new DefaultTableModel(data, title) {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}

			@Override
			public void fireTableCellUpdated(int row, int column) {

				super.fireTableCellUpdated(row, column);
			}
		};

		table = new JTable(model);
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setShowGrid(false);
		table.setBackground(UIManager.getColor("Button.background"));
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		scrollPane.setViewportView(table);

	}
}
