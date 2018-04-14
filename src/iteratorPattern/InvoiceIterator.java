package iteratorPattern;

import java.util.Iterator;
/**
 * Interface für Collections of Invoices
 * @author Martin
 *
 */
public interface InvoiceIterator {
	
	@SuppressWarnings("rawtypes")
	public Iterator createInvoiceIterator();

}


