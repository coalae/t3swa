package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import CompositePattern.Product;
import Logic.FastBillConnection;
import Logic.IConnection;
import Logic.MockupConnection;
import Model.Invoice;
import Model.Subscription;
import net.miginfocom.swing.MigLayout;

/**
 * Die Klasse InvoiceTable erzeugt eine Tabelle mit Rechnungen.
 * @author Martin Regenfelder
 *
 */
public class InvoiceTable extends JFrame{

	/**
	 * Instanzvariablen
	 */
	private static final long serialVersionUID = 1L;
	JButton btnBack;
	String mode;
	Invoice invoice;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel model = null;
	IConnection con;

	/**
	 * Create application.
	 */
	public InvoiceTable(String mode, Invoice invoice) {
		this.mode = mode;
		this.invoice = invoice;
		this.setVisible(true);
		initialize();
	}

	/**
	 * Initialize contents of frame.
	 */
	private void initialize() {

		// setBounds(100, 100, 450, 300);
		setBounds(150, 150, 1000, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[grow][]", "[][][grow]"));

		JLabel lblCustomerList = new JLabel("Customer List");
		getContentPane().add(lblCustomerList, "cell 0 0,grow");

		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				setVisible(false);
				new ListOfSubscriptions(mode, con.getSubscriptionsOfCustomer(con.getCustomerPerID(invoice.getCustomerId())));
			}
		});

		

		getContentPane().add(btnBack, "cell 1 0,grow");

		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "cell 0 2,grow");

		// lade Daten mittels ProxyCustomerData, falls Customer Datenliste (aus
		// dem File) noch keine Daten hat

		if (this.mode.equals("test")) {
			this.con = new MockupConnection();
		} else {
			this.con = new FastBillConnection();
		}

		// Inhalte fuer JTable vorbereiten
		String title[] = { "InvoiceID", "CustomerId", "ProduktID", "Preis", "ErstellungsDatum","Fällig am","Gezahlt am"};
		String data[][] = new String[1][7];

		
			data[0][0] = invoice.getInvoiceId().toString();
			data[0][1] = Integer.toString(invoice.getCustomerId());
			data[0][2] = Integer.toString(invoice.getProductId());
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YY  hh.mm.ss");
			Subscription s = con.getSubscriptionPerId(invoice.getSubscriptionID());
			Product p = con.getProductPerId(s.getProductId());
			data[0][3] = Double.toString(p.getPlan().getMonthlyRate()); 
			data[0][4] = sdf.format(invoice.getInvoiceCreationDate());
			data[0][5] = sdf.format(invoice.getInvoiceDueDate());
			if(invoice.getInvoicePaidDate()!=null){
				data[0][6] = sdf.format(invoice.getInvoicePaidDate());
			}else{
				data[0][6] = "noch nicht bezahlt!";
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
