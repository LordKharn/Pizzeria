package Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Getraenk {
	private IntegerProperty id;
	private StringProperty name;
	private DoubleProperty preis;
	
	
	public Getraenk(int id, String name, double preis) {
		this.id = new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.preis = new SimpleDoubleProperty(preis);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id.get();
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id.set(id);;
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
	public double getPreis() {
		return preis.get();
	}
	/**
	 * @param preis the preis to set
	 */
	public void setPreis(double preis) {
		this.preis.set(preis);;
	}
	public String toString(){
		return this.name.get();
	}
}
