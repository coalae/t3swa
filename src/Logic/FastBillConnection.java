package Logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

import javax.xml.bind.DatatypeConverter;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;


import CompositePattern.PlanGold;
import CompositePattern.PlanSilver;
import CompositePattern.Product;
import CompositePattern.ProductMaxService;
import CompositePattern.ProductMediumService;
import CompositePattern.ProductMinService;
import FactoryMethodPattern.IProperties;
import FactoryMethodPattern.IPropertyReader;
import FactoryMethodPattern.PropertiesFilePropertyReader;
import Model.Customer;
import Model.Invoice;
import Model.Subscription;
import iteratorPattern.InvoiceIterator;
import iteratorPattern.SubscriptionIterator;

/**
 * Die Klasse FastBillConnection implementiert das Interface IConnection, das die Methoden, die in 
 * den Connections angeboten werden, vorgibt.
 * Die Klasse wird im Abstract Factory Pattern verwendet.
 * @author Cordula Eggerth
 *
 */
public class FastBillConnection implements IConnection, SubscriptionIterator, InvoiceIterator{
	

	/**
	 * Variable fuer den Zugriff auf die Schnittstelle IPropertyReader
	 */
	IPropertyReader reader;
	
	/**
	 * Variable fuer den Zugriff auf die Schnittstelle IProperties
	 */
	IProperties iprop;
	
	/**
	 * Der Konstruktor holt sich ueber die Schnittstelle IPropertyReader die Zugangsdaten und uebergibt sie dem Interface IProperties
	 */
	public FastBillConnection(){
		this.reader = new PropertiesFilePropertyReader();
		this.iprop = this.reader.readProperties();
	}

	
	
	/**
	 * getCustomerList holt die Customer Liste von FastBill. 
	 * Dafuer wird ein API Request gesendet und ein JSON Format als Response erhalten. 
	 * Das JSON Format wird eingelesen und eine customerList (ArrayList von Typ Customer) 
	 * wird daraus erstellt.
	 * @return customerList
	 */
	@Override
	public ArrayList<Customer> getCustomerList(){
		
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		
		
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost post = null;
        try {
            post = new HttpPost("https://my.fastbill.com/api/1.0/api.php");
            String encoding = DatatypeConverter.printBase64Binary((iprop.getUserMail() +":"+ iprop.getID()).getBytes("UTF-8"));
            
            post.setHeader("Authorization", "Basic " + encoding);
            String json = "{\"SERVICE\":\"customer.get\",\"FILTER\":{}}";
            HttpEntity entity = new ByteArrayEntity(json.getBytes("UTF-8"));
            post.setEntity(entity);
            
            HttpResponse response = httpClient.execute(post);
         
                String jsonString = EntityUtils.toString(response.getEntity());
                JSONObject root = new JSONObject(jsonString);
                JSONObject jsonResponse = (JSONObject) root.get("RESPONSE");
                JSONArray customers = (JSONArray) jsonResponse.get("CUSTOMERS");
                
                for(int i = 0 ; i < customers.length(); i++){
                	JSONObject customer = (JSONObject) customers.get(i);
                	Customer thisCustomer = new Customer(customer.getInt("CUSTOMER_NUMBER"), customer.getString("CUSTOMER_TYPE"),
                			customer.getString("ORGANIZATION"),customer.getString("LAST_NAME"));
                	customerList.add(thisCustomer);
                }

        } catch (Exception e) {
			e.printStackTrace();
		} finally {
            post.abort();
        }
        
        return customerList; 
	}
	
