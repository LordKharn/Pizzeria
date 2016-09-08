package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.sql.Connection;

import Models.PizzaGroesse;

public class PizzaGroesseDAO {
	private Connection dbConnect;
	
	public PizzaGroesseDAO(){
		this.dbConnect = (Connection) DAOFactory.createConnection();
	}
	
	public HashMap<String,PizzaGroesse> selectAllPizzaGroesse(){
		String sql="SELECT * FROM pizzagroesse ORDER BY Preis";
		HashMap<String,PizzaGroesse> groesse = new HashMap<String,PizzaGroesse>();
		if(this.dbConnect != null){
			try{
				PreparedStatement preStm = this.dbConnect.prepareStatement(sql);
				ResultSet rs = preStm.executeQuery();
				while(rs.next()){
					groesse.put(rs.getString(1),(new PizzaGroesse(rs.getString(1),rs.getDouble(2))));
				}
				preStm.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return groesse;
	}
}
