package Controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import CompositePattern.Product;
import ConnectionStrategyPattern.ContextForConnection;
import ConnectionStrategyPattern.FastBillConnectionStrategy;
import ConnectionStrategyPattern.MockupConnectionStrategy;
import Logic.IConnection;
import Model.Customer;

import javax.swing.ButtonGroup;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

/**
 * Main Menu ist die Startseite der Application. Zunaechst kann darueber die Konfiguration ueber die Auswahl des 
 * Modus (mode) als productive oder test erfolgen. 
 * Die Auswahl von productive als mode loest aus, dass die FastBillAPI fuer 
 * alle Requests verwendet wird. 
 * Die Auswahl von test als mode loest aus, dass das Mockup verwendet wird (d.h. es werden serialisierte Daten 
 * aus einem File gelesen, und die FastBillAPI wird nicht verwendet). 
 * @author Cordula Eggerth
 *
 */
public class MainMenu extends JFrame{

	/**
	 * Instanzvariablen.
	 */
	private static final long serialVersionUID = 1L;
	private ButtonGroup group;
	private JRadioButton rdbtnProductive;
	private JRadioButton rdbtnTest;
    private JButton btnCreateCustomer;
    private JButton btnGetCustomerList;
    private JButton btnGetProductList;
    private JButton btnSubscriptions;

	
	/**
	 * Launch application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create application.
	 */
	public MainMenu() {
		setVisible(true);
		initialize();
	}

