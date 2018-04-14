package Controller;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import ConnectionStrategyPattern.ContextForConnection;
import ConnectionStrategyPattern.FastBillConnectionStrategy;
import ConnectionStrategyPattern.MockupConnectionStrategy;
import Logic.IConnection;
import Model.Customer;

import javax.swing.JButton;

/**
 * Mittels AddCustomer kann ein neuer Customer je nach vorheriger Auswahl 
 * des Konfigurationsmodus auf FastBill oder im File (Mockup) hinzugefuegt 
 * werden.
 * @author Cordula
 *
 */
public class AddCustomer extends JFrame{

	/**
	 * Instanzvariablen
	 */
	private static final long serialVersionUID = 1L;
	private final JLabel lblAddCustomer = new JLabel("Add Customer");
	private JTextField textField_1;
	private JTextField textField;
	private String mode;
	private ButtonGroup group;
	private JRadioButton rdbtnBusiness;
	private JRadioButton rdbtnConsumer;
	private JButton btnSaveCustomer;
	private JButton btnBackToMain;
	protected Component frame;

	/**
	 * Create application.
	 */
	public AddCustomer(String mode) {
		this.mode=mode;
		this.setVisible(true);
		initialize();
	}

	/**
	 * Initialize contents of frame.
	 */
	private void initialize() {

		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new MigLayout("", "[][][grow][]", "[][][][][][][][][][][][]"));
		getContentPane().add(lblAddCustomer, "cell 1 0");
		
		// Radiobuttons gruppieren zu ButtonGroup
	    group = new ButtonGroup();
	    
	    JLabel lblType = new JLabel("Type");
	    getContentPane().add(lblType, "cell 1 3");
	    
	    rdbtnBusiness = new JRadioButton("Business");
	    getContentPane().add(rdbtnBusiness, "cell 1 4");
	    group.add(rdbtnBusiness);
	    
	    rdbtnConsumer = new JRadioButton("Consumer");
	    getContentPane().add(rdbtnConsumer, "cell 1 5");
	    group.add(rdbtnConsumer);
	    
	    // Textfelder anlegen
	    JLabel lblOrganization = new JLabel("Organization");
	    getContentPane().add(lblOrganization, "cell 1 6,alignx left");
	    
	    textField = new JTextField();
	    getContentPane().add(textField, "cell 2 6,growx");
	    textField.setColumns(10);
	    
	    JLabel lblLastName = new JLabel("Last name");
	    getContentPane().add(lblLastName, "cell 1 7");
	    
	    textField_1 = new JTextField();
	    getContentPane().add(textField_1, "cell 2 7,growx");
	    textField_1.setColumns(10);
	    
	    btnSaveCustomer = new JButton("Save Customer");
	    getContentPane().add(btnSaveCustomer, "cell 1 11");
	    
	    btnBackToMain = new JButton("Back to Main Menu");
	    getContentPane().add(btnBackToMain, "cell 2 11");
	    
	    
	    
		/**
		 *  ActionListener fuer Button "Save Customer" anlegen
		 */
		btnSaveCustomer.addActionListener(new ActionListener() {

			/**
			 * Bei Klicken auf den Button "Save Customer" werden die eingegebenen 
			 * Informationen des neuen Customer, sofern die Benutzereingaben korrekt 
			 * waren, gespeichert.
			 * Falls ein Fehler aufgetreten ist, wird eine entsprechende Meldung angezeigt.
			 * @param ActionEvent e
			 */
            public void actionPerformed(ActionEvent e) {
 
            	if((!rdbtnBusiness.isSelected() && !rdbtnConsumer.isSelected()) && (textField.getText().equals("") || textField_1.getText().equals(""))){
                  		JOptionPane.showMessageDialog(frame,
            		    "Empty fields are not allowed.",
            		    "Create Customer Information",
            		    JOptionPane.WARNING_MESSAGE);
            	} else if((!rdbtnBusiness.isSelected() && !rdbtnConsumer.isSelected()) && (!textField.getText().equals("") && !textField_1.getText().equals(""))){
                      		JOptionPane.showMessageDialog(frame,
                		    "You need to selected business or consumer as customer type",
                		    "Create Customer Information",
                		    JOptionPane.WARNING_MESSAGE);
            	} else if(textField.getText().equals("") || textField_1.getText().equals("")){
            		JOptionPane.showMessageDialog(frame,
            		    "You need to fill in organization AND lastname to save customer.",
            		    "Create Customer Information",
            		    JOptionPane.WARNING_MESSAGE);
            	} else {
            	
	            	String customerType = "";
		      		  if(rdbtnBusiness.isSelected()){
		  	            System.out.println("business");
		  	            customerType="business";
		  	        }
		  	        else if(rdbtnConsumer.isSelected()){
		  	            System.out.println("consumer");
		  	            customerType="consumer";
		  	        }
		      		  
		      		String organization = textField.getText();
		      		String lastname = textField_1.getText();
	      		  
		      		Customer newCustomer = new Customer(0,customerType,organization,lastname);
		    		IConnection connection = null;

	      			ContextForConnection context = null;

		      		if(mode.equals("productive")) {
		      			context = new ContextForConnection(new FastBillConnectionStrategy());
		      		}
		      		else { // if mode test is selected
		      			context = new ContextForConnection(new MockupConnectionStrategy());
		      		}
		      		
	      			connection = context.execute(mode);
		      		connection.createCustomer(newCustomer);		      		
		      		
	      			JOptionPane.showMessageDialog(frame, "New Customer was successfully created.");

            	}	
            	
            	// Textfelder wieder leer setzen
	      		textField.setText("");
	      		textField_1.setText("");
      		  
            }	
        });
		
		
		
		/**
		 *  ActionListener fuer Button "Back to Main Menu" anlegen
		 */
		btnBackToMain.addActionListener(new ActionListener() {

			/**
			 * Bei Klicken auf den Button "Back to Main Menu" wird 
			 * zum das Fenster MainMenu zurueck navigiert.
			 * @param ActionEvent e
			 */
            public void actionPerformed(ActionEvent e) {
            	
        		  dispose();
          		  setVisible(false);
          		  new MainMenu();
            }
            
        });      
 
	}

}
