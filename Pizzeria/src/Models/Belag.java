package Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Belag {
	private IntegerProperty id;
	private StringProperty name;
	private DoubleProperty preisS;
	private DoubleProperty preisM;
	private DoubleProperty preisL;
	private StringProperty belagsTyp;
	
	public Belag(int id, String name, double preisS, double preisM, double preisL, String belagsTyp) {
		this.id = new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.preisS = new SimpleDoubleProperty(preisS);
		this.preisM = new SimpleDoubleProperty(preisM);
		this.preisL = new SimpleDoubleProperty(preisL); 
		this.belagsTyp = new SimpleStringProperty(belagsTyp);
	}


	/**
	 * @return the preisS
	 */
	public double getPreisS() {
		return preisS.get();
	}


	/**
	 * @param preisS the preisS to set
	 */
	public void setPreisS(double preisS) {
		this.preisS.set(preisS);;
	}


	/**
	 * @return the preisM
	 */
	public double getPreisM() {
		return preisM.get();
	}


	/**
	 * @param preisM the preisM to set
	 */
	public void setPreisM(double preisM) {
		this.preisM.set(preisM);;
	}


	/**
	 * @return the preisL
	 */
	public double getPreisL() {
		return preisL.get();
	}


	/**
	 * @param preisL the preisL to set
	 */
	public void setPreisL(double preisL) {
		this.preisL.set(preisL);;
	}


	/**
	 * @return the belagsTyp
	 */
	public String getBelagsTyp() {
		return belagsTyp.get();
	}


	/**
	 * @param belagsTyp the belagsTyp to set
	 */
	public void setBelagsTyp(String belagsTyp) {
		this.belagsTyp.set(belagsTyp);;
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


	public int getId() {
		return id.get();
	}


	public void setId(int id) {
		this.id.set(id);;
	}
	
	public double getPreis(String groesse){
		switch(groesse){
		case "Small":
			return this.preisS.get();
		case "Medium":
			return this.preisM.get();
		default:
			return this.preisL.get();
		}
	}
}
