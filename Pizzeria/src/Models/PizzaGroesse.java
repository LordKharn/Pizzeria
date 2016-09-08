package Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PizzaGroesse {
	private StringProperty groesse;
	private DoubleProperty preis;
	
	public PizzaGroesse(String pizzaGroesse, double preis) {
		this.groesse = new SimpleStringProperty(pizzaGroesse);
		this.preis = new SimpleDoubleProperty(preis);
	}

	/**
	 * @return the groesse
	 */
	public String getGroesse() {
		return groesse.get();
	}

	/**
	 * @param groesse the groesse to set
	 */
	public void setGroesse(String groesse) {
		this.groesse.set(groesse);;
	}

	/**
	 * @return the preis
	 */
	public double getPreis() {
		return preis.get();
	}

	/**
	 * @param preis the preis to set
	 */
	public void setPreis(double preis) {
		this.preis.set(preis);;
	}
}
