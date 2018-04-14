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

import CompositePattern.Product;
import Logic.MockupConnection;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

/**
 * ShowProductList zeigt die Product Liste an je nach Auswahl des Konfigurationsmodus (mode).
 * @author Cordula Eggerth
 *
 */
public class ShowProductList extends JFrame {

	/**
	 * Instanzvariablen
	 */
	private static final long serialVersionUID = 1L;
	JButton btnBackToMain;
	String mode;
	ArrayList<Product> productList;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel model = null;

	/**
	 * Create application.
	 */
	public ShowProductList(String mode, ArrayList<Product> productList) {
		this.mode=mode;
		this.productList=productList;
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
		
		JLabel lblCustomerList = new JLabel("Product List");
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
			
		
		// lade Daten, falls Product Datenliste (aus dem File) noch keine Daten hat
		if(this.mode.equals("test") && productList.size()==0){
			MockupConnection mockupConnection = new MockupConnection();
			mockupConnection.writeCurrentProductListIntoFile();
			this.productList = mockupConnection.getProductList();
		}	
		
		// Inhalte fuer JTable vorbereiten
		String title[] = {"Product Id", "Name", "Description", "VAT Percent", "Monthly Plan Rate"};
		String data[][] = new String[productList.size()][5];

		for(int row=0;row<productList.size();row++){
			data[row][0]=String.valueOf(productList.get(row).getProductId());
			data[row][1]=productList.get(row).getName();			
			data[row][2]=productList.get(row).getDescription();
			data[row][3]=String.valueOf(productList.get(row).getVatPercent());
			data[row][4]=String.valueOf(productList.get(row).getPlan().getMonthlyRate());
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
