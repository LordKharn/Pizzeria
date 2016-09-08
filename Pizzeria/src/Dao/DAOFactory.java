package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactory {
	//private static String className = "com.mysql.jdbc.Driver";
		//Hier müssen Ihre Daten für name und kennwort eingetragen werden, bitte nicht root benutzen
		private static String dbString = "jdbc:mysql://localhost:3306/pizzaservice?user=root&password=";
		private static Connection connection;
		
		public static Connection createConnection(){
			//geändert, um das Registeren der Klasse des DB-Treibers nicht 100 mal zu machen
			try {
				if(connection == null || connection.isClosed()){
					try{
						//Class.forName(className);
						connection = ((Connection) DriverManager.getConnection(dbString));
						return connection;
					}
					//catch(ClassNotFoundException e){
					//	e.printStackTrace();
					//}
					
					catch(SQLException  e){
						e.printStackTrace();
					}
				}
				else{
					return connection;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public static KundeDAO getKundeDAO() {
			KundeDAO kundeDAO = new KundeDAO();
			return kundeDAO;
		}
		
		public static BelagDAO getBelagDAO() {
			BelagDAO belagDAO = new BelagDAO();
			return belagDAO;
		}
		
		public static PizzaDAO getPizzaDAO() {
			PizzaDAO pizzaDAO = new PizzaDAO();
			return pizzaDAO;
		}
		
		public static GetraenkDAO getGetraenkDAO() {
			GetraenkDAO getraenkDAO = new GetraenkDAO();
			return getraenkDAO;
		}
		
		public static BestellungDAO getBestellungkDAO() {
			BestellungDAO bestellungDAO = new BestellungDAO();
			return bestellungDAO;
		}
		
		public static PizzaGroesseDAO getPizzaGroesseDAO() {
			PizzaGroesseDAO pizzaGroesseDAO = new PizzaGroesseDAO();
			return pizzaGroesseDAO;
		}
}
