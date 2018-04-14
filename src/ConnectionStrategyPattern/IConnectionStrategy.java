package ConnectionStrategyPattern;

import javax.swing.JTable;

import Logic.IConnection;

/**
 * Das Interface IConnectionStrategy gibt die Methode execute, die jeweils von den Klassen 
 * FastBillConnectionStrategy und MockupConnectionStrategy implementiert wird in ihrer jeweiligen
 * Ausfuehrung zur Erstellung einer Connection (je nach Konfigurationsmodus), an.
 * @author Cordula Eggerth
 *
 */
public interface IConnectionStrategy {
	
	/**
	 * execute legt je nach mode (Konfigurationsmodus, d.h. entweder productive oder test) eine Connection 
	 * an und gibt diese an die aufrufende Klassen zurueck.
	 * @param mode
	 * @return IConnection
	 */
	public IConnection execute(String mode);

}