	/**
	 * createCustomer erstellt einen neuen Customer in FastBill. 
	 * Dafuer wird ein API Request gesendet. Als Antwort erhaelt man 
	 * ein JSON Format, ueber das gesehen werden kann, ob ein Success
	 * des Hinzufuegens erfolgt ist. 
	 * @return customerList
	 */
	@Override
	public void createCustomer(Customer customer){
				
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost post = null;
        try {
            post = new HttpPost("https://my.fastbill.com/api/1.0/api.php");
            String encoding = DatatypeConverter.printBase64Binary((iprop.getUserMail() +":"+ iprop.getID()).getBytes("UTF-8"));
            
            post.setHeader("Authorization", "Basic " + encoding);
            String json = "{\"SERVICE\":\"customer.create\",\"DATA\":{\"CUSTOMER_TYPE\":\""+customer.getCustomerType()+"\",\"ORGANIZATION\":\""+customer.getOrganization()+"\",\"LAST_NAME\":\""+customer.getLastname()+"\"}}";
            HttpEntity entity = new ByteArrayEntity(json.getBytes("UTF-8"));
            post.setEntity(entity);
            
            HttpResponse response = httpClient.execute(post);
         
                System.out.println(EntityUtils.toString(response.getEntity()));
                
        } catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
            post.abort();
        }
	}
    
	
	
	/**
	 * getProductList holt die Product Liste von FastBill. 
	 * Dafuer wird ein API Request gesendet und ein JSON Format als Response erhalten. 
	 * Das JSON Format wird eingelesen und eine customerList (ArrayList von Typ Product) 
	 * wird daraus erstellt.
	 * @return productList
	 */
	@Override
	public ArrayList<Product> getProductList(){
		
		ArrayList<Product> productList = new ArrayList<Product>();
		
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost post = null;
        try {
            post = new HttpPost("https://my.fastbill.com/api/1.0/api.php");
            String encoding = DatatypeConverter.printBase64Binary((iprop.getUserMail() +":"+ iprop.getID()).getBytes("UTF-8"));
            
            post.setHeader("Authorization", "Basic " + encoding);
            String json = "{\"SERVICE\":\"article.get\",\"FILTER\":{}}";
            HttpEntity entity = new ByteArrayEntity(json.getBytes("UTF-8"));
            post.setEntity(entity);
            
            HttpResponse response = httpClient.execute(post);
         
            String jsonString = EntityUtils.toString(response.getEntity());
             
               
                JSONObject root = new JSONObject(jsonString);
                JSONObject jsonResponse = (JSONObject) root.get("RESPONSE");
                
                JSONArray products = (JSONArray) jsonResponse.get("ARTICLES");
                
                for(int i = 0 ; i < products.length(); i++){
                	JSONObject article = (JSONObject) products.get(i);
                	
                	if(article.getString("TITLE").equals("ProductMaxService-Gold")){
                		
                		PlanGold planGold = new PlanGold(Double.parseDouble(article.getString("UNIT_PRICE")));
                    	Product newProduct = new ProductMaxService(article.getInt("ARTICLE_NUMBER"), article.getString("TITLE"),
                    			article.getString("DESCRIPTION"),Double.parseDouble(article.getString("VAT_PERCENT")), planGold);
                    	
                    	productList.add(newProduct);   
                	} 
                	else if(article.getString("TITLE").equals("ProductMaxService-Silver")){
                		PlanSilver planSilver = new PlanSilver(Double.parseDouble(article.getString("UNIT_PRICE")));
                		Product newProduct = new ProductMaxService(article.getInt("ARTICLE_NUMBER"), article.getString("TITLE"),
                    			article.getString("DESCRIPTION"),Double.parseDouble(article.getString("VAT_PERCENT")), planSilver);
                    	productList.add(newProduct);              		
                	} 
                	else if(article.getString("TITLE").equals("ProductMediumService-Gold")){
                		PlanGold planGold = new PlanGold(Double.parseDouble(article.getString("UNIT_PRICE")));
                		Product newProduct = new ProductMediumService(article.getInt("ARTICLE_NUMBER"), article.getString("TITLE"),
                    			article.getString("DESCRIPTION"),Double.parseDouble(article.getString("VAT_PERCENT")), planGold);
                    	productList.add(newProduct);              		
                	} 
                	else if(article.getString("TITLE").equals("ProductMediumService-Silver")){
                		PlanSilver planSilver = new PlanSilver(Double.parseDouble(article.getString("UNIT_PRICE")));
                		Product newProduct = new ProductMediumService(article.getInt("ARTICLE_NUMBER"), article.getString("TITLE"),
                    			article.getString("DESCRIPTION"),Double.parseDouble(article.getString("VAT_PERCENT")), planSilver);
                    	productList.add(newProduct);              		
                	} 
                	else if(article.getString("TITLE").equals("ProductMinService-Gold")){
                		PlanGold planGold = new PlanGold(article.getDouble("UNIT_PRICE"));
                    	ProductMinService newProduct = new ProductMinService(article.getInt("ARTICLE_NUMBER"), article.getString("TITLE"),
                    			article.getString("DESCRIPTION"),Double.parseDouble(article.getString("VAT_PERCENT")), planGold);
                    	productList.add(newProduct);   
                	}
                    else { // if(article.getString("TITLE").equals("ProductMinService-Silver")){
                		PlanSilver planSilver = new PlanSilver(Double.parseDouble(article.getString("UNIT_PRICE")));
                		Product newProduct = new ProductMinService(article.getInt("ARTICLE_NUMBER"), article.getString("TITLE"),
                        		article.getString("DESCRIPTION"),Double.parseDouble(article.getString("VAT_PERCENT")), planSilver);
                        productList.add(newProduct);              		
                    } 
                }	

        } catch (Exception e) {
			e.printStackTrace();
		} finally {
            post.abort();
        }

        return productList; 
	}
	
	/**
	 * getProductPerId liefert ein Produkt anhand der ID
	 * @return Product product
	 */
	@Override
	public Product getProductPerId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getCustomerPerID(int id) {
		return null;
	}



	@Override
	public ArrayList<Subscription> getAllSubscriptions() {
		return null;
	}



	@Override
	public ArrayList<Subscription> getSubscriptionsOfCustomer(Customer customer) {
		return null;
	}



	@Override
	public boolean addSubscription(Subscription subscription) {
		return false;
	}



	@Override
	public boolean deleteSubscription(Subscription subscription) {
		return false;
	}



	@Override
	public Subscription getSubscriptionPerId(UUID id) {
		return null;
	}



	@Override
	public void updateSubscription(Subscription subscription) {		
	}



	@Override
	public ArrayList<Invoice> getAllInvoices() {
		return null;
	}



	@Override
	public ArrayList<Invoice> getAllInvoicesOfCustomer(Customer customer) {
		return null;
	}



	@Override
	public void payInvoice(Invoice invoice) {		
	}



	@Override
	public void updateInvoice(Invoice invoice) {		
	}



	@Override
	public Invoice getInvoicePerSubscriptionID(UUID id) {
		return null;
	}
	
	/**
	 * Liefert ArrayList<Invoice> als Iterator
	 */
	@Override
	public Iterator createInvoiceIterator() {
		return getAllInvoices().iterator();
	}
	
	/**
	 * Liefert ArrayList<Invoice> als Iterator
	 */
	@Override
	public Iterator createSubscriptionIterator() {
		return getAllSubscriptions().iterator();
	}
	
	    
}