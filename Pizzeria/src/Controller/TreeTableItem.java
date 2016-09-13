package Controller;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TreeTableItem {
	private StringProperty name;
	private DoubleProperty preis;
	
	public TreeTableItem(String name, Double preis) {
		this.name = new SimpleStringProperty(name);
		this.preis = new SimpleDoubleProperty(preis);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name.get();
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name.set(name);;
	}

	/**
	 * @return the preis
	 */
	public Double getPreis() {
		return preis.get();
	}

	/**
	 * @param preis the preis to set
	 */
	public void setPreis(Double preis) {
		this.preis.set(preis);;
	}
	
	public DoubleProperty getPreisProperty(){
		return preis;
	}
	
}	