	/**
	 * Initialize contents of frame.
	 */
	private void initialize() {

		setBounds(100, 100, 481, 333);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[][][][]", "[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]"));
		
		JLabel lblMainMenu = new JLabel("Main Menu");
		getContentPane().add(lblMainMenu, "cell 0 0");
		
		// Radio buttons gruppieren zu ButtonGroup
	    group = new ButtonGroup();

	    /* Modus (mode) Optionen anlegen 
	     * productive bedeutet: FastBillConnection wird verwendet; 
	     * test bedeutet: MockupConnection wird verwendet
	     */
		JLabel lblMode = new JLabel("Please select the configuration mode:");
		getContentPane().add(lblMode, "cell 0 2");
		
		rdbtnProductive = new JRadioButton("productive");
		getContentPane().add(rdbtnProductive, "cell 0 3");
	    group.add(rdbtnProductive);
	
		rdbtnTest = new JRadioButton("test");
		getContentPane().add(rdbtnTest, "cell 0 4");
	    group.add(rdbtnTest);

	    
	    // Angebotene GUI Operationen (ueber Klick auf Buttons erreichbar)
		JLabel lblOperations = new JLabel("Available Operations:");
		getContentPane().add(lblOperations, "cell 0 6");
		
		btnCreateCustomer = new JButton("Create Customer");
		btnCreateCustomer.setEnabled(false);
		getContentPane().add(btnCreateCustomer, "cell 0 7");
		
		btnGetCustomerList = new JButton("Get Customer List");
		btnGetCustomerList.setEnabled(false);
		getContentPane().add(btnGetCustomerList, "cell 0 8");		
		
				btnGetProductList = new JButton("Get Product List");
				btnGetProductList.setEnabled(false);
				getContentPane().add(btnGetProductList, "cell 0 9");
				btnGetProductList.addActionListener(new ActionListener() {

					/**
					 * Bei Klick auf den Button "Get Product List" wird   
					 * auf das Fenster ShowProductList.java weitergeschaltet.
					 * @param ActionEvent e
					 */
            public void actionPerformed(ActionEvent e) {
	            
            	String mode = "";
      		  	if(rdbtnProductive.isSelected()){
      		  		System.out.println("productive");
      		  		mode="productive";
      		  	}
      		  	else if(rdbtnTest.isSelected()){
      		  		System.out.println("test");
  	            mode="test";
      		  	}
      		  	
            	IConnection connection = null;

	      		ContextForConnection context = null;

				      	if(mode.equals("productive")) {
				      		context = new ContextForConnection(new FastBillConnectionStrategy());
				      	}
				      	else { // if mode test is selected
				      		context = new ContextForConnection(new MockupConnectionStrategy());
				      	}
				      		
	      		connection = context.execute(mode);
				      	ArrayList<Product> productList=connection.getProductList();	
				      		 
				      	dispose();
				      	setVisible(false);
				      	new ShowProductList(mode, productList);
				      		
            }	
            
        });
		
		btnSubscriptions = new JButton("Subscriptions");
		btnSubscriptions.setEnabled(false);
		getContentPane().add(btnSubscriptions, "cell 0 10");
		
		
		
		// ActionListener fuer RadioButtons
		 // Listener fuer productive radiobutton
		rdbtnProductive.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	    		btnCreateCustomer.setEnabled(true);
	    		btnGetCustomerList.setEnabled(true);
	    		btnGetProductList.setEnabled(true);
	    		btnSubscriptions.setEnabled(false);
	        }
	    });

	    // Listener fuer test radiobutton
		rdbtnTest.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	    		btnCreateCustomer.setEnabled(true);
	    		btnGetCustomerList.setEnabled(true);
	    		btnGetProductList.setEnabled(true);	    		
	    		btnSubscriptions.setEnabled(true);
	        }
	    });
		

		
		/**
		 *  ActionListener fuer Button "Create Customer" anlegen.
		 */
		btnCreateCustomer.addActionListener(new ActionListener() {

			/**
			 * Bei Klick auf den Button "Create Customer" wird der Modus weitergegeben 
			 * und auf das Fenster AddCustomer.java weitergeschaltet.
			 * @param ActionEvent e
			 */
            public void actionPerformed(ActionEvent e) {
            	String mode = "";
      		  if(rdbtnProductive.isSelected()){
  	            System.out.println("productive");
  	            mode="productive";
  	        }
  	        else if(rdbtnTest.isSelected()){
  	            System.out.println("test");
  	            mode="test";
  	        }
      		  dispose();
      		  setVisible(false);
      		  new AddCustomer(mode);
            	
            }	
        });
		
		

		/**
		 *  ActionListener fuer Button "Get Customer List" anlegen.
		 */
		btnGetCustomerList.addActionListener(new ActionListener() {

			/**
			 * Bei Klick auf den Button "Get Customer List" wird   
			 * auf das Fenster ShowCustomerList.java weitergeschaltet.
			 * @param ActionEvent e
			 */
            public void actionPerformed(ActionEvent e) {
	            
            	String mode = "";
      		  	if(rdbtnProductive.isSelected()){
      		  		System.out.println("productive");
      		  		mode="productive";
      		  	}
      		  	else if(rdbtnTest.isSelected()){
      		  		System.out.println("test");
  	            mode="test";
      		  	}
      		  	
            	IConnection connection = null;

	      		ContextForConnection context = null;

		      	if(mode.equals("productive")) {
		      		context = new ContextForConnection(new FastBillConnectionStrategy());
		      	}
		      	else { // if mode test is selected
		      		context = new ContextForConnection(new MockupConnectionStrategy());
		      	}
		      		
	      		connection = context.execute(mode);
		      	ArrayList<Customer> customerList=connection.getCustomerList();	
		      		 
		      	dispose();
		      	setVisible(false);
		      	new ShowCustomerList(mode, customerList);
		      		
            }	
            
        });
		
		
		/**
		 *  ActionListener fuer Button "Subscriptions" anlegen.
		 */
		btnSubscriptions.addActionListener(new ActionListener() {

			/**
			 * Bei Klick auf den Button "Subscriptions" wird   
			 * auf das Fenster ShowSubscriptions.java weitergeschaltet.
			 * @param ActionEvent e
			 */
            public void actionPerformed(ActionEvent e) {
	            
            	String mode = "";
      		  	if(rdbtnProductive.isSelected()){
      		  		System.out.println("productive");
      		  		mode="productive";
      		  	}
      		  	else if(rdbtnTest.isSelected()){
      		  		System.out.println("test");
  	            mode="test";
      		  	}
      		  	
            	IConnection connection = null;

	      		ContextForConnection context = null;

		      	if(mode.equals("productive")) {
		      		context = new ContextForConnection(new FastBillConnectionStrategy());
		      	}
		      	else { // if mode test is selected
		      		context = new ContextForConnection(new MockupConnectionStrategy());
		      	}
		      		
	      		connection = context.execute(mode);
		      	ArrayList<Customer> customerList=connection.getCustomerList();	
		      		 
		      	dispose();
		      	setVisible(false);
		      	new ShowSubscriptions(mode, customerList);
		      		
            }	
            
        });
		
	}

}
