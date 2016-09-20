package Models;

import java.util.ArrayList;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Pizza {
	private IntegerProperty id = new SimpleIntegerProperty(0);
	private PizzaGroesse groesse;
	private ArrayList<Belag> belag;
	private DoubleProperty preis = new SimpleDoubleProperty(0);
	
	public Pizza(int id, PizzaGroesse groesse, ArrayList<Belag> belag, double preis) {
		this.id = new SimpleIntegerProperty(id);
		this.groesse = groesse;
		this.belag = belag;
		this.preis = new SimpleDoubleProperty(preis);
	}
	
	public Pizza(PizzaGroesse groesse, ArrayList<Belag> belag, double preis) {
		this.groesse = groesse;
		this.belag = belag;
		this.preis = new SimpleDoubleProperty(preis);
	}

	public Pizza(int id, PizzaGroesse groesse, ArrayList<Belag> belag) {
		this.id = new SimpleIntegerProperty(id);
		this.groesse = groesse;
		this.belag = belag;
	}
	
	public Pizza(PizzaGroesse groesse) {
		this.belag = new ArrayList<Belag>();
		this.groesse = groesse;
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
	 * @return the groesse
	 */
	public PizzaGroesse getPizzaGroesse() {
		return groesse;
	}
	/**
	 * @param groesse the groesse to set
	 */
	public void setPizzaGroesse(PizzaGroesse groesse) {
		this.groesse = groesse;
	}
	/**
	 * @return the belag
	 */
	public ArrayList<Belag> getBelag() {
		return belag;
	}
	/**
	 * @param belag the belag to set
	 */
	public void setBelag(ArrayList<Belag> belag) {
		this.belag = belag;
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
	
	public void addBelag(Belag belag){
		this.belag.add(belag);
	}
}
