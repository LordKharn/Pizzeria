package Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Backend.KonstantInstanceSaver;

import java.sql.Connection;

import Models.Belag;
import Models.Pizza;

public class PizzaDAO {
	private Connection dbConnect;
	
	public PizzaDAO(){
		this.dbConnect = (Connection) DAOFactory.createConnection();
	}
		
	public void insertPizza(Pizza pizza,int bestellId) {
		String sql = "INSERT INTO pizza (Pizzagroesse, Bestellungsid) VALUES ('" + pizza.getPizzaGroesse().getGroesse() + "', '"+ bestellId + "')";
		if(this.dbConnect != null){
			try{
				PreparedStatement preStm = this.dbConnect.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				preStm.executeUpdate();
				ResultSet rs = preStm.getGeneratedKeys();
				rs.first();
				pizza.setId(rs.getInt(1));
				preStm.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			if(pizza.getBelag().size() != 0){
				String sql2 = "INSERT INTO pizzabelag (PizzaID,BelagID) VALUES ";
				for(int i = 0; i < pizza.getBelag().size(); i++){
					sql2 += "(" + pizza.getId() + "," + pizza.getBelag().get(i).getId() + "),";
				}
				sql2 = sql2.substring(0, sql2.length()-1);
				
				try{
					PreparedStatement preStm = this.dbConnect.prepareStatement(sql2);
					preStm.executeUpdate();
					preStm.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	public ArrayList<Pizza> findPizza(int BestellId){
		String sql="SELECT p.ID, p.Pizzagroesse, pb.BelagID "
				+ "FROM pizza p LEFT JOIN Pizzabelag pb ON p.ID = pb.PizzaID "
				+ "LEFT JOIN belag b ON b.ID = pb.BelagID WHERE p.Bestellungsid = " + BestellId + " ORDER BY p.ID ";
		ArrayList<Pizza> pizzalist = new ArrayList<Pizza>();
		if(this.dbConnect != null){
			try{
				PreparedStatement preStm = this.dbConnect.prepareStatement(sql);
				ResultSet rs = preStm.executeQuery();

				Pizza grundPizza = null;
				int pizzaID = 0;
				
				while(rs.next()){
					if(rs.getInt(1) != pizzaID){
						grundPizza = new Pizza(rs.getInt(1),KonstantInstanceSaver.getPizzaGroesse(rs.getString(2)),new ArrayList<Belag>());
						pizzalist.add(grundPizza);
					}
					if(rs.getInt(3) != 0){
						grundPizza.addBelag(KonstantInstanceSaver.getBelag(rs.getInt(3)));
					}
					pizzaID = rs.getInt(1);
				}				
				preStm.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return pizzalist;
	}
}