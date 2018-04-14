package Controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import Model.Customer;
import ProxyPattern.ProxyCustomerData;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

/**
 * ShowCustomerList zeigt die Customer Liste an je nach Auswahl des Konfigurationsmodus (mode).
 * @author Cordula Eggerth
 *
 */
public class ShowCustomerList extends JFrame {

	/**
	 * Instanzvariablen
	 */
	private static final long serialVersionUID = 1L;
	JButton btnBackToMain;
	String mode;
	ArrayList<Customer> customerList;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel model = null;

	/**
	 * Create application.
	 */
	public ShowCustomerList(String mode, ArrayList<Customer> customerList) {
		this.mode=mode;
		this.customerList=customerList;
		this.setVisible(true);
		initialize();
	}

	/**
	 * Initialize contents of frame.
	 */
	private void initialize() {
		
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[grow][]", "[][][grow]"));
		
		JLabel lblCustomerList = new JLabel("Customer List");
		getContentPane().add(lblCustomerList, "cell 0 0,grow");
		
		btnBackToMain = new JButton("Back");
		btnBackToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
      		  dispose();
      		  setVisible(false);
      		  new MainMenu();
			}
		});
		getContentPane().add(btnBackToMain, "cell 1 0,grow");
		
		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "cell 0 2,grow");
			
		
		// lade Daten mittels ProxyCustomerData, falls Customer Datenliste (aus dem File) noch keine Daten hat
		if(this.mode.equals("test") && customerList.size()==0){
			ProxyCustomerData customerData = new ProxyCustomerData();
			this.customerList = customerData.loadCustomerData();
		}	
		
		// Inhalte fuer JTable vorbereiten
		String title[] = {"Customer Nr.", "Type", "Organization", "Last Name"};
		String data[][] = new String[customerList.size()][4];

		for(int row=0;row<customerList.size();row++){
			int customerNr = customerList.get(row).getId();
			data[row][0]=String.valueOf(customerNr);
			data[row][1]=customerList.get(row).getCustomerType();			
			data[row][2]=customerList.get(row).getOrganization();
			data[row][3]=customerList.get(row).getLastname();
		}
		setTheDataInTheTable(data,title);		
	}
	
	
	/**
	 * Mittels setTheDataInTheTable koennen die Dateninhalte (unter Verwendung der customerList) und 
	 * die Spaltentitel im JTable gesetzt werden.
	 */
	public void setTheDataInTheTable(Object data[][], Object title[]){
		
		model = new DefaultTableModel(data, title){
 
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
