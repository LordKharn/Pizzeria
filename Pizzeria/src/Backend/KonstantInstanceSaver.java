package Backend;

import java.util.HashMap;

import Dao.DAOFactory;
import Models.Belag;
import Models.Getraenk;
import Models.PizzaGroesse;

public class KonstantInstanceSaver {
	private static HashMap<Integer,Belag> belaege = DAOFactory.getBelagDAO().selectAllBelag();
	private static HashMap<String,PizzaGroesse> pizzaGroessen = DAOFactory.getPizzaGroesseDAO().selectAllPizzaGroesse() ;
	private static HashMap<Integer,Getraenk> getraenke = DAOFactory.getGetraenkDAO().selectAllGetraenk();
	
	/**
	 * @return the belaege
	 */
	public static HashMap<Integer, Belag> getBelaege() {
		return belaege;
	}
	
	/**
	 * @return the pizzaGroessen
	 */
	public static HashMap<String, PizzaGroesse> getPizzaGroessen() {
		return pizzaGroessen;
	}

	/**
	 * @return the getraenke
	 */
	public static HashMap<Integer, Getraenk> getGetraenke() {
		return getraenke;
	}
	
	/**
	 * @return the belaege
	 */
	public static Belag getBelag(int id) {
		return belaege.get(id);
	}
	
	/**
	 * @return the pizzaGroessen
	 */
	public static PizzaGroesse getPizzaGroesse(String groesse) {
		return pizzaGroessen.get(groesse);
	}

	/**
	 * @return the getraenke
	 */
	public static Getraenk getGetraenk(int id) {
		return getraenke.get(id);
	}
	
}
