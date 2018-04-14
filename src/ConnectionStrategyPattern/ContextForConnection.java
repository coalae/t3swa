package ConnectionStrategyPattern;

import Logic.IConnection;

/**
 * ContextForConnection ist Teil des ConnectionStrategyPattern. Die Klasse bietet den Context fuer die 
 * Ausfuehrung des Strategy Patterns und der Methode execute.
 * @author Cordula Eggerth
 *
 */
public class ContextForConnection {
	
	/**
	 * Instanzvariable
	 */
	private IConnectionStrategy strategy;
	
	/**
	 * Konstruktor
	 * @param strategy
	 */
	public ContextForConnection(IConnectionStrategy strategy){
		this.strategy = strategy;
	}
	
	/**
	 * Execute erstellt ein Objekt vom Typ IConnection je nachdem ob der mode (Modus) productive (d.h. Verwendung der
	 * FastBillConnection) oder test (d.h. Verwendung der MockupConnection) war.
	 * @param mode
	 * @return
	 */
	public IConnection execute(String mode){
		return strategy.execute(mode);
	}
	
}
