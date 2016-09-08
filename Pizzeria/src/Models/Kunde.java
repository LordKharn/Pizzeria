package Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Kunde {
	private IntegerProperty kundenNummer;
	private StringProperty name;
	private StringProperty strasse;
	private StringProperty ort;
	private IntegerProperty plz;
	
	public Kunde(String name, String strasse, String ort, int plz) {
		this.kundenNummer = new SimpleIntegerProperty();
		this.name = new SimpleStringProperty(name);
		this.strasse = new SimpleStringProperty(strasse);
		this.ort = new SimpleStringProperty(ort);
		this.plz = new SimpleIntegerProperty(plz);
	}
	
	public Kunde(int kundenNummer, String name, String strasse, String ort, int plz) {
		this.kundenNummer = new SimpleIntegerProperty(kundenNummer);
		this.name = new SimpleStringProperty(name);
		this.strasse = new SimpleStringProperty(strasse);
		this.ort = new SimpleStringProperty(ort);
		this.plz = new SimpleIntegerProperty(plz);
	}
	
	public Kunde(int kundenNummer) {
		this.kundenNummer = new SimpleIntegerProperty(kundenNummer);
	}

	/**
	 * @return the kundenNummer
	 */
	public int getKundenNummer() {
		return kundenNummer.get();
	}

	/**
	 * @param kundenNummer the kundenNummer to set
	 */
	public void setKundenNummer(int kundenNummer) {
		this.kundenNummer.set(kundenNummer);
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
	 * @return the strasse
	 */
	public String getStrasse() {
		return strasse.get();
	}

	/**
	 * @param strasse the strasse to set
	 */
	public void setStrasse(String strasse) {
		this.strasse.set(strasse);;
	}

	/**
	 * @return the ort
	 */
	public String getOrt() {
		return ort.get();
	}

	/**
	 * @param ort the ort to set
	 */
	public void setOrt(String ort) {
		this.ort.set(ort);;
	}

	/**
	 * @return the plz
	 */
	public int getPlz() {
		return plz.get();
	}

	/**
	 * @param plz the plz to set
	 */
	public void setPlz(int plz) {
		this.plz.set(plz);;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Kunde [kundenNummer=" + kundenNummer + ", name=" + name + ", strasse=" + strasse + ", ort=" + ort
				+ ", plz=" + plz + "]";
	}
}
