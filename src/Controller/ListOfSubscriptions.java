package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
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
import Model.Subscription;
import net.miginfocom.swing.MigLayout;

/**
 * Die Klasse ListOfScubscriptions erzeugt eine Liste mit Subscriptions und zeigt diese im GUI an.
 * @author Martin Regenfelder
 *
 */
public class ListOfSubscriptions extends JFrame{
	/**
	 * Instanzvariablen
	 */
	private static final long serialVersionUID = 1L;
	JButton btnBack;
	JButton btn_invoice;
	JButton payInvoice;
	String mode;
	ArrayList<Subscription> subscriptionList;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel model = null;
	IConnection con;

	/**
	 * Create application.
	 */
	public ListOfSubscriptions(String mode, ArrayList<Subscription> subscriptionList) {
		this.mode = mode;
		this.subscriptionList = subscriptionList;
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

		JLabel lblCustomerList = new JLabel("List of "+subscriptionList.get(0).getCustomerID());
		getContentPane().add(lblCustomerList, "cell 0 0,grow");
		
		/**
		 * Bei Klick auf den Button "Back" wird   
		 * auf das Fenster ShowSubscriptions.java weitergeschaltet.
		 * @param ActionEvent e
		 */
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				setVisible(false);
				new ShowSubscriptions(mode, con.getCustomerList());
			}
		});
		
		/**
		 * Bei Klick auf den Button "invoice" wird   
		 * auf das Fenster InvoiceTable.java weitergeschaltet.
		 * @param ActionEvent e
		 */
		btn_invoice = new JButton("show Invoice");
		btn_invoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rowIndex = table.getSelectedRow();
				if (rowIndex == -1) {
					JOptionPane.showMessageDialog(table, "Error, keine Zelle ausgewählt!", "Fehler bei der Auswahl!",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				dispose();
		      	setVisible(false);
				new InvoiceTable(mode, con.getInvoicePerSubscriptionID(subscriptionList.get(rowIndex).getId()));
			}

		});
		
		/**
		 * Bei Klick auf den Button "payInvoice" wird das aktuelle Fenster mit aktuallisierten Daten neu gerendert
		 * @param ActionEvent e
		 */
		payInvoice = new JButton("pay invoice");
		payInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rowIndex = table.getSelectedRow();
				if (rowIndex == -1) {
					JOptionPane.showMessageDialog(table, "Error, keine Zelle ausgewählt!", "Fehler bei der Auswahl!",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (subscriptionList.get(rowIndex).getSubscriptionStatus().equals("active")) {
					JOptionPane.showMessageDialog(table, "Error, bereits bezahlt!", "Payment Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				con.payInvoice(con.getInvoicePerSubscriptionID(subscriptionList.get(rowIndex).getId()));
				dispose();
				setVisible(false);
				new ListOfSubscriptions(mode, con.getSubscriptionsOfCustomer(con.getCustomerPerID(subscriptionList.get(0).getCustomerID())));
				
			}

		});

		getContentPane().add(btnBack, "cell 1 0,grow");
		getContentPane().add(btn_invoice, "cell 1 1,grow");
		getContentPane().add(payInvoice, "cell 2 0,grow");

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
		String title[] = { "SubscriptionID", "Gültig bis", "ProduktID", "Status"};
		String data[][] = new String[subscriptionList.size()][5];

		for (int i=0; i<subscriptionList.size(); i++) {
			data[i][0] = subscriptionList.get(i).getId().toString();
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YY  hh.mm.ss");
			data[i][1] = sdf.format(subscriptionList.get(i).getValidUntil());
			data[i][2] = Integer.toString(subscriptionList.get(i).getProductId());
			data[i][3] = subscriptionList.get(i).getSubscriptionStatus();
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
