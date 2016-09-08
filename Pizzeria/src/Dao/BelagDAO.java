package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import Models.Belag;


public class BelagDAO {
	private Connection dbConnect;
	
	public BelagDAO(){
		this.dbConnect = (Connection) DAOFactory.createConnection();
	}
	
//	public Belag getBelag(int id) {
//		String sql = "SELECT b.Name, bt.PreisS, bt.PreisM, bt.PreisL FROM belag b, belagstyp bt WHERE b.ID = " + id + " AND b.BelagstypID = bt.ID"; 
//		Belag belag = null;
//		
//		if(this.dbConnect != null){
//			try{
//				PreparedStatement preStm = this.dbConnect.prepareStatement(sql);
//				ResultSet rs = preStm.executeQuery();
//				rs.first();
//				belag = new Belag(rs.getString(1),rs.getInt(2));
//				preStm.close();
//				return belag;
//			}
//			catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return belag;
//	}
	
	public HashMap<Integer,Belag> selectAllBelag(){
		String sql="SELECT b.ID, b.Name, bt.PreisS, bt.PreisM, bt.PreisL, bt.Name FROM belag b, belagstyp bt WHERE b.BelagstypID = bt.ID";
		HashMap<Integer,Belag> belag = new HashMap<Integer,Belag>();
		if(this.dbConnect != null){
			try{
				PreparedStatement preStm = this.dbConnect.prepareStatement(sql);
				ResultSet rs = preStm.executeQuery();
				while(rs.next()){
					belag.put(rs.getInt(1), new Belag(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4), rs.getDouble(5), rs.getString(6)));
				}
				preStm.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return belag;
	}
	
//	public Belag findBelag(int id){
//		String sql="SELECT b.ID, b.Name, bt.PreisS, bt.PreisM, bt.PreisL, bt.Name FROM belag b, belagstyp bt WHERE b.BelagstypID = bt.ID AND b.ID =" + id;
//		Belag belag = null;
//		if(this.dbConnect != null){
//			try{
//				PreparedStatement preStm = this.dbConnect.prepareStatement(sql);
//				ResultSet rs = preStm.executeQuery();
//				while(rs.next()){
//					belag = new Belag(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4), rs.getDouble(5), rs.getString(6));
//				}
//				preStm.close();
//			}
//			catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return belag;
//	}
	
}
