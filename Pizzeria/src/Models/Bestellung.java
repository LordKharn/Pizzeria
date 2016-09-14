package Models;

import java.util.ArrayList;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Bestellung {
	private IntegerProperty id = new SimpleIntegerProperty(0) ;
	private Kunde kunde;
	private ArrayList<Pizza> pizzen;
	private ArrayList<Getraenk> getraenke;
	private Getraenk bonusGetraenke;
	private DoubleProperty preis = new SimpleDoubleProperty(0);
	

	public Bestellung(Kunde kunde, ArrayList<Pizza> pizzen, ArrayList<Getraenk> getraenke,
			Getraenk bonusGetraenke, double preis) {
		this.kunde = kunde;
		this.pizzen = pizzen;
		this.getraenke = getraenke;
		this.bonusGetraenke = bonusGetraenke;
		this.preis = new SimpleDoubleProperty(preis);;
	}
	
	public Bestellung(int id, Kunde kunde, ArrayList<Pizza> pizzen, ArrayList<Getraenk> getraenke,
			Getraenk bonusGetraenke, double preis) {
		this.id = new SimpleIntegerProperty(id);
		this.kunde = kunde;
		this.pizzen = pizzen;
		this.getraenke = getraenke;
		this.bonusGetraenke = bonusGetraenke;
		this.preis = new SimpleDoubleProperty(preis);
	}

	public Bestellung(int id, Kunde kunde, Getraenk bonusGetraenke) {
		this.id = new SimpleIntegerProperty(id);
		this.kunde = kunde;
		this.bonusGetraenke = bonusGetraenke;
	}

	public Bestellung(int id, Kunde kunde) {
		this.id = new SimpleIntegerProperty(id);
		this.kunde = kunde;
	}
	
	public Bestellung(ArrayList<Pizza> pizzen, ArrayList<Getraenk> getraenke, double preis) {
		this.pizzen = pizzen;
		this.getraenke = getraenke;
		this.preis = new SimpleDoubleProperty(preis);
	}	
	
	public Bestellung() {
		this.pizzen = new ArrayList<Pizza>();
		this.getraenke = new ArrayList<Getraenk>();
	}

	/**
	 * @return the kunde
	 */
	public Kunde getKunde() {
		return kunde;
	}
	/**
	 * @param kunde the kunde to set
	 */
	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}
	/**
	 * @return the pizzen
	 */
	public ArrayList<Pizza> getPizzen() {
		return pizzen;
	}
	/**
	 * @param pizzen the pizzen to set
	 */
	public void setPizzen(ArrayList<Pizza> pizzen) {
		this.pizzen = pizzen;
	}
	/**
	 * @return the getraenke
	 */
	public ArrayList<Getraenk> getGetraenke() {
		return getraenke;
	}
	/**
	 * @param getraenke the getraenke to set
	 */
	public void setGetraenke(ArrayList<Getraenk> getraenke) {
		this.getraenke = getraenke;
	}
	/**
	 * @return the bonusGetraenke
	 */
	public Getraenk getBonusGetraenke() {
		return bonusGetraenke;
	}
	/**
	 * @param bonusGetraenke the bonusGetraenke to set
	 */
	public void setBonusGetraenke(Getraenk bonusGetraenke) {
		this.bonusGetraenke = bonusGetraenke;
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
		this.preis.set(preis);
	}
	public int getId() {
		return id.get();
	}
	public void setId(int id) {
		this.id.set(id);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Bestellung [id=" + id + ", kunde=" + kunde + ", bonusGetraenke=" + bonusGetraenke + "]";
	}
}
